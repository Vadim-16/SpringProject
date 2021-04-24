package ca.purpose.edu.repositories;


import ca.purpose.edu.models.Book;
import ca.purpose.edu.models.Comment;
import ca.purpose.edu.models.Reader;

import java.util.List;
import java.util.Optional;

public interface ReaderRepositoryJpa {
    Optional<Reader> findById(long readerId);

    List<Reader> findAll();

    List<Comment> findCommentsByReaderId(long readerId);

    List<Book> findBooksByReaderId(long readerId);

    Reader save(Reader reader);

    boolean deleteById(long readerId);

    long count();
}
