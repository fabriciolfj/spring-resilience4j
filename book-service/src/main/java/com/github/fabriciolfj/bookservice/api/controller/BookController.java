package com.github.fabriciolfj.bookservice.api.controller;

import com.github.fabriciolfj.bookservice.api.dto.BookDTO;
import com.github.fabriciolfj.bookservice.domain.entity.Book;
import com.github.fabriciolfj.bookservice.domain.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book save(@RequestBody final BookDTO dto) {
        return bookService.create(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Book> findAll() {
        return bookService.findAll();
    }
}
