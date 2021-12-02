package com.example.library.repositories;

import com.example.library.exceptions.DataException;
import com.example.library.models.Book;
import com.example.library.models.BookFilter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class BookRepository {

    private final ObjectMapper objectMapper;

    private static final File FILE = new File("C:\\VK-WCC\\library\\src\\main\\resources\\data\\books.json");

    public BookRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    private Set<Book> getBooks() {
        try {
            return objectMapper.readValue(FILE, new TypeReference<>() {
            });
        } catch (IOException exception) {
            System.out.println(Arrays.toString(exception.getStackTrace()));
            throw new DataException();
        }
    }

    public Book save(Book book) {
        Set<Book> books = getBooks();
        books.add(book);
        writeBooks(books);

        return book;
    }

    private void writeBooks(Collection<Book> books) {
        try {
            objectMapper.writeValue(FILE, books);
        } catch (IOException ex) {
            throw new DataException();
        }
    }

    public boolean existsByGuid(String bookGuid) {
        return getBooks().stream().anyMatch(book -> book.getGuid().equals(bookGuid));
    }

    public Book getByGuid(String guid) {
        return getBooks().stream().filter(book -> book.getGuid().equals(guid)).findAny().orElse(null);
    }

    public Set<Book> findByFilter(BookFilter bookFilter) {
        return getBooks().stream()
          .filter(book -> bookFilter.getAuthor() == null || book.getAuthor().equals(bookFilter.getAuthor()))
          .filter(book -> bookFilter.getCategory() == null || book.getCategory().equals(bookFilter.getCategory()))
          .filter(book -> bookFilter.getIsbn() == null || book.getIsbn().equals(bookFilter.getIsbn()))
          .filter(book -> bookFilter.getLanguage() == null || book.getLanguage().equals(bookFilter.getLanguage()))
          .filter(book -> bookFilter.getName() == null || book.getName().equals(bookFilter.getName()))
          .collect(Collectors.toSet());
    }

    public boolean deleteByGuid(String guid) {
        Set<Book> books = getBooks();
        boolean result = books.removeIf(book -> book.getGuid().equals(guid));
        writeBooks(books);

        return result;
    }
}
