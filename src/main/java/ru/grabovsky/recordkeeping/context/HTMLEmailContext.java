package ru.grabovsky.recordkeeping.context;

import lombok.Data;

import java.util.Map;

/**
 * Info to create HTML email message
 *
 * @author GrabovskyAlexey
 */
@Data
public class HTMLEmailContext extends EmailContext{
    private String templateName;
    private Map<String, Object> context;
}
