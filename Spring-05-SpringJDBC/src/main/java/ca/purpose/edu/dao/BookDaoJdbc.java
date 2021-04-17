package ca.purpose.edu.dao;

import ca.purpose.edu.models.Author;
import ca.purpose.edu.models.Book;
import ca.purpose.edu.models.Genre;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
public class BookDaoJdbc implements BookDao {
    private final String SQL_COUNT = "select count(*) from books";
    private final String SQL_INSERT = "insert into books (bookTitle, authorId, genreId) VALUES (:bookTitle, :authorId, :genreId)";
    private final String SQL_UPDATE = "update books set bookTitle = :bookTitle, authorId = :authorId, genreId = :genreId where bookId = :bookId";
    private final String SQL_DELETE_BY_ID = "delete from books where bookId = :bookId";
    private final String SQL_GET_BY_ID = "select * from ((books " +
            "inner join authors on books.authorID = authors.authorId)" +
            "inner join genres on books.genreId = genres.genreId)" +
            "where bookId = :bookId";
    private final String SQL_GET_ALL = "select * from ((books " +
            "inner join authors on books.authorID = authors.authorId)" +
            "inner join genres on books.genreId = genres.genreId)";

    private final NamedParameterJdbcOperations namedJdbc;

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

//        One way to auto-generate Id:
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("bookTitle", book.getBookTitle());
        map.addValue("authorId", book.getAuthor().getAuthorId());
        map.addValue("genreId", book.getGenre().getGenreId());
        namedJdbc.update(SQL_INSERT, map, keyHolder);

//        Another way to auto-generate Id:
//        PreparedStatementCreator preparedStatementCreator = connection -> {
//            PreparedStatement ps = connection.prepareStatement(SQL_INSERT, new String[]{"bookId"});
//            ps.setString(1, book.getBookTitle());
//            ps.setLong(2, book.getAuthor().getAuthorId());
//            ps.setLong(3, book.getGenre().getGenreId());
//            return ps;
//        };
//        namedJdbc.getJdbcOperations().update(preparedStatementCreator, keyHolder);

        return (long) keyHolder.getKey();
    }

    @Override
    public List<Book> getAll() {
        return namedJdbc.query(SQL_GET_ALL, new BookMapper());
    }

    @Override
    public boolean deleteById(long bookId) {
        int update = namedJdbc.update(SQL_DELETE_BY_ID, new MapSqlParameterSource("bookId", bookId));
        return update == 1;
    }

    @Override
    public boolean update(Book book) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("bookId", book.getBookId());
        map.addValue("bookTitle", book.getBookTitle());
        map.addValue("authorId", book.getAuthor().getAuthorId());
        map.addValue("genreId", book.getGenre().getGenreId());
        int update = namedJdbc.update(SQL_UPDATE, map);
        return update == 1;
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long bookId = resultSet.getLong("bookId");
            String bookTitle = resultSet.getString("bookTitle");
            long authorId = resultSet.getLong("authorId");
            String authorName = resultSet.getString("authorName");
            long genreId = resultSet.getLong("genreId");
            String genre = resultSet.getString("genre");
            return new Book(bookId, bookTitle, new Author(authorId, authorName), new Genre(genreId, genre));
        }
    }
}
