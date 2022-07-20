package ru.grabovsky.recordkeeping.context;

import lombok.Data;

/**
 * Info to create email message
 *
 * @author GrabovskyAlexey
 */
@Data
public class EmailContext {
    private String from;
    private String senderDisplayName;
    private String to;
    private String receiverDisplayName;
    private String subject;
}
