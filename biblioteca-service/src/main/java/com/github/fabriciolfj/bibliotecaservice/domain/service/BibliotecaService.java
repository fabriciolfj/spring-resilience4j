package com.github.fabriciolfj.bibliotecaservice.domain.service;


import java.awt.print.Book;
import java.util.List;

import com.github.fabriciolfj.bibliotecaservice.domain.model.BookModel;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@Slf4j
@RequiredArgsConstructor
public class BibliotecaService {

    private final RestTemplate restTemplate;

    @CircuitBreaker(name = "add", fallbackMethod = "fallbackForaddBook")
    public String addBook(final BookModel book) {
        log.error("Inside addbook call book service. ");
        String response = restTemplate.postForObject("/books", book, String.class);
        return response;
    }

    @RateLimiter(name = "add", fallbackMethod = "fallbackForRatelimitBook")
    public String addBookwithRateLimit(final BookModel book) {
        log.error("Inside addbook Ratelimit. ");
        String response = restTemplate.postForObject("/books", book, String.class);
        log.error("Inside addbook, cause ");
        return response;
    }

    @Retry(name = "get", fallbackMethod = "fallbackRetry")
    public List<BookModel> getBookList() {
        log.info("Inside getBookList");
        return restTemplate.getForObject("/books", List.class);
    }

    @Bulkhead(name = "get", type = Bulkhead.Type.SEMAPHORE, fallbackMethod = "fallbackBulkhead")
    public List<BookModel> getBookListBulkhead() {
        log.error("Inside getBookList bulk head");
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return restTemplate.getForObject("/books", List.class);
    }

    public String fallbackForaddBook(BookModel book, Throwable t) {
        log.error("Inside circuit breaker fallbackForaddBook, cause - {}", t.toString());
        return "Inside circuit breaker fallback method. Some error occurred while calling service for adding book";
    }

    public String fallbackForRatelimitBook(BookModel book, Throwable t) {
        log.error("Inside fallbackForRatelimitBook, cause - {}", t.toString());
        return "Inside fallbackForRatelimitBook method. Some error occurred while calling service for adding book";
    }

    public List<BookModel> fallbackRetry(Throwable t) {
        log.error("Inside fallbackRetry, cause - {}", t.toString());
        var book = BookModel.builder()
                .id(0l)
                .name("Default retry")
                .build();

        return List.of(book);
    }
    public List<BookModel> fallbackBulkhead(Throwable t) {
        log.error("Inside fallbackBulkhead, cause - {}", t.toString());
        var book = BookModel.builder()
                .id(0l)
                .name("DefaultBulkhead")
                .build();

        return List.of(book);
    }
}
