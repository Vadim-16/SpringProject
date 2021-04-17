package dao;

import ca.purpose.edu.dao.BookDao;
import ca.purpose.edu.dao.BookDaoJdbc;
import ca.purpose.edu.models.Author;
import ca.purpose.edu.models.Book;
import ca.purpose.edu.models.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;


import static org.junit.jupiter.api.Assertions.*;


@JdbcTest
@Sql("classpath:test-data.sql")
@ContextConfiguration(classes = {BookDaoJdbc.class})
public class BookDaoJdbcTest {

    @Autowired
    private BookDao bookDao;

    @Test
    @DisplayName("test book getById()")
    public void testGetById() {
        Book bookFromDao = bookDao.getById(6);
        Book book = new Book(6, "testingBook3", new Author(4, "testingAuthor1"),
                new Genre(6, "testingGenre3"));
        assertEquals(bookFromDao, book);
    }

    @Test
    @DisplayName("test book insert()")
    public void testInsert() {
        Book book = new Book(7, "testingBook4", new Author(4, "testingAuthor1"),
                new Genre(6, "testingGenre3"));
        long insertNumber = bookDao.insert(book);
        book.setBookId(insertNumber);
        Book bookFromDao = bookDao.getById(insertNumber);
        assertEquals(bookFromDao, book);
    }

    @Test
    @DisplayName("test book count()")
    public void testCount() {
        assertEquals(6, bookDao.count());
    }

    @Test
    @DisplayName("test book delete()")
    public void testDeleteById() {
        assertEquals(6, bookDao.count());
        boolean isDeleted = bookDao.deleteById(5);
        assertThrows(EmptyResultDataAccessException.class, () -> bookDao.getById(5));
        assertEquals(5, bookDao.count());
    }

    @Test
    @DisplayName("test book update()")
    public void testUpdate() {
        Book book = new Book(6, "Insomnia", new Author(4, "testingAuthor1"),
                new Genre(4, "testingGenre1"));
        boolean isUpdated = bookDao.update(book);
        Book bookFromDao = bookDao.getById(book.getBookId());
        assertEquals(bookFromDao, book);
    }

    @Test
    @DisplayName("test book getAll()")
    public void testGetAll() {
        assertEquals(6, bookDao.getAll().size());
        boolean isDeleted = bookDao.deleteById(4);
        assertEquals(5, bookDao.getAll().size());
    }
}
