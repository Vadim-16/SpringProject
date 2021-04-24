package ca.purpose.edu.repositories;

import ca.purpose.edu.models.Book;
import ca.purpose.edu.models.Comment;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryJpa {
    Optional<Book> findById(long bookId);

    List<Book> findAll();

    List<Comment> findAllByBookId(long bookId);

    Book save(Book book);

    boolean deleteById(long bookId);

    long count();
}
