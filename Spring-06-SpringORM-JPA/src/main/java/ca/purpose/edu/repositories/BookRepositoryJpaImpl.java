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
public class BookRepositoryJpaImpl implements BookRepositoryJpa {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Book> findById(long bookId) {
        return Optional.ofNullable(entityManager.find(Book.class, bookId));
    }

    @Override
    public List<Book> findAll() {
        return entityManager.createQuery("select b from Book b", Book.class).getResultList();
    }

    @Override
    public List<Reader> findCommentsByBookId(long bookId) {
        return entityManager.createQuery("select c from Book b INNER JOIN Reader r where b.bookId = :bookId", Reader.class).setParameter("bookId", bookId).getResultList();
    }

    @Override
    public Book save(Book book) {
        if (book.getBookId() <= 0) {
            entityManager.persist(book);
            return book;
        } else return entityManager.merge(book);
    }

    @Override
    public boolean deleteById(long bookId) {
        try {
            entityManager.remove(findById(bookId).orElse(null));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public long count() {
        return entityManager.createQuery("select count(b.bookId) from book b", Long.class).getSingleResult();
    }
}
