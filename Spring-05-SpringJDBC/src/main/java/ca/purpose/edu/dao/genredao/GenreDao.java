package ca.purpose.edu.dao.genredao;

import ca.purpose.edu.domain.Genre;

import java.util.List;

public interface GenreDao {
    Genre getById(long genreId);

    int count();

    void insert(Genre genre);

    List<Genre> getAll();

    void deleteById(long genreId);
}
