package ru.grabovsky.recordkeeping.services.abstaract;

import ru.grabovsky.recordkeeping.context.HTMLEmailContext;
import ru.grabovsky.recordkeeping.context.SimpleTextEmailContext;

/**
 * MailService interface
 *
 * @author GrabovskyAlexey
 */
public interface MailService{
    /**
     * Send HTML context to user
     *
     * @param context Html message info for send
     */
    void sendHTMLMail(HTMLEmailContext context);
    /**
     * Send simple text mail to user
     *
     * @param context Simple text message info for send
     */
    void sendSimpleTextEmail(SimpleTextEmailContext context);
}
