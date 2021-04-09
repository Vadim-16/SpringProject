package ca.purpose.edu.dao.bookdao;

import ca.purpose.edu.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
public class BookDaoJdbc implements BookDao {
    private final String SQL_GET_BY_ID = "select * from books where bookId = :bookId";
    private final String SQL_COUNT = "select count(*) from books";
    private final String SQL_INSERT = "insert into books (`bookTitle`, authorId, genreId) VALUES (?, ?, ?)";
    private final String SQL_GET_ALL = "select * from books";
    private final String SQL_DELETE_BY_ID = "delete from books where bookId = :bookId";
    private final String SQL_UPDATE = "update books set bookTitle = :bookTitle, authorId = :authorId, genreId = :genreId where bookId = :bookId";

    private final NamedParameterJdbcOperations namedJdbc;

    @Autowired
    public BookDaoJdbc(NamedParameterJdbcOperations namedJdbc) {
        this.namedJdbc = namedJdbc;
    }

    @Override
    public Book getById(long bookId) {
        final MapSqlParameterSource map = new MapSqlParameterSource("bookId", bookId);
        return namedJdbc.queryForObject(SQL_GET_BY_ID, map, new BookMapper());
    }


    @Override
    public int count() {
        return namedJdbc.queryForObject(SQL_COUNT, (Map<String, ?>) null, Integer.class);
    }

    @Override
    public long insert(Book book) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT, new String[]{"bookId"});
            ps.setString(1, book.getBookTitle());
            ps.setLong(2, book.getAuthorId());
            ps.setLong(3, book.getGenreId());
            return ps;
        };
        namedJdbc.getJdbcOperations().update(preparedStatementCreator, keyHolder);
        return (long) keyHolder.getKey();
    }

    @Override
    public List<Book> getAll() {
        return namedJdbc.query(SQL_GET_ALL, new BookMapper());
    }

    @Override
    public void deleteById(long bookId) {
        namedJdbc.update(SQL_DELETE_BY_ID, new MapSqlParameterSource("bookId", bookId));
    }

    @Override
    public void update(Book book) {
        namedJdbc.update(SQL_UPDATE, new BeanPropertySqlParameterSource(book));
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long bookId = resultSet.getLong("bookId");
            String bookTitle = resultSet.getString("bookTitle");
            long authorId = resultSet.getLong("authorId");
            long genreId = resultSet.getLong("genreId");
            return new Book(bookId, bookTitle, authorId, genreId);
        }
    }
}
