package ca.purpose.edu.dao.bookdao;

import ca.purpose.edu.domain.Book;

import java.util.List;

public interface BookDao {
    Book getById(long bookId);

    int count();

    void insert(Book book);

    List<Book> getAll();

    void deleteById(long bookId);
}
