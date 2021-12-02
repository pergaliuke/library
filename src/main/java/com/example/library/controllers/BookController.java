package com.example.library.controllers;

import com.example.library.models.Book;
import com.example.library.models.BookFilter;
import com.example.library.services.BookService;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public Set<Book> getBooks(BookFilter bookFilter){
        return bookService.findByFilter(bookFilter);
    }

    @PostMapping
    public Book addBook(@RequestBody @Valid Book book) {
        return bookService.addBook(book);
    }

    @GetMapping("/{guid}")
    public Book getBook(@PathVariable String guid){
        return bookService.getBookByGuid(guid);
    }

    @DeleteMapping("/{guid}")
    public boolean deleteBook(@PathVariable String guid){
        return bookService.deleteByGuid(guid);
    }
}
