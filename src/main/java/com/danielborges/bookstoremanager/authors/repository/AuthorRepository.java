package com.danielborges.bookstoremanager.authors.repository;

import com.danielborges.bookstoremanager.authors.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
