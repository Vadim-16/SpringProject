package ca.purpose.edu.repositories;

import ca.purpose.edu.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepositoryJpa {
    Optional<Author> findById(long authorId);

    List<Author> findAll();

    Author save(Author author);

    boolean deleteById(long authorId);

    long count();
}
