package ca.purpose.edu;

import java.io.*;


public class ExamRunner {
    private String filePath;

    public ExamRunner(String filePath) {
        this.filePath = filePath;
    }

    public int runExam() {
        int mark = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
            while (reader.ready()) {
                String line = reader.readLine();
                String[] qAndA = line.split(",");
                System.out.println(qAndA[0]);
                String studentAnswer = consoleReader.readLine();
                if (studentAnswer.equalsIgnoreCase(qAndA[1])) {
                    mark++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mark;
    }
}
