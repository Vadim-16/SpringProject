package ca.purpose.edu.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Author {
    private long authorId;
    private String authorName;
}
