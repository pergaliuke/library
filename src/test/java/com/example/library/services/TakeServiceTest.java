package com.example.library.services;

import com.example.library.models.Book;
import com.example.library.models.BookFilter;
import com.example.library.models.TakeEvent;
import java.time.LocalDate;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TakeServiceTest {

    @Autowired
    private TakeService takeService;

    @Autowired
    private BookService bookService;

    @Test
    void takeBook() {

        Book bookToTake = bookService.addBook(createBook());

        TakeEvent takeEvent = new TakeEvent();
        takeEvent.setBookGuid(bookToTake.getGuid());
        takeEvent.setClientGuid("36debe9f-033e-436f-898a-1d339bfe68f2");
        takeEvent.setReturnDate(LocalDate.now().plusDays(14));

        System.out.println(takeEvent);
        takeService.takeBook(takeEvent);

        Set<Book> filteredBooks = bookService.findByFilter(
          BookFilter.builder().available(false).build());
        assert filteredBooks.stream().anyMatch(book1 -> book1.equals(bookToTake));

        bookService.deleteByGuid(bookToTake.getGuid());
    }

    private static Book createBook() {
        return Book.builder()
          .author("Vernon Wycherley")
          .category("Classics")
          .isbn("844993228-9")
          .language("English")
          .publicationDate(LocalDate.parse("2000-01-01"))
          .name("Nun, The (La monja)")
          .build();
    }
}
