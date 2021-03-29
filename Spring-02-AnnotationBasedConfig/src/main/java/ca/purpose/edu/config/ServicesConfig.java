package ca.purpose.edu.config;


import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

@Configuration("config")
public class ServicesConfig {
    public static Locale locale = Locale.ENGLISH;

    public static MessageSource messageSource(String path) {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename(path);
        ms.setDefaultEncoding("Windows-1251");
        return ms;
    }
}
