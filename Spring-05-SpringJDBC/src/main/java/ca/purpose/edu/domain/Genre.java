package ca.purpose.edu.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@AllArgsConstructor
@Data
public class Genre {
    private long genreId;
    private String genre;
//    private Set<Book> books;
}
