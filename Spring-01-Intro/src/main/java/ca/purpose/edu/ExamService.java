package ca.purpose.edu;

public class ExamService {
    public static void main(String[] args) {
        Examiner examiner = new Examiner(".\\Spring-01-Intro\\src\\main\\resources\\QuestionsWithAnswers.csv");
        Student student = examiner.examineStudent();
        System.out.println("Thank you for your answers, " + student.getFirstName() + " " + student.getLastName() + "!");
        System.out.println("You got " + student.getMark());
        System.out.println(student);
    }
}
