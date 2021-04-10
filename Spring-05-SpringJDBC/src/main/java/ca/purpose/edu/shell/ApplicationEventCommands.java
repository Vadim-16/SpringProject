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
        else {
            boolean isDeleted = false;
            switch (objectName.toLowerCase()) {
                case "book": {
                    isDeleted = bookDao.deleteById(objectId);
                    break;
                }
                case "author": {
                    isDeleted = authorDao.deleteById(objectId);
                    break;
                }
                case "genre": {
                    isDeleted = genreDao.deleteById(objectId);
                    break;
                }
                default:
                    System.out.println("Improper object query: " + objectName);
            }
            String message = isDeleted ? "Delete successful" : "Delete failed";
            System.out.println(message);
        }
    }

    @ShellMethod(value = "Update object", key = {"u", "update"})
    public void update(String object, long objectId, String objectName,
                       @ShellOption(defaultValue = "0") long authorId, @ShellOption(defaultValue = "0") long genreId) {
        if (userName == null) System.out.println("Please login");
        else {
            boolean isUpdated = false;
            switch (object.toLowerCase()) {
                case "book": {
                    Author author = authorDao.getById(authorId);
                    Genre genre = genreDao.getById(genreId);
                    isUpdated = bookDao.update(new Book(objectId, objectName, author, genre));
                    break;
                }
                case "author": {
                    isUpdated = authorDao.update(new Author(objectId, objectName));
                    break;
                }
                case "genre": {
                    isUpdated = genreDao.update(new Genre(objectId, objectName));
                    break;
                }
                default:
                    System.out.println("Improper object query: " + object);
            }
            String message = (isUpdated) ? "Update successful" : "Update failed";
            System.out.println(message);
        }
    }

    @ShellMethod(value = "Insert object", key = {"i", "insert"})
    public void insert(String object, String objectName,
                       @ShellOption(defaultValue = "0") long authorId, @ShellOption(defaultValue = "0") long genreId) {
        if (userName == null) System.out.println("Please login");
        else switch (object.toLowerCase()) {
            case "book": {
                Author author = authorDao.getById(authorId);
                Genre genre = genreDao.getById(genreId);
                System.out.println("Generated bookId: " + bookDao.insert(new Book(0, objectName, author, genre)));
                break;
            }
            case "author": {
                System.out.println("Generated authorId: " + authorDao.insert(new Author(0, objectName)));
                break;
            }
            case "genre": {
                System.out.println("Generated genreId: " + genreDao.insert(new Genre(0, objectName)));
                break;
            }
            default:
                System.out.println("Improper object query: " + objectName);
        }
    }
}
