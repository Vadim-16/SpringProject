package ca.purpose.edu;

import ca.purpose.edu.Config.DaoConfig;
import ca.purpose.edu.Config.ServicesConfig;
import ca.purpose.edu.Examiners.Examiner;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

@Configuration
@ComponentScan
public class ExamServiceImpl implements ExamService {
    private final Examiner examiner;

    public ExamServiceImpl(Examiner examiner) {
        this.examiner = examiner;
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ServicesConfig.class);
        context.register(DaoConfig.class);
        context.refresh();

        ExamServiceImpl examService = context.getBean(ExamServiceImpl.class);
        examService.runService(new Student());
    }

    @Override
    public void runService(Student student) {
        student = examiner.runExam(student);
        MessageSource ms = messageSource();
        System.out.println(ms.getMessage("result.msg", new String[]{student.getFirstName(), student.getLastName(),
                String.valueOf(student.getMark())}, Locale.forLanguageTag("ru")));
    }

    private MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/message");
        ms.setDefaultEncoding("Windows-1251");
        return ms;
    }
}
