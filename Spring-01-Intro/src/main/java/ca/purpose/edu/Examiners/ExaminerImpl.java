package ca.purpose.edu.Examiners;

import ca.purpose.edu.ExamFormers.ExamFormer;
import ca.purpose.edu.Student;

import java.io.*;
import java.util.List;


public class ExaminerImpl implements Examiner {
    private final ExamFormer examFormer;

    public ExaminerImpl(ExamFormer examFormer) {
        this.examFormer = examFormer;
    }

    public Student runExam(Student student) {
        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
            initStudent(student, consoleReader);
            evaluateStudent(student, consoleReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return student;
    }

    private void initStudent(Student student, BufferedReader consoleReader) throws IOException {
        System.out.print("Please enter your first name: ");
        student.setFirstName(consoleReader.readLine());
        System.out.print("Please enter your last name: ");
        student.setLastName(consoleReader.readLine());
    }

    private void evaluateStudent(Student student, BufferedReader consoleReader) throws IOException {
        int mark = 0;
        List<String> questions = examFormer.parseQuestions();
        List<String> answers = examFormer.parseAnswers();
        for (int i = 0; i < questions.size(); i++) {
            System.out.println(questions.get(i));
            if (consoleReader.readLine().equalsIgnoreCase(answers.get(i))) {
                mark++;
            }
        }
        student.setMark(mark);
    }
}
