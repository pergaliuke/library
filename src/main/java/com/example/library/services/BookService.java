package com.example.library.services;

import com.example.library.models.Book;
import com.example.library.repositories.BookRepository;
import java.util.Collection;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Collection<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book addBook(Book book) {
        book.setGuid(UUID.randomUUID().toString());
        return bookRepository.addBook(book);
    }
}
