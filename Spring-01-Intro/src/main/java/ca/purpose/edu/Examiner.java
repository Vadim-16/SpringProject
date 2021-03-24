package ca.purpose.edu;

public class Examiner {
    private String examPath;

    public Examiner(String examPath) {
        this.examPath = examPath;
    }

    public Student examineStudent() {
        Student student = new StudentInitializer().initStudent();
        student.setMark(new ExamRunner(examPath).runExam());
        return student;
    }

}
