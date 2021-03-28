package ca.purpose.edu.config;


import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfig {
    public static String locale = "en";


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
