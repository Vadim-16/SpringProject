package dao;

import ca.purpose.edu.dao.GenreDaoJdbc;
import ca.purpose.edu.models.Genre;
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
@ContextConfiguration(classes = {GenreDaoJdbc.class})
public class GenreDaoJdbcTest {

    @Autowired
    private GenreDaoJdbc genreDao;

    @Test
    @DisplayName("test genre getById()")
    public void testGetById() {
        Genre genreFromDao = genreDao.getById(6);
        Genre genre = new Genre(6, "testingGenre3");
        assertEquals(genreFromDao, genre);
    }

    @Test
    @DisplayName("test genre insert()")
    public void testInsert() {
        Genre genre = new Genre(7, "Science");
        long insertNumber = genreDao.insert(genre);
        genre.setGenreId(insertNumber);
        Genre genreFromDao = genreDao.getById(insertNumber);
        assertEquals(genreFromDao, genre);
    }

    @Test
    @DisplayName("test genre count()")
    public void testCount() {
        assertEquals(6, genreDao.count());
    }

    @Test
    @DisplayName("test genre delete()")
    public void testDeleteById() {
        boolean isDeleted = genreDao.deleteById(4);
        assertThrows(EmptyResultDataAccessException.class, () -> genreDao.getById(4));
        assertEquals(5, genreDao.count());
    }

    @Test
    @DisplayName("test genre update()")
    public void testUpdate() {
        Genre genre = new Genre(5, "Gogol");
        boolean isUpdated = genreDao.update(genre);
        Genre genreFromDao = genreDao.getById(genre.getGenreId());
        assertEquals(genreFromDao, genre);
    }

    @Test
    @DisplayName("test genre getAll()")
    public void testGetAll() {
        assertEquals(6, genreDao.getAll().size());
        boolean isDeleted = genreDao.deleteById(4);
        assertEquals(5, genreDao.getAll().size());
    }
}

