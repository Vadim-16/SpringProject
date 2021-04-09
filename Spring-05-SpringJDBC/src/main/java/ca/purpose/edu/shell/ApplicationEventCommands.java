package ca.purpose.edu.shell;

import ca.purpose.edu.dao.authordao.AuthorDao;
import ca.purpose.edu.dao.bookdao.BookDao;
import ca.purpose.edu.dao.genredao.GenreDao;
import ca.purpose.edu.domain.Author;
import ca.purpose.edu.domain.Book;
import ca.purpose.edu.domain.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.shell.ExitRequest;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationEventCommands {
    private String userName;

    @Autowired
    private BookDao bookDao;
    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private GenreDao genreDao;

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "user") String userName) {
        this.userName = userName;
        return String.format("Welcome: %s", userName);
    }

    @ShellMethod(value = "Time command", key = {"t", "time"})
    public void time() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE MMM dd, yyyy - h:mma");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
    }

    @ShellMethod(value = "Count objects", key = {"c", "count"})
    public void count(@ShellOption(defaultValue = "books") String objectName) {
        if (userName == null) System.out.println("Please login");
        else switch (objectName.toLowerCase()) {
            case "books" -> System.out.println("Total books: " + bookDao.count());
            case "authors" -> System.out.println("Total authors: " + authorDao.count());
            case "genres" -> System.out.println("Total genres: " + genreDao.count());
        }
    }

    @ShellMethod(value = "Get object by Id", key = {"g", "get"})
    public void get(String objectName, long objectId) {
        if (userName == null) System.out.println("Please login");
        else switch (objectName.toLowerCase()) {
            case "book" -> System.out.println(bookDao.getById(objectId));
            case "author" -> System.out.println(authorDao.getById(objectId));
            case "genre" -> System.out.println(genreDao.getById(objectId));
        }
    }

    @ShellMethod(value = "Get all objects", key = {"a", "getAll"})
    public void getAll(@ShellOption(defaultValue = "books") String objectName) {
        if (userName == null) System.out.println("Please login");
        else switch (objectName.toLowerCase()) {
            case "books" -> System.out.println(bookDao.getAll());
            case "authors" -> System.out.println(authorDao.getAll());
            case "genres" -> System.out.println(genreDao.getAll());
        }
    }

    @ShellMethod(value = "Delete object by Id", key = {"d", "delete"})
    public void delete(String objectName, long objectId) {
        if (userName == null) System.out.println("Please login");
        else switch (objectName.toLowerCase()) {
            case "book" -> bookDao.deleteById(objectId);
            case "author" -> authorDao.deleteById(objectId);
            case "genre" -> genreDao.deleteById(objectId);
        }
    }

    @ShellMethod(value = "Update object", key = {"u", "update"})
    public void update(String object, long objectId, String objectName,
                       @Nullable long authorId, @Nullable long genreId) {
        if (userName == null) System.out.println("Please login");
        else switch (object.toLowerCase()) {
            case "book" -> bookDao.update(new Book(objectId, objectName, authorId, genreId));
            case "author" -> authorDao.update(new Author(objectId, objectName));
            case "genre" -> genreDao.update(new Genre(objectId, objectName));
        }
    }

    @ShellMethod(value = "Insert object", key = {"i", "insert"})
    public void insert(String object, long objectId, String objectName,
                       @Nullable long authorId, @Nullable long genreId) {
        if (userName == null) System.out.println("Please login");
        else switch (object.toLowerCase()) {
            case "book" -> bookDao.insert(new Book(objectId, objectName, authorId, genreId));
            case "author" -> authorDao.insert(new Author(objectId, objectName));
            case "genre" -> genreDao.insert(new Genre(objectId, objectName));
        }
    }
}
