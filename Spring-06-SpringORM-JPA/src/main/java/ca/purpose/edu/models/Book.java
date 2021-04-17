package ca.purpose.edu.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Books")
public class Book {

    @Id
    @GeneratedValue
    private long bookId;

    @Column(name = "title", nullable = false, unique = true)
    private String bookTitle;

    @OneToMany
    @JoinColumn(name = "author_authorId")
    private Author author;

    @OneToMany(targetEntity = Genre.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "genre_genreId")
    private Genre genre;
}
