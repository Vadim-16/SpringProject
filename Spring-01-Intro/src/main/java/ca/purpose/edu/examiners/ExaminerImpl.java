package ca.purpose.edu.examiners;

import ca.purpose.edu.examformers.ExamFormer;
import ca.purpose.edu.examformers.Question;
import ca.purpose.edu.Student;

import java.io.*;


public class ExaminerImpl implements Examiner {
    private final ExamFormer examFormer;

    public ExaminerImpl(ExamFormer examFormer) {
        this.examFormer = examFormer;
    }

    @Override
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
        for (Question question : examFormer.getQuestionnaire()) {
            System.out.println(question.getQuestion());
            if (consoleReader.readLine().equalsIgnoreCase(question.getAnswer())) {
                mark++;
            }
        }
        student.setMark(mark);
    }
}
