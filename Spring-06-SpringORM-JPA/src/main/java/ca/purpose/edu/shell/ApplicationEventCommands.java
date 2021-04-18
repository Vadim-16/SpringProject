package ca.purpose.edu.shell;


import ca.purpose.edu.models.Author;
import ca.purpose.edu.models.Book;
import ca.purpose.edu.models.Genre;
import ca.purpose.edu.repositories.*;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
                default:
                    System.out.println("Improper object query: " + objectName);
            }
            String message = isDeleted ? "Delete successful" : "Delete failed";
            System.out.println(message);
        }
    }

//    @ShellMethod(value = "Update object", key = {"u", "update"})
//    public void update(String object, long objectId, String objectName,
//                       @ShellOption(defaultValue = "0") long authorId, @ShellOption(defaultValue = "0") long genreId) {
//        if (userName == null) System.out.println("Please login");
//        else {
//            boolean isUpdated = false;
//            switch (object.toLowerCase()) {
//                case "book": {
//                    Author author = authorDao.getById(authorId);
//                    Genre genre = genreDao.getById(genreId);
//                    isUpdated = bookDao.update(new Book(objectId, objectName, author, genre));
//                    break;
//                }
//                case "author": {
//                    isUpdated = authorDao.update(new Author(objectId, objectName));
//                    break;
//                }
//                case "genre": {
//                    isUpdated = genreDao.update(new Genre(objectId, objectName));
//                    break;
//                }
//                default:
//                    System.out.println("Improper object query: " + object);
//            }
//            String message = (isUpdated) ? "Update successful" : "Update failed";
//            System.out.println(message);
//        }
//    }

    @ShellMethod(value = "Insert/update object", key = {"i", "u", "update", "insert"})
    public void insert(String object, String objectName, long objectId,
                       @ShellOption(defaultValue = "0") long authorId, @ShellOption(defaultValue = "0") long genreId) {
        if (userName == null) System.out.println("Please login");
        else switch (object.toLowerCase()) {
            case "book": {
                Author author = authorRepository.findById(authorId).orElse(null);
                ArrayList<Author> authors = new ArrayList<>();
                authors.add(author);
                Genre genre = genreRepository.findById(genreId).orElse(null);
                ArrayList<Genre> genres = new ArrayList<>();
                genres.add(genre);
                Comment comment = commentRepository.findById(commentId).orElse(null);
                ArrayList<Genre> genres = new ArrayList<>();
                genres.add(genre);
                System.out.println("Generated/updated bookId: " + bookRepository.save(new Book(objectId, objectName, authors, genres, )).getBookId());
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
            default:
                System.out.println("Improper object query: " + objectName);
        }
    }
}
