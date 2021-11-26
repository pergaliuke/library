package com.example.library.services;

import com.example.library.models.BookDto;
import com.example.library.repositories.BookRepository;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDto> findAll() throws IOException {
       return bookRepository.findAll().stream().map(item->(BookDto) item).collect(Collectors.toList());
    }
}
