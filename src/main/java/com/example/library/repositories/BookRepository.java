package com.example.library.repositories;

import com.example.library.exceptions.DataException;
import com.example.library.models.Book;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class BookRepository {

    private ObjectMapper objectMapper;

    public BookRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    private static final File FILE = new File("C:\\VK-WCC\\library\\src\\main\\resources\\data\\books.json");

    public Collection<Book> findAll() {
        return readBooks();
    }

    private List<Book> readBooks() {
        try {
            return objectMapper.readValue(FILE, new TypeReference<>() {
            });
        } catch (IOException exception) {
            System.out.println(Arrays.toString(exception.getStackTrace()));
            throw new DataException();
        }
    }

    public Book addBook(Book book) {
        List<Book> books = readBooks();
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
}
