package ca.purpose.edu.dao.genredao;

import ca.purpose.edu.domain.Genre;

import java.util.List;

public interface GenreDao {

    Genre getById(long genreId);

    boolean deleteById(long genreId);

    long insert(Genre genre);

    boolean update(Genre genre);

    List<Genre> getAll();

    int count();

}
