package ca.purpose.edu.Config;

import ca.purpose.edu.ExamFormers.Extractors.FileQuestionExtractor;
import ca.purpose.edu.ExamFormers.Extractors.QuestionExtractor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:FilePath.properties")
public class DaoConfig {

    @Bean
    public QuestionExtractor questionExtractor(@Value("${examPath_ru}")String examPath) {
        return new FileQuestionExtractor(examPath);
    }

}
