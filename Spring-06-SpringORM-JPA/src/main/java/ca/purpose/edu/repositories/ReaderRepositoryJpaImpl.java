package ca.purpose.edu.repositories;

import ca.purpose.edu.models.Book;
import ca.purpose.edu.models.Reader;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ReaderRepositoryJpaImpl implements ReaderRepositoryJpa {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Reader> findById(long readerId) {
        return Optional.ofNullable(entityManager.find(Reader.class, readerId));
    }

    @Override
    public List<Reader> findAll() {
        return entityManager.createQuery("select r from Reader r", Reader.class).getResultList();
    }

    @Override
    public Reader save(Reader reader) {
        if (reader.getReaderId() <= 0) {
            entityManager.persist(reader);
            return reader;
        } else return entityManager.merge(reader);
    }

    @Override
    public boolean deleteById(long readerId) {
        try {
            entityManager.remove(findById(readerId).orElse(null));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public long count() {
        return entityManager.createQuery("select count(r.readerId) from Reader r", Long.class).getSingleResult();
    }
}
