package ca.purpose.edu.shell;

import ca.purpose.edu.models.*;
import ca.purpose.edu.repositories.*;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@ShellComponent
public class ApplicationEventCommands {
    private String userName;
    private BookRepositoryJpa bookRepository;
    private AuthorRepositoryJpa authorRepository;
    private GenreRepositoryJpa genreRepository;
    private CommentRepositoryJpa commentRepository;
    private ReaderRepositoryJpa readerRepository;

    public ApplicationEventCommands(BookRepositoryJpa bookRepository, AuthorRepositoryJpa authorRepository,
                                    GenreRepositoryJpa genreRepository, CommentRepositoryJpa commentRepository,
                                    ReaderRepositoryJpa readerRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.commentRepository = commentRepository;
        this.readerRepository = readerRepository;
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
                System.out.println("Total books: " + bookRepository.findAll().size());
                break;
            }
            case "authors": {
                System.out.println("Total authors: " + authorRepository.count());
                break;
            }
            case "genres": {
                System.out.println("Total genres: " + genreRepository.findAll().size());
                break;
            }
            case "comments": {
                System.out.println("Total comments: " + commentRepository.findAll().size());
                break;
            }
            case "readers": {
                System.out.println("Total readers: " + readerRepository.findAll().size());
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
                System.out.println(bookRepository.findById(objectId).orElse(null));
                break;
            }
            case "author": {
                System.out.println(authorRepository.findById(objectId).orElse(null));
                break;
            }
            case "genre": {
                System.out.println(genreRepository.findById(objectId).orElse(null));
                break;
            }
            case "comment": {
                System.out.println(commentRepository.findById(objectId).orElse(null));
                break;
            }
            case "reader": {
                System.out.println(readerRepository.findById(objectId).orElse(null));
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
                System.out.println(bookRepository.findAll());
                break;
            }
            case "authors": {
                System.out.println(authorRepository.findAll());
                break;
            }
            case "genres": {
                System.out.println(genreRepository.findAll());
                break;
            }
            case "comments": {
                System.out.println(commentRepository.findAll());
                break;
            }
            case "readers": {
                System.out.println(readerRepository.findAll());
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
                    isDeleted = bookRepository.deleteById(objectId);
                    break;
                }
                case "author": {
                    isDeleted = authorRepository.deleteById(objectId);
                    break;
                }
                case "genre": {
                    isDeleted = genreRepository.deleteById(objectId);
                    break;
                }
                case "comment": {
                    isDeleted = commentRepository.deleteById(objectId);
                    break;
                }
                case "reader": {
                    isDeleted = readerRepository.deleteById(objectId);
                    break;
                }
                default:
                    System.out.println("Improper object query: " + objectName);
            }
            String message = isDeleted ? "Delete successful" : "Delete failed";
            System.out.println(message);
        }
    }

    @ShellMethod(value = "Insert/update object", key = {"i", "u", "update", "insert"})
    public void insert(String object, String objectName, long objectId,
                       @ShellOption(defaultValue = "0") long authorId, @ShellOption(defaultValue = "0") long genreId) {
        if (userName == null) System.out.println("Please login");
        else switch (object.toLowerCase()) {
            case "book": {
                Author author = authorRepository.findById(authorId).orElse(null);
                Genre genre = genreRepository.findById(genreId).orElse(null);
                System.out.println("Generated/updated bookId: " + bookRepository.save(new Book(objectId, objectName, author, genre)).getBookId());
                break;
            }
            case "author": {
                System.out.println("Generated/updated authorId: " + authorRepository.save(new Author(objectId, objectName)).getAuthorId());
                break;
            }
            case "genre": {
                System.out.println("Generated/updated genreId: " + genreRepository.save(new Genre(objectId, objectName)).getGenreId());
                break;
            }
            case "comment": {
                Reader reader = readerRepository.findById(authorId).orElse(null);
                Book book = bookRepository.findById(genreId).orElse(null);
                System.out.println("Generated/updated commentId: " + commentRepository.save(new Comment(objectId, objectName)).getCommentId());
                break;
            }
            case "reader": {
                List<Comment> comments = readerRepository.findCommentsByReaderId(authorId);
                List<Book> books = readerRepository.findBooksByReaderId(genreId);
                System.out.println("Generated/updated readerId: " + readerRepository.save(new Reader(objectId, objectName, comments, books)).getReaderId());
                break;
            }
            default:
                System.out.println("Improper object query: " + objectName);
        }
    }
}
