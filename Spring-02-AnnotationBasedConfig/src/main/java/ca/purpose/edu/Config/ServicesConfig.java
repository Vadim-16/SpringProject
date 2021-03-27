package ca.purpose.edu.Config;

import ca.purpose.edu.ExamFormers.ExamFormer;
import ca.purpose.edu.ExamFormers.ExamFormerImpl;
import ca.purpose.edu.ExamFormers.Extractors.QuestionExtractor;
import ca.purpose.edu.ExamService;
import ca.purpose.edu.ExamServiceImpl;
import ca.purpose.edu.Examiners.Examiner;
import ca.purpose.edu.Examiners.ExaminerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfig {

    @Bean
    public ExamService examService(Examiner examiner) {
        return new ExamServiceImpl(examiner);
    }

    @Bean
    public Examiner examiner(ExamFormer examFormer) {
        return new ExaminerImpl(examFormer);
    }

    @Bean
    public ExamFormer examFormer(QuestionExtractor questionExtractor) {
        return new ExamFormerImpl(questionExtractor);
    }

}
