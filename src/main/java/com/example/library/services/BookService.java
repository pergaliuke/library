package com.example.library.services;

import com.example.library.exceptions.BookNotFoundException;
import com.example.library.models.Book;
import com.example.library.models.BookFilter;
import com.example.library.repositories.BookRepository;
import com.example.library.repositories.TakenBooksRepository;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final TakenBooksRepository takenBooksRepository;

    public BookService(BookRepository bookRepository,
      TakenBooksRepository takenBooksRepository) {
        this.bookRepository = bookRepository;
        this.takenBooksRepository = takenBooksRepository;
    }

    public Collection<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book addBook(Book book) {
        book.setGuid(UUID.randomUUID().toString());
        return bookRepository.save(book);
    }

    public Book getBookByGuid(String guid) {
        Book book = bookRepository.getByGuid(guid);
        if (book == null) {
            throw new BookNotFoundException();
        }
        return book;
    }

    public Set<Book> findByFilter(BookFilter bookFilter) {
        Set<Book> books = bookRepository.findByFilter(bookFilter);

        if (bookFilter.getAvailable() != null) {
            Set<String> takenGuids = takenBooksRepository.getBookGuids();
            return books.stream()
              .filter(book ->
                !bookFilter.getAvailable() && takenGuids.contains(book.getGuid())
                  || bookFilter.getAvailable() && !takenGuids.contains(book.getGuid()))
              .collect(Collectors.toSet());
        }

        return books;
    }

    public boolean deleteByGuid(String guid) {
        takenBooksRepository.deleteByBookGuid(guid);
        return bookRepository.deleteByGuid(guid);
    }
}
