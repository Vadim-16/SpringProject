package ca.purpose.edu.shell;

import ca.purpose.edu.ExamService;
import ca.purpose.edu.ExamServiceImpl;
import ca.purpose.edu.Main;
import ca.purpose.edu.models.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationEventCommands {
    private String userName;

    @Autowired
    private ExamService examService;


    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "student") String userName) {
        this.userName = userName;
        return String.format("Добро пожаловать: %s", userName);
    }

    @ShellMethod(value = "Time command", key = {"t", "time"})
    public void time() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE MMM dd, yyyy - h:mma");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
    }

    @ShellMethod(value = "Start exam", key = {"s", "start"})
    public void start() {
        if (userName == null) System.out.println("Сначала залогиньтесь");
        else examService.runService(new Student());
    }
}
