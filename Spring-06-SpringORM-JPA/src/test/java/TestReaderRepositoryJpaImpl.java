import ca.purpose.edu.models.Reader;
import ca.purpose.edu.repositories.ReaderRepositoryJpa;
import ca.purpose.edu.repositories.ReaderRepositoryJpaImpl;
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
@ContextConfiguration(classes = {ReaderRepositoryJpaImpl.class})
public class TestReaderRepositoryJpaImpl {

    @Autowired
    private ReaderRepositoryJpa readerRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("test reader findById()")
    public void testFindById() {
        Reader readerFromJpa = readerRepository.findById(4L).get();
        Reader readerExpected = entityManager.find(Reader.class, 4L);
        assertEquals(readerFromJpa, readerExpected);
    }

    @Test
    @DisplayName("test reader findAll()")
    public void testFindAll() {
        List<Reader> allReadersFromJpa = readerRepository.findAll();
        assertThat(allReadersFromJpa).isNotNull().hasSize(6)
                .allMatch(r -> r.getReaderId() != 0)
                .allMatch(r -> r.getName() != null);
    }

    @Test
    @DisplayName("test reader save()")
    public void testSave() {
        Reader reader7 = new Reader(7, "TestingReader7");
        readerRepository.save(reader7);
        Reader readerFromJpa = entityManager.find(Reader.class, 7L);
        assertEquals(readerFromJpa, reader7);
    }

    @Test
    @DisplayName("test reader deleteById() and count()")
    public void testDeleteById() {
        assertEquals(6, readerRepository.count());
        readerRepository.deleteById(4);
        assertNull(entityManager.find(Reader.class, 4L));
        assertEquals(5, readerRepository.count());
    }

}


