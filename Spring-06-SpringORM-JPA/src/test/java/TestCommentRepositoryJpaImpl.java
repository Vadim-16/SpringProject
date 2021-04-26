import ca.purpose.edu.models.*;
import ca.purpose.edu.repositories.CommentRepositoryJpa;
import ca.purpose.edu.repositories.CommentRepositoryJpaImpl;
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
@ContextConfiguration(classes = {CommentRepositoryJpaImpl.class})
public class TestCommentRepositoryJpaImpl {

    @Autowired
    private CommentRepositoryJpa commentRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("test comment findById()")
    public void testFindById() {
        Comment commentFromJpa = commentRepository.findById(4L).get();
        Comment commentExpected = entityManager.find(Comment.class, 4L);
        assertEquals(commentFromJpa, commentExpected);
    }

    @Test
    @DisplayName("test comment findAll()")
    public void testFindAll() {
        List<Comment> allCommentsFromJpa = commentRepository.findAll();
        assertThat(allCommentsFromJpa).isNotNull().hasSize(6)
                .allMatch(b -> b.getCommentId() != 0)
                .allMatch(b -> b.getComment() != null)
                .allMatch(b -> b.getBook() != null)
                .allMatch(b -> b.getReader() != null);
    }

    @Test
    @DisplayName("test comment save()")
    public void testSave() {
        Comment comment7 = new Comment(7, "TestingComment7", entityManager.find(Reader.class,4L),
                entityManager.find(Book.class, 5L));
        commentRepository.save(comment7);
        Comment commentFromJpa = entityManager.find(Comment.class, 7L);
        assertEquals(commentFromJpa, comment7);
    }

    @Test
    @DisplayName("test comment deleteById() and count()")
    public void testDeleteById() {
        assertEquals(6, commentRepository.count());
        commentRepository.deleteById(5);
        assertNull(entityManager.find(Comment.class, 5L));
        assertEquals(5, commentRepository.count());
    }

}


