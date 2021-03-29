package ca.purpose.edu.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

@Configuration("config")
@PropertySource("classpath:app.properties")
public class ServicesConfig {
    private Locale locale;

    public ServicesConfig(@Value("${locale}") String locale) {
        this.locale = Locale.forLanguageTag(locale);
    }

    public String getLocaleMessage(String param, String... variables) {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/message");
        ms.setDefaultEncoding("Windows-1251");
        return ms.getMessage(param, variables, locale);
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
