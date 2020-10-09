package com.github.fabriciolfj.bookservice.domain.repository;

import com.github.fabriciolfj.bookservice.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
