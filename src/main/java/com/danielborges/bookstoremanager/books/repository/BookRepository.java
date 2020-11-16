package com.danielborges.bookstoremanager.books.repository;

import com.danielborges.bookstoremanager.books.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
