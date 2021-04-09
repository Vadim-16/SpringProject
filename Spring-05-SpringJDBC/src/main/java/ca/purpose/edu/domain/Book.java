package ca.purpose.edu.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Book {
    private long bookId;
    private String bookTitle;
    private long authorId;
    private long genreId;
//    private Author author;
//    private Genre genre;
}
