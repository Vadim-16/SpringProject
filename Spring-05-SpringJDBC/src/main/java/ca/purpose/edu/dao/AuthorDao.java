package ca.purpose.edu.dao;

import ca.purpose.edu.models.Author;

import java.util.List;

public interface AuthorDao {

    Author getById(long authorId);

    boolean deleteById(long authorId);

    long insert(Author author);

    boolean update(Author author);

    List<Author> getAll();

    int count();

}
