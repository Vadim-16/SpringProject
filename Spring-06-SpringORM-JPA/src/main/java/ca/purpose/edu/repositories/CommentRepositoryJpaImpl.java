package ca.purpose.edu.repositories;

import ca.purpose.edu.models.Comment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class CommentRepositoryJpaImpl implements CommentRepositoryJpa {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Comment> findById(long commentId) {
        return Optional.ofNullable(entityManager.find(Comment.class, commentId));
    }

    @Override
    public List<Comment> findAll() {
        return entityManager.createQuery("select c from Comment c", Comment.class).getResultList();
    }

    @Override
    public Comment save(Comment comment) {
        if (comment.getCommentId() <= 0) {
            entityManager.persist(comment);
            return comment;
        } else return entityManager.merge(comment);
    }

    @Override
    public boolean deleteById(long commentId) {
        try {
            entityManager.remove(findById(commentId).orElse(null));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public long count() {
        return entityManager.createQuery("select count(c.commentId) from Comment c", Long.class).getSingleResult();
    }
}
