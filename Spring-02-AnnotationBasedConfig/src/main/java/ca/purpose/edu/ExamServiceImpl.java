package ca.purpose.edu;

import ca.purpose.edu.config.ServicesConfig;
import ca.purpose.edu.examiners.Examiner;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@ComponentScan
public class ExamServiceImpl implements ExamService {
    private final Examiner examiner;

    public ExamServiceImpl(Examiner examiner) {
        this.examiner = examiner;
    }


    public static void main(String[] args) {
        ServicesConfig.locale = Locale.forLanguageTag("ru");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ExamServiceImpl.class);
        ExamServiceImpl examService = context.getBean(ExamServiceImpl.class);
        examService.runService(new Student());
    }


    @Override
    public void runService(Student student) {
        student = examiner.runExam(student);
        MessageSource ms = messageSource();
        String result = ms.getMessage("result.msg", new String[]{student.getFirstName(), student.getLastName(),
                String.valueOf(student.getMark())}, ServicesConfig.locale);
        System.out.println(result);
    }

    private static MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/message");
        ms.setDefaultEncoding("Windows-1251");
        return ms;
    }

}
