package ca.purpose.edu.examFormers.extractors;

import ca.purpose.edu.examFormers.Question;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class DBQuestionExtractor implements QuestionExtractor{
    private final Connection connection;

    public DBQuestionExtractor(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Question> getQuestionnaire() {
        List<Question> questionnaire = new ArrayList<>();
        //imitation of business logic for retrieval from DB
        Question question1 = new Question();
        question1.setQuestion("2 + 2 = ?");
        question1.setAnswer("4");
        questionnaire.add(question1);

        Question question2 = new Question();
        question2.setQuestion("2 + 2 * 2 = ?");
        question2.setAnswer("6");
        questionnaire.add(question2);

        Question question3 = new Question();
        question3.setQuestion("12^2 = ?");
        question3.setAnswer("144");
        questionnaire.add(question3);

        Question question4 = new Question();
        question4.setQuestion("Canada's capital?");
        question4.setAnswer("Ottawa");
        questionnaire.add(question4);

        Question question5 = new Question();
        question5.setQuestion("World War 2 end year?,1945");
        question5.setAnswer("1945");
        questionnaire.add(question5);

        return questionnaire;
    }
}
