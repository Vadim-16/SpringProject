package ca.purpose.edu.config;


import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

@Configuration
public class ServicesConfig {
    public static Locale locale = Locale.ENGLISH;


    public static MessageSource messageSource(String path) {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename(path);
        ms.setDefaultEncoding("Windows-1251");
        return ms;
    }


    //    @Bean
//    public ExamService examService(Examiner examiner) {
//        return new ExamServiceImpl(examiner);
//    }
//
//    @Bean
//    public Examiner examiner(ExamFormer examFormer) {
//        return new ExaminerImpl(examFormer);
//    }
//
//    @Bean
//    public ExamFormer examFormer(QuestionExtractor questionExtractor) {
//        return new ExamFormerImpl(questionExtractor);
//    }

}
