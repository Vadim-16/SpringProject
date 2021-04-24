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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookId;

    @Column(name = "title", nullable = false, unique = true)
    private String bookTitle;

    @OneToOne(targetEntity = Author.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "author_Id")
    private Author author;

    @OneToOne(targetEntity = Genre.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "genre_Id")
    private Genre genre;

}
