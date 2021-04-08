package ca.purpose.edu.dao.genredao;

import ca.purpose.edu.domain.Genre;

import java.util.List;

public interface GenreDao {

    Genre getById(long genreId);

    void deleteById(long genreId);

    void insert(Genre genre);

    void update(Genre genre);

    List<Genre> getAll();

    int count();

}