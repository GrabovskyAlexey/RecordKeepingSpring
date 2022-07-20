package ru.grabovsky.recordkeeping.context;

import lombok.Data;

/**
 * Info to create simple text email message
 *
 * @author GrabovskyAlexey
 */
@Data
public class SimpleTextEmailContext extends EmailContext{
    private String text;
}
