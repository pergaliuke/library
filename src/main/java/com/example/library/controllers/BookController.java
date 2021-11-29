package com.example.library.controllers;

import com.example.library.models.Book;
import com.example.library.services.BookService;
import java.util.Collection;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Collection<Book> getBooks(){
        return bookService.findAll();
    }

    @PostMapping
    public Book addBook(@RequestBody @Valid Book book) {
        return bookService.addBook(book);
    }

}
