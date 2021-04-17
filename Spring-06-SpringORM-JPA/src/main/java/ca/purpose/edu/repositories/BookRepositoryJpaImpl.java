package ca.purpose.edu.repositories;

import ca.purpose.edu.models.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryJpaImpl implements BookRepositoryJpa {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    @Override
    public List<Book> findAll() {
        return entityManager.createNamedQuery("select b from books b", Book.class).getResultList();
    }

    @Override
    public Book save(Book book) {
        if (book.getBookId() <= 0) {
            entityManager.persist(book);
            return book;
        } else return entityManager.merge(book);
    }
}
