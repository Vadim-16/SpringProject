package ca.purpose.edu.dao.authordao;

import ca.purpose.edu.domain.Author;

import java.util.List;

public interface AuthorDao {
    Author getById(long authorId);

    int count();

    void insert(Author author);

    List<Author> getAll();

    void deleteById(long authorId);
}
