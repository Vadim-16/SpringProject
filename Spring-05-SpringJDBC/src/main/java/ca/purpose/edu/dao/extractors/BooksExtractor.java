package ca.purpose.edu.dao.extractors;

import ca.purpose.edu.domain.Author;
import ca.purpose.edu.domain.Book;
import ca.purpose.edu.domain.Genre;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class BooksExtractor implements ResultSetExtractor<Set<Book>> {

    @Override
    public Set<Book> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Set<Book> books = new HashSet<>();
        while (resultSet.next()) {
            long bookId = resultSet.getLong("bookId");
            boolean contains = books.stream().anyMatch(book1 -> book1.getBookId() == bookId);
            if (!contains){
                String bookTitle = resultSet.getString("bookTitle");
                long authorId = resultSet.getLong("authorId");
                String authorName = resultSet.getString("authorName");
                long genreId1 = resultSet.getLong("genreId");
                String genre1 = resultSet.getString("genre");
                Book book = new Book(bookId, bookTitle, new Author(authorId, authorName), new Genre(genreId1, genre1));
                books.add(book);
            }
        }
        return books;
    }
}
