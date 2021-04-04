package ca.purpose.edu.examformers;

import ca.purpose.edu.examformers.extractors.FileQuestionExtractor;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;


@TestConfiguration
public class MyTestConfig {
    @Bean
    public ExamFormer examFormer(){
        return new ExamFormerImpl(new FileQuestionExtractor(".\\src\\test\\test-resources\\test-questions_ru.csv"));
    }
}
