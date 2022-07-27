package ru.grabovsky.recordkeeping.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import ru.grabovsky.recordkeeping.configs.MailConfigTest;
import ru.grabovsky.recordkeeping.context.HTMLEmailContext;
import ru.grabovsky.recordkeeping.context.SimpleTextEmailContext;
import ru.grabovsky.recordkeeping.exceptions.mail.MailTemplateNotFoundException;
import ru.grabovsky.recordkeeping.exceptions.mail.PrepareMailMessageException;
import ru.grabovsky.recordkeeping.exceptions.mail.ProcessMailTemplateException;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MailConfigTest.class})
class MailServiceTest {
    private MailServiceImpl mailService;
    private final FreeMarkerConfigurer freeMarkerConfigurer = new MailConfigTest().freemarkerClassLoaderConfig();
    private final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

    @BeforeEach
    void setUp() {
        mailSender.setHost("192.168.1.20");
        mailSender.setPort(1025);
        mailService = new MailServiceImpl(mailSender, freeMarkerConfigurer);
    }

    @Test
    void success_html_email_send() {
        HTMLEmailContext context = new HTMLEmailContext();
        context.setTo("test@test.ru");
        context.setReceiverDisplayName("TestUser");
        context.setFrom("noreply@example.com");
        context.setSenderDisplayName("NoReply Sender");
        context.setSubject("TestMessage");
        context.setTemplateName("test-template");
        Map<String, Object> messageContext = new HashMap<>();
        messageContext.put("recipientName", "TestRecipientName");
        messageContext.put("text", "Welcome");
        messageContext.put("senderName", "TestSenderName");
        context.setContext(messageContext);

        assertThatNoException().isThrownBy(() -> mailService.sendHTMLMail(context));
    }

    @Test
    void error_html_email_send_with_empty_address() {
        HTMLEmailContext context = new HTMLEmailContext();
        context.setTo("");
        context.setReceiverDisplayName("TestUser");
        context.setFrom("noreply@example.com");
        context.setSenderDisplayName("NoReply Sender");
        context.setSubject("TestMessage");
        context.setTemplateName("test-template");
        Map<String, Object> messageContext = new HashMap<>();
        messageContext.put("recipientName", "TestRecipientName");
        messageContext.put("text", "Welcome");
        messageContext.put("senderName", "TestSenderName");
        context.setContext(messageContext);
        assertThatExceptionOfType(PrepareMailMessageException.class)
                .isThrownBy(() -> mailService.sendHTMLMail(context));
    }

    @Test
    void error_html_email_send_with_incorrect_template_name() {
        HTMLEmailContext context = new HTMLEmailContext();
        context.setTo("test@test.ru");
        context.setReceiverDisplayName("TestUser");
        context.setFrom("noreply@example.com");
        context.setSenderDisplayName("NoReply Sender");
        context.setSubject("TestMessage");
        context.setTemplateName("something");
        Map<String, Object> messageContext = new HashMap<>();
        messageContext.put("recipientName", "TestRecipientName");
        messageContext.put("text", "Welcome");
        messageContext.put("senderName", "TestSenderName");
        context.setContext(messageContext);
        assertThatExceptionOfType(MailTemplateNotFoundException.class)
                .isThrownBy(() -> mailService.sendHTMLMail(context));
    }

    @Test
    void error_html_email_send_with_incorrect_template_context() {
        HTMLEmailContext context = new HTMLEmailContext();
        context.setTo("");
        context.setReceiverDisplayName("TestUser");
        context.setFrom("noreply@example.com");
        context.setSenderDisplayName("NoReply Sender");
        context.setSubject("TestMessage");
        context.setTemplateName("test-template");
        Map<String, Object> messageContext = new HashMap<>();
        messageContext.put("recipientName", "TestRecipientName");
        messageContext.put("text", "Welcome");
        context.setContext(messageContext);
        assertThatExceptionOfType(ProcessMailTemplateException.class)
                .isThrownBy(() -> mailService.sendHTMLMail(context));
    }

    @Test
    void success_simple_text_email_send() {
        SimpleTextEmailContext context = new SimpleTextEmailContext();
        context.setTo("test@test.ru");
        context.setReceiverDisplayName("test@test.ru");
        context.setFrom("noreply@example.com");
        context.setSenderDisplayName("NoReply Sender");
        context.setSubject("SimpleTestMessage");
        context.setText("Test Text message");
        assertThatNoException()
                .isThrownBy(() -> mailService.sendSimpleTextEmail(context));
    }

    @Test
    void error_simple_text_email_send_with_empty_addresses() {
        SimpleTextEmailContext context = new SimpleTextEmailContext();
        context.setTo("");
        context.setReceiverDisplayName("test@test.ru");
        context.setFrom("noreply@example.com");
        context.setSenderDisplayName("NoReply Sender");
        context.setSubject("SimpleTestMessage");
        context.setText("Test Text message");
        assertThatExceptionOfType(PrepareMailMessageException.class)
                .isThrownBy(() -> mailService.sendSimpleTextEmail(context));
    }
}