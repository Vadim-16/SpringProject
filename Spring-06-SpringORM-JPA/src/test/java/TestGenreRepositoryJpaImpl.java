import ca.purpose.edu.models.Genre;
import ca.purpose.edu.repositories.GenreRepositoryJpa;
import ca.purpose.edu.repositories.GenreRepositoryJpaImpl;
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
@ContextConfiguration(classes = {GenreRepositoryJpaImpl.class})
public class TestGenreRepositoryJpaImpl {

    @Autowired
    private GenreRepositoryJpa genreRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("test genre findById()")
    public void testFindById() {
        Genre genreFromJpa = genreRepository.findById(4L).get();
        Genre genreExpected = entityManager.find(Genre.class, 4L);
        assertEquals(genreFromJpa, genreExpected);
    }

    @Test
    @DisplayName("test genre findAll()")
    public void testFindAll() {
        List<Genre> allGenresFromJpa = genreRepository.findAll();
        assertThat(allGenresFromJpa).isNotNull().hasSize(6)
                .allMatch(g -> g.getGenreId() != 0)
                .allMatch(g -> g.getGenre() != null);
    }

    @Test
    @DisplayName("test genre save()")
    public void testSave() {
        Genre genre7 = new Genre(7, "TestingGenre7");
        genreRepository.save(genre7);
        Genre genreFromJpa = entityManager.find(Genre.class, 7L);
        assertEquals(genreFromJpa, genre7);
    }

    @Test
    @DisplayName("test genre deleteById() and count()")
    public void testDeleteById() {
        assertEquals(6, genreRepository.count());
        genreRepository.deleteById(6);
        assertNull(entityManager.find(Genre.class, 6L));
        assertEquals(5, genreRepository.count());
    }

}


