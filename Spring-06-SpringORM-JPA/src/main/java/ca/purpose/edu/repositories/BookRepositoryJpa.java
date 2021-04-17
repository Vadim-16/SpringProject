package ca.purpose.edu.repositories;

import ca.purpose.edu.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryJpa {
    Optional<Book> findById(long id);

    List<Book> findAll();

    Book save(Book book);


}
