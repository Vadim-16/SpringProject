package ca.purpose.edu.dao.bookdao;

import ca.purpose.edu.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations namedJdbc;

    @Autowired
    public BookDaoJdbc(NamedParameterJdbcOperations namedJdbc) {
        this.namedJdbc = namedJdbc;
    }


    @Override
    public Book getById(long bookId) {
        final MapSqlParameterSource map = new MapSqlParameterSource("bookId", bookId);
        return namedJdbc.queryForObject("select * from books where bookId = :bookId", map, new BookMapper());
    }


    @Override
    public int count() {
        return namedJdbc.queryForObject("select count(*) from books", (Map<String, ?>) null, Integer.class);
    }

    @Override
    public void insert(Book book) {
        final MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("bookId", book.getBookId());
        map.addValue("bookTitle", book.getBookTitle());
        map.addValue("authorId", book.getAuthorId());
        map.addValue("genreId", book.getGenreId());
        namedJdbc.update("insert into books (bookId, `bookTitle`, authorId, genreId)" +
                " VALUES (:bookId, :bookTitle, :authorId, :genreId)", map);
    }

    @Override
    public List<Book> getAll() {
        return namedJdbc.query("select * from books", new BookMapper());
    }

    @Override
    public void deleteById(long bookId) {
        final MapSqlParameterSource map = new MapSqlParameterSource("bookId", bookId);
        namedJdbc.update("delete from books where bookId = :bookId", map);
    }

    @Override
    public void update(Book book) {
        final MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("bookId", book.getBookId());
        map.addValue("bookTitle", book.getBookTitle());
        map.addValue("authorId", book.getAuthorId());
        map.addValue("genreId", book.getAuthorId());
        namedJdbc.update("update books set bookTitle = :bookTitle, authorId = :authorId, genreId = :genreId where bookId = :bookId", map);
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
