package ca.purpose.edu.repositories;


import ca.purpose.edu.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepositoryJpa {
    Optional<Comment> findById(long commentId);

    List<Comment> findAll();

    List<Comment> findAllByBookId(long bookId);

    Comment save(Comment comment);

    boolean deleteById(long commentId);

    long count();
}
