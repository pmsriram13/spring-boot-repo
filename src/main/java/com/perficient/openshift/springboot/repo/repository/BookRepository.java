package com.perficient.openshift.springboot.repo.repository;

import com.perficient.openshift.springboot.repo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByIsbn(String isbn);

    List<Book> findByTitleContaining(String title);
}

