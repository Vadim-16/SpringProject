package ca.purpose.edu.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Author {
    private long authorId;
    private String authorName;
//    private Set<Book> books;
}
