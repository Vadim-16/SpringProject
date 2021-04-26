package ca.purpose.edu.repositories;

import ca.purpose.edu.models.Genre;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class GenreRepositoryJpaImpl implements GenreRepositoryJpa {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Genre> findById(long id) {
        return Optional.ofNullable(entityManager.find(Genre.class, id));
    }

    @Override
    public List<Genre> findAll() {
        return entityManager.createQuery("select g from Genre g", Genre.class).getResultList();
    }

    @Override
    public Genre save(Genre genre) {
        if (genre.getGenreId() <= 0) {
            entityManager.persist(genre);
            return genre;
        } else return entityManager.merge(genre);
    }

    @Override
    public boolean deleteById(long genreId) {
        entityManager.remove(findById(genreId).orElse(null));
        return true;
    }

    @Override
    public long count() {
        return entityManager.createQuery("select count(g.genreId) from Genre g", Long.class).getSingleResult();
    }
}
