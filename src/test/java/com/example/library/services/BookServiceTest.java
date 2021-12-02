package com.example.library.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.library.models.Book;
import com.example.library.models.BookFilter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    private static List<Book> books = new ArrayList<>();

    @BeforeAll
    static void beforeAll() {
        books.addAll(List.of(
          createBook("Vernon Wycherley", "Classics", "844993228-9", "Some classical title", "English",
            LocalDate.parse("2000-01-01")),
          createBook("Jenelle Bushel", "Horror", "020697162-1", "Very spooky title", "Lithuanian",
            LocalDate.parse("2010-01-01"))));
    }

    @AfterAll
    static void afterAll() {
        books.clear();
    }

    @Test
    void addBook() {
        int bookCountBeforeAddition = getBookCount();
        Book bookToAdd = books.get(0);

        Book result = bookService.addBook(bookToAdd);
        int bookCountAfterAddition = getBookCount();

        assertNotNull(result);
        assertNotNull(result.getGuid());
        assertEquals(bookToAdd.getAuthor(), result.getAuthor());
        assertEquals(bookToAdd.getIsbn(), result.getIsbn());
        assertEquals(bookToAdd.getCategory(), result.getCategory());
        assertEquals(bookToAdd.getLanguage(), result.getLanguage());
        assertEquals(bookToAdd.getName(), result.getName());
        assertEquals(bookToAdd.getPublicationDate(), result.getPublicationDate());
        assertEquals(bookCountBeforeAddition + 1, bookCountAfterAddition);

        bookService.deleteByGuid(result.getGuid());
    }

    private static Book createBook(String author, String category, String isbn, String name, String language,
      LocalDate publicationDate) {
        Book book = new Book();
        book.setIsbn(isbn);
        book.setAuthor(author);
        book.setCategory(category);
        book.setName(name);
        book.setLanguage(language);
        book.setPublicationDate(publicationDate);
        return book;
    }

    @Test
    void getBookByGuid() {
        Book bookToFind = bookService.addBook(books.get(1));

        Book result = bookService.getBookByGuid(bookToFind.getGuid());

        assertEquals(bookToFind, result);

        bookService.deleteByGuid(result.getGuid());
    }

    @Test
    void findByFilter() {
        Book bookClassic = bookService.addBook(books.get(0));
        Book bookHorror = bookService.addBook(books.get(1));
        String categoryToFilter = bookClassic.getCategory();

        Set<Book> classicBooks = bookService.findByFilter(
          BookFilter.builder().category(categoryToFilter).build());

        classicBooks.stream().map(Book::getCategory).forEach(category -> assertEquals(categoryToFilter, category));

        bookService.deleteByGuid(bookClassic.getGuid());
        bookService.deleteByGuid(bookHorror.getGuid());
    }

    @Test
    void deleteByGuid() {
        Book bookToDelete = bookService.addBook(books.get(1));
        int bookCountBeforeDeletion = getBookCount();

        boolean result = bookService.deleteByGuid(bookToDelete.getGuid());
        int bookCountAfterDeletion = getBookCount();

        assert result;
        assertEquals(bookCountBeforeDeletion - 1, bookCountAfterDeletion);

    }

    private int getBookCount() {
        return bookService.findByFilter(BookFilter.builder().build()).size();
    }
}
