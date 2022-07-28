package ru.grabovsky.recordkeeping.services;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import ru.grabovsky.recordkeeping.context.HTMLEmailContext;
import ru.grabovsky.recordkeeping.context.SimpleTextEmailContext;
import ru.grabovsky.recordkeeping.exceptions.mail.MailTemplateNotFoundException;
import ru.grabovsky.recordkeeping.exceptions.mail.PrepareMailMessageException;
import ru.grabovsky.recordkeeping.exceptions.mail.ProcessMailTemplateException;
import ru.grabovsky.recordkeeping.services.abstaract.MailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Service for send HTML and simple txt mail to users
 *
 * @author GrabovskyAlexey
 */
@RequiredArgsConstructor
@Service
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;

    private final FreeMarkerConfigurer freemarkerConfigurer;

    @Override
    public void sendHTMLMail(HTMLEmailContext context) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            Template freemarkerTemplate = freemarkerConfigurer.getConfiguration()
                    .getTemplate(context.getTemplateName() + ".ftl");
            String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerTemplate, context.getContext());
            helper.setTo(context.getTo());
            helper.setSubject(context.getSubject());
            helper.setFrom(context.getFrom());
            helper.setText(htmlBody, true);
            mailSender.send(mimeMessage);
        } catch (TemplateException e) {
            throw new ProcessMailTemplateException(e.getMessage());
        } catch (MessagingException e) {
            throw new PrepareMailMessageException(e.getMessage());
        } catch (IOException e){
            throw new MailTemplateNotFoundException(e.getMessage());
        }
    }


    @Override
    public void sendSimpleTextEmail(SimpleTextEmailContext context) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(context.getTo());
            message.setFrom(context.getFrom());
            message.setSubject(context.getSubject());
            message.setText(context.getText());
            mailSender.send(message);
        } catch (MailParseException e){
            throw new PrepareMailMessageException(e.getMessage());
        }
    }
}
