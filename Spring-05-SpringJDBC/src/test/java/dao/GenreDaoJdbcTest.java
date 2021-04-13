package dao;

import ca.purpose.edu.Main;
import ca.purpose.edu.dao.GenreDao;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = Main.class)
@ExtendWith(SpringExtension.class)
public class GenreDaoJdbcTest {

    @MockBean
    private Shell shell;

    @Autowired
    private GenreDao genreDao;

    @Test
    @DisplayName("test author getById()")
    public void testGetById() {
        Genre genreFromDao = genreDao.getById(3);
        Genre genre = new Genre(3, "Horror");
        assertEquals(genreFromDao, genre);
    }

    @Test
    @DisplayName("test author insert()")
    public void testInsert() {
        Genre genre = new Genre(4, "Science");
        long insertNumber = genreDao.insert(genre);
        Genre genreFromDao = genreDao.getById(insertNumber);
        assertEquals(genreFromDao, genre);
    }

    @Test
    @DisplayName("test author count()")
    public void testCount() {
        assertEquals(3, genreDao.count());
    }

    @Test
    @DisplayName("test author delete()")
    public void testDeleteById() {
        boolean isDeleted = genreDao.deleteById(1);
        assertThrows(EmptyResultDataAccessException.class, () -> genreDao.getById(1));
        assertEquals(2, genreDao.count());
    }

    @Test
    @DisplayName("test author update()")
    public void testUpdate() {
        Genre genre = new Genre(3, "Gogol");
        boolean isUpdated = genreDao.update(genre);
        Genre genreFromDao = genreDao.getById(genre.getGenreId());
        assertEquals(genreFromDao, genre);
    }

    @Test
    @DisplayName("test author getAll()")
    public void testGetAll() {
        assertEquals(2, genreDao.getAll().size());
        boolean isDeleted = genreDao.deleteById(2);
        assertEquals(1, genreDao.getAll().size());
    }
}

