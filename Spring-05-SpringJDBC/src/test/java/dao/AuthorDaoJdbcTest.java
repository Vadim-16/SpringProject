package dao;

import ca.purpose.edu.Main;
import ca.purpose.edu.dao.AuthorDao;
import ca.purpose.edu.domain.Author;
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
public class AuthorDaoJdbcTest {

    @MockBean
    private Shell shell;

    @Autowired
    private AuthorDao authorDao;

    @Test
    @DisplayName("test author getById()")
    public void testGetById() {
        Author authorFromDao = authorDao.getById(2);
        Author author = new Author(2, "Gellar");
        assertEquals(authorFromDao, author);
    }

    @Test
    @DisplayName("test author insert()")
    public void testInsert() {
        Author author = new Author(4, "Moore");
        long insertNumber = authorDao.insert(author);
        Author authorFromDao = authorDao.getById(insertNumber);
        assertEquals(authorFromDao, author);
    }

    @Test
    @DisplayName("test author count()")
    public void testCount() {
        assertEquals(3, authorDao.count());
    }

    @Test
    @DisplayName("test author delete()")
    public void testDeleteById() {
        boolean isDeleted = authorDao.deleteById(1);
        assertThrows(EmptyResultDataAccessException.class, () -> authorDao.getById(1));
        assertEquals(2, authorDao.count());
    }

    @Test
    @DisplayName("test author update()")
    public void testUpdate() {
        Author author = new Author(3, "Gogol");
        boolean isUpdated = authorDao.update(author);
        Author authorFromDao = authorDao.getById(author.getAuthorId());
        assertEquals(authorFromDao, author);
    }

    @Test
    @DisplayName("test author getAll()")
    public void testGetAll() {
        assertEquals(2, authorDao.getAll().size());
        boolean isDeleted = authorDao.deleteById(2);
        assertEquals(1, authorDao.getAll().size());
    }
}