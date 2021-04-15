package dao;

import ca.purpose.edu.dao.AuthorDao;
import ca.purpose.edu.dao.AuthorDaoJdbc;
import ca.purpose.edu.domain.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@JdbcTest
@Sql("classpath:test-data.sql")
@ContextConfiguration(classes = {AuthorDaoJdbc.class})
public class AuthorDaoJdbcTest {

    @Autowired
    private AuthorDao authorDao;

    @Test
    @DisplayName("test author getById()")
    public void testGetById() {
        Author authorFromDao = authorDao.getById(5);
        Author author = new Author(5, "testingAuthor2");
        assertEquals(authorFromDao, author);
    }

    @Test
    @DisplayName("test author insert()")
    public void testInsert() {
        Author author = new Author(7, "Moore");
        long insertNumber = authorDao.insert(author);
        author.setAuthorId(insertNumber);
        Author authorFromDao = authorDao.getById(insertNumber);
        assertEquals(authorFromDao, author);
    }

    @Test
    @DisplayName("test author count()")
    public void testCount() {
        assertEquals(6, authorDao.count());
    }

    @Test
    @DisplayName("test author delete()")
    public void testDeleteById() {
        boolean isDeleted = authorDao.deleteById(4);
        assertThrows(EmptyResultDataAccessException.class, () -> authorDao.getById(4));
        assertEquals(5, authorDao.count());
    }

    @Test
    @DisplayName("test author update()")
    public void testUpdate() {
        Author author = new Author(6, "Gogol");
        boolean isUpdated = authorDao.update(author);
        Author authorFromDao = authorDao.getById(author.getAuthorId());
        assertEquals(authorFromDao, author);
    }

    @Test
    @DisplayName("test author getAll()")
    public void testGetAll() {
        assertEquals(6, authorDao.getAll().size());
        boolean isDeleted = authorDao.deleteById(4);
        assertEquals(5, authorDao.getAll().size());
    }
}