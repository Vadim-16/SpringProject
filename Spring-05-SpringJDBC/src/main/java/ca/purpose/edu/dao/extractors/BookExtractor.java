package ca.purpose.edu.dao.extractors;

import ca.purpose.edu.domain.Author;
import ca.purpose.edu.domain.Book;
import ca.purpose.edu.domain.Genre;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookExtractor implements ResultSetExtractor<Book> {

    @Override
    public Book extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        long bookId = resultSet.getLong("bookId");
        String bookTitle = resultSet.getString("bookTitle");

        long authorId = resultSet.getLong("authorId");
        String authorName = resultSet.getString("authorName");

        long genreId = resultSet.getLong("genreId");
        String genre = resultSet.getString("genre");

        return new Book(bookId, bookTitle, new Author(authorId, authorName), new Genre(genreId,genre));
    }
}
