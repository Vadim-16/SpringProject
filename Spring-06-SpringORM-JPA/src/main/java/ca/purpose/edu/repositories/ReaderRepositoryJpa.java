package ca.purpose.edu.repositories;


import ca.purpose.edu.models.Reader;

import java.util.List;
import java.util.Optional;

public interface ReaderRepositoryJpa {
    Optional<Reader> findById(long readerId);

    List<Reader> findAll();

    Reader save(Reader reader);

    boolean deleteById(long readerId);

    long count();
}
