package ca.purpose.edu.dao.bookdao;

import ca.purpose.edu.domain.Book;

import java.util.List;

public interface BookDao {

    Book getById(long bookId);

    void deleteById(long bookId);

    long insert(Book book);

    void update(Book book);

    List<Book> getAll();

    int count();

}
