import ca.purpose.edu.models.Author;
import ca.purpose.edu.repositories.AuthorRepositoryJpa;
import ca.purpose.edu.repositories.AuthorRepositoryJpaImpl;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@Sql("classpath:test-data.sql")
@EntityScan("ca.purpose.edu.models")
@EnableJpaRepositories(basePackages = {"ca.purpose.edu.*"})
@ContextConfiguration(classes = {AuthorRepositoryJpaImpl.class})
public class TestAuthorRepositoryJpaImpl {

    @Autowired
    private AuthorRepositoryJpa authorRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("test author findById()")
    public void testFindById() {
        Author authorFromJpa = authorRepository.findById(4L).get();
        Author authorExpected = entityManager.find(Author.class, 4L);
        assertEquals(authorFromJpa, authorExpected);
    }

    @Test
    @DisplayName("test author findAll()")
    public void testFindAll() {
        List<Author> allAuthorsFromJpa = authorRepository.findAll();
        assertThat(allAuthorsFromJpa).isNotNull().hasSize(6)
                .allMatch(a -> a.getAuthorId() != 0)
                .allMatch(a -> a.getAuthorName() != null);
    }

    @Test
    @DisplayName("test author save()")
    public void testSave() {
        Author author7 = new Author(7, "TestingAuthor7");
        authorRepository.save(author7);
        Author authorFromJpa = authorRepository.findById(7L).get();
        assertEquals(authorFromJpa, author7);
    }

    @Test
    @DisplayName("test author deleteById() and count()")
    public void testDeleteById() {
        assertEquals(6, authorRepository.count());
        authorRepository.deleteById(5);
        assertEquals(Optional.empty(), authorRepository.findById(5));
        assertEquals(5, authorRepository.count());
    }

}


