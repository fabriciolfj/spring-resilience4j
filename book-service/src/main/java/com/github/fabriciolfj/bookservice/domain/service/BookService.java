package com.github.fabriciolfj.bookservice.domain.service;

import com.github.fabriciolfj.bookservice.api.dto.BookDTO;
import com.github.fabriciolfj.bookservice.api.mapper.BookMapper;
import com.github.fabriciolfj.bookservice.domain.entity.Book;
import com.github.fabriciolfj.bookservice.domain.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper mapper;

    public Book create(final BookDTO dto) {
        return Optional.of(dto)
                .map(mapper::toEntity)
                .map(bookRepository::save)
                .orElseThrow(() -> new RuntimeException("Falha ao adicionar o livro " + dto));
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }


}
