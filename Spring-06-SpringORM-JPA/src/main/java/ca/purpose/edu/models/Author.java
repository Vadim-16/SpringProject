package ca.purpose.edu.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Authors")
public class Author {

    @Id
    @GeneratedValue
    private long authorId;

    @Column(name = "name", nullable = false, unique = true)
    private String authorName;
}
