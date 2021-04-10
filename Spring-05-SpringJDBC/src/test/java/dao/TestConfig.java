package dao;

import ca.purpose.edu.dao.bookdao.BookDao;
import ca.purpose.edu.dao.bookdao.BookDaoJdbc;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@TestConfiguration
public class TestConfig {

//    @Bean
//    public BookDao bookDao(){
//        return new BookDaoJdbc(new NamedParameterJdbcTemplate());
//    }
}
