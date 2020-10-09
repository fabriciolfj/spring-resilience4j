package com.github.fabriciolfj.bibliotecaservice.api;

import com.github.fabriciolfj.bibliotecaservice.domain.model.BookModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.fabriciolfj.bibliotecaservice.domain.service.BibliotecaService;
import java.util.List;

@RestController
@RequestMapping("/bibliotecas")
@RequiredArgsConstructor
public class BibliotecaController {

    private final BibliotecaService service;

    @PostMapping
    public String addBook(@RequestBody BookModel bookModel){
        return service.addBook(bookModel);
    }

    @PostMapping ("/ratelimit")
    public String addBookwithRateLimit(@RequestBody BookModel book){
        return service.addBookwithRateLimit(book);
    }

    @GetMapping
    public List<BookModel> getSellersList() {
        return service.getBookList();
    }

    @GetMapping ("/bulkhead")
    public List<BookModel> getSellersListBulkhead() {
        return service.getBookListBulkhead();
    }

}
