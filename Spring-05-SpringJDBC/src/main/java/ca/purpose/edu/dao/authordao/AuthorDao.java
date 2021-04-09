package ca.purpose.edu.dao.authordao;

import ca.purpose.edu.domain.Author;

import java.util.List;

public interface AuthorDao {

    Author getById(long authorId);

    void deleteById(long authorId);

    long insert(Author author);

    void update(Author author);

    List<Author> getAll();

    int count();

}
