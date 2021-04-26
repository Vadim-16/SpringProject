import ca.purpose.edu.models.Author;
import ca.purpose.edu.models.Book;
import ca.purpose.edu.models.Genre;
import ca.purpose.edu.repositories.BookRepositoryJpa;
import ca.purpose.edu.repositories.BookRepositoryJpaImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@DataJpaTest
@Sql("classpath:test-data.sql")
@EntityScan("ca.purpose.edu.models")
@EnableJpaRepositories(basePackages = {"ca.purpose.edu.*"})
@ContextConfiguration(classes = {BookRepositoryJpaImpl.class})
public class TestBookRepositoryJpaImpl {

    @Autowired
    private BookRepositoryJpa boookRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("test book findById()")
    public void testFindById() {
        Book bookFromJpa = boookRepository.findById(4L).get();
        Book bookExpected = entityManager.find(Book.class, 4L);
        assertEquals(bookFromJpa, bookExpected);
    }

    @Test
    @DisplayName("test book findAll()")
    public void testFindAll() {
        List<Book> allBooksFromJpa = boookRepository.findAll();
        assertThat(allBooksFromJpa).isNotNull().hasSize(6)
                .allMatch(b -> b.getBookId() != 0)
                .allMatch(b -> b.getBookTitle() != null)
                .allMatch(b -> b.getAuthor() != null)
                .allMatch(b -> b.getGenre() != null);
    }

    @Test
    @DisplayName("test book save()")
    public void testSave() {
        Book book7 = new Book(7, "TestingBook7", entityManager.find(Author.class,4L),
                                                                entityManager.find(Genre.class, 5L));
        boookRepository.save(book7);
        Book bookFromJpa = entityManager.find(Book.class, 7L);
        assertEquals(bookFromJpa, book7);
    }

    @Test
    @DisplayName("test book deleteById() and count()")
    public void testDeleteById() {
        assertEquals(6, boookRepository.count());
        boookRepository.deleteById(5);
        assertNull(entityManager.find(Book.class, 5L));
        assertEquals(5, boookRepository.count());
    }

}


