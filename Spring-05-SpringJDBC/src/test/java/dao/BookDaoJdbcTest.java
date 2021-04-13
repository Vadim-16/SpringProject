package dao;


import ca.purpose.edu.Main;
import ca.purpose.edu.dao.BookDao;
import ca.purpose.edu.domain.Author;
import ca.purpose.edu.domain.Book;
import ca.purpose.edu.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.shell.Shell;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = Main.class)
@ExtendWith(SpringExtension.class)
public class BookDaoJdbcTest {

    @MockBean
    private Shell shell;

    @Autowired
    private BookDao bookDao;

    @Test
    @DisplayName("test book getById()")
    public void testGetById() {
        Book bookFromDao = bookDao.getById(1);
        Book book = new Book(1, "Insomnia", new Author(2, "Gellar"),
                new Genre(3, "Horror"));
        assertEquals(bookFromDao, book);
    }

    @Test
    @DisplayName("test book insert()")
    public void testInsert() {
        Book book = new Book(4, "Insomnia", new Author(2, "Gellar"),
                new Genre(3, "Horror"));
        long insertNumber = bookDao.insert(book);
        Book bookFromDao = bookDao.getById(insertNumber);
        assertEquals(bookFromDao, book);
    }

    @Test
    @DisplayName("test book count()")
    public void testCount() {
        assertEquals(3, bookDao.count());
    }

    @Test
    @DisplayName("test book delete()")
    public void testDeleteById() {
        assertEquals(3, bookDao.count());
        boolean isDeleted = bookDao.deleteById(1);
        assertThrows(EmptyResultDataAccessException.class, () -> bookDao.getById(1));
        assertEquals(2, bookDao.count());
    }

    @Test
    @DisplayName("test book update()")
    public void testUpdate() {
        Book book = new Book(3, "Insomnia", new Author(2, "Gellar"),
                new Genre(3, "Horror"));
        boolean isUpdated = bookDao.update(book);
        Book bookFromDao = bookDao.getById(book.getBookId());
        assertEquals(bookFromDao, book);
    }

    @Test
    @DisplayName("test book getAll()")
    public void testGetAll() {
        assertEquals(2, bookDao.getAll().size());
        boolean isDeleted = bookDao.deleteById(2);
        assertEquals(1, bookDao.getAll().size());
    }
}
