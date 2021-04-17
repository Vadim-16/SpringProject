package ca.purpose.edu.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {
    private long bookId;
    private String bookTitle;
    private Author author;
    private Genre genre;
}
