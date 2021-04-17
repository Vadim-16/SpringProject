package ca.purpose.edu.dao;

import ca.purpose.edu.models.Book;

import java.util.List;

public interface BookDao {

    Book getById(long bookId);

    boolean deleteById(long bookId);

    long insert(Book book);

    boolean update(Book book);

    List<Book> getAll();

    int count();

}
