package ca.purpose.edu.repositories;

import ca.purpose.edu.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepositoryJpa {
    Optional<Genre> findById(long id);

    List<Genre> findAll();

    Genre save(Genre genre);

    boolean deleteById(long genreId);

    long count();
}
