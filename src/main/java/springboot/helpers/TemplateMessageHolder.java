package springboot.helpers;


import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

public class TemplateMessageHolder {

    ResourceBundleMessageSource source;

    public TemplateMessageHolder(ResourceBundleMessageSource source) {
        this.source = source;
    }

    public String get(String property) {
        return source.getMessage(property, null, LocaleContextHolder.getLocale());
    }

    public String get(String property, Object... args) {
        return source.getMessage(property, args, LocaleContextHolder.getLocale());
    }
}
