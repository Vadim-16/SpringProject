package ca.purpose.edu.ExamFormers.Extractors;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class DBExtractor implements Extractor{
    private Connection connection;

    public DBExtractor(Connection connection) {
        this.connection = connection;
    }

    public List<String> extractQuestionsWithAnswers() {
        List<String> questionsWithAnswers = new ArrayList<>();
        //imitation of business logic for retrieval from DB
        questionsWithAnswers.add("2 + 2 = ?,4");
        questionsWithAnswers.add("2 + 2 * 2 = ?,6");
        questionsWithAnswers.add("12^2 = ?,144");
        questionsWithAnswers.add("Canada's capital?,Ottawa");
        questionsWithAnswers.add("World War 2 end year?,1945");
        return questionsWithAnswers;
    }
}
