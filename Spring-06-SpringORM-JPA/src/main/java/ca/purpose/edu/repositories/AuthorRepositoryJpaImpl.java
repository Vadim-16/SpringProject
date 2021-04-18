package ca.purpose.edu.repositories;

import ca.purpose.edu.models.Author;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class AuthorRepositoryJpaImpl implements AuthorRepositoryJpa {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Author> findById(long id) {
        return Optional.ofNullable(entityManager.find(Author.class, id));
    }

    @Override
    public List<Author> findAll() {
        return entityManager.createQuery("select a from Author a", Author.class).getResultList();
    }

    @Override
    public Author save(Author author) {
        if (author.getAuthorId() <= 0) {
            entityManager.persist(author);
            return author;
        } else return entityManager.merge(author);
    }

    @Override
    public boolean deleteById(long authorId) {
        try {
            entityManager.remove(findById(authorId).orElse(null));
            return true;
        } catch (Exception E) {
            return false;
        }
    }

    @Override
    public long count() {
        return entityManager.createQuery("select count(a.authorId) from Author a", Long.class).getSingleResult();
    }
}
