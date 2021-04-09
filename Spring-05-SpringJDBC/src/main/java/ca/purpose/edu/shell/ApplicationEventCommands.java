package ca.purpose.edu.shell;

import ca.purpose.edu.dao.authordao.AuthorDao;
import ca.purpose.edu.dao.bookdao.BookDao;
import ca.purpose.edu.dao.genredao.GenreDao;
import ca.purpose.edu.domain.Author;
import ca.purpose.edu.domain.Book;
import ca.purpose.edu.domain.Genre;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ShellComponent
public class ApplicationEventCommands {
    private String userName;
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public ApplicationEventCommands(BookDao bookDao, AuthorDao authorDao, GenreDao genreDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

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
            case "books": {
                System.out.println("Total books: " + bookDao.count());
                break;
            }
            case "authors": {
                System.out.println("Total authors: " + authorDao.count());
                break;
            }
            case "genres": {
                System.out.println("Total genres: " + genreDao.count());
                break;
            }
            default:
                System.out.println("Improper object query: " + objectName);
        }
    }

    @ShellMethod(value = "Get object by Id", key = {"g", "get"})
    public void get(String objectName, long objectId) {
        if (userName == null) System.out.println("Please login");
        else switch (objectName.toLowerCase()) {
            case "book": {
                System.out.println(bookDao.getById(objectId));
                break;
            }
            case "author": {
                System.out.println(authorDao.getById(objectId));
                break;
            }
            case "genre": {
                System.out.println(genreDao.getById(objectId));
                break;
            }
            default:
                System.out.println("Improper object query: " + objectName);
        }
    }

    @ShellMethod(value = "Get all objects", key = {"a", "getAll"})
    public void getAll(@ShellOption(defaultValue = "books") String objectName) {
        if (userName == null) System.out.println("Please login");
        else switch (objectName.toLowerCase()) {
            case "books": {
                System.out.println(bookDao.getAll());
                break;
            }
            case "authors": {
                System.out.println(authorDao.getAll());
                break;
            }
            case "genres": {
                System.out.println(genreDao.getAll());
                break;
            }
            default:
                System.out.println("Improper object query: " + objectName);
        }
    }

    @ShellMethod(value = "Delete object by Id", key = {"d", "delete"})
    public void delete(String objectName, long objectId) {
        if (userName == null) System.out.println("Please login");
        else switch (objectName.toLowerCase()) {
            case "book": {
                bookDao.deleteById(objectId);
                break;
            }
            case "author": {
                authorDao.deleteById(objectId);
                break;
            }
            case "genre": {
                genreDao.deleteById(objectId);
                break;
            }
            default:
                System.out.println("Improper object query: " + objectName);
        }
    }

    @ShellMethod(value = "Update object", key = {"u", "update"})
    public void update(String object, long objectId, String objectName,
                       @ShellOption(defaultValue = "0") long authorId, @ShellOption(defaultValue = "0") long genreId) {
        if (userName == null) System.out.println("Please login");
        else switch (object.toLowerCase()) {
            case "book": {
                bookDao.update(new Book(objectId, objectName, authorId, genreId));
                break;
            }
            case "author": {
                authorDao.update(new Author(objectId, objectName));
                break;
            }
            case "genre": {
                genreDao.update(new Genre(objectId, objectName));
                break;
            }
            default:
                System.out.println("Improper object query: " + objectName);
        }
    }

    @ShellMethod(value = "Insert object", key = {"i", "insert"})
    public void insert(String object, long objectId, String objectName,
                       @ShellOption(defaultValue = "0") long authorId, @ShellOption(defaultValue = "0") long genreId) {
        if (userName == null) System.out.println("Please login");
        else switch (object.toLowerCase()) {
            case "book": {
                bookDao.insert(new Book(objectId, objectName, authorId, genreId));
                break;
            }
            case "author": {
                authorDao.insert(new Author(objectId, objectName));
                break;
            }
            case "genre": {
                genreDao.insert(new Genre(objectId, objectName));
                break;
            }
            default:
                System.out.println("Improper object query: " + objectName);
        }
    }
}
