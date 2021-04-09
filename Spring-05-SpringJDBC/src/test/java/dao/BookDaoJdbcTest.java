package dao;

import ca.purpose.edu.Main;
import ca.purpose.edu.dao.authordao.AuthorDaoJdbc;
import ca.purpose.edu.dao.bookdao.BookDao;
import ca.purpose.edu.dao.bookdao.BookDaoJdbc;
import ca.purpose.edu.dao.genredao.GenreDaoJdbc;
import ca.purpose.edu.domain.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = Main.class)
@ExtendWith(SpringExtension.class)
public class BookDaoJdbcTest {

    @Autowired
    private BookDaoJdbc bookDaoJdbc;

    @Test
    @DisplayName("test getById() method")
    public void testGetById(){
        Book book1 = bookDaoJdbc.getById(1);
        assertEquals(book1.getBookId(), 1);
    }
}
