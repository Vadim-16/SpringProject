package dao;

import ca.purpose.edu.dao.BookDao;
import ca.purpose.edu.domain.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


//@ExtendWith(SpringExtension.class)
@JdbcTest
@Sql({"test-schema.sql", "test-data.sql"})
public class BookDaoJdbcTest {

//    @MockBean
//    private NamedParameterJdbcOperations namedJdbc;

    @Autowired
    private BookDao bookDao;

    @Test
    @DisplayName("test getById() method")
    public void testGetById(){
        Book book1 = bookDao.getById(1);
        assertEquals(book1.getBookId(), 1);
    }
}
