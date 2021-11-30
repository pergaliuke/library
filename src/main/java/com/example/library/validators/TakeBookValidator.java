package com.example.library.validators;

import com.example.library.exceptions.ValidationException;
import com.example.library.models.TakeEvent;
import com.example.library.repositories.BookRepository;
import com.example.library.repositories.TakenBooksRepository;
import java.time.LocalDate;
import java.time.Period;
import org.springframework.stereotype.Component;

@Component
public class TakeBookValidator {

    private static final int MAX_PERIOD = 60;
    private static final int MAX_BOOK_COUNT = 3;
    private final BookRepository bookRepository;
    private final TakenBooksRepository takenBooksRepository;

    public TakeBookValidator(BookRepository bookRepository,
      TakenBooksRepository takenBooksRepository) {
        this.bookRepository = bookRepository;
        this.takenBooksRepository = takenBooksRepository;
    }

    public void validate(TakeEvent takeEvent) {
        checkPeriod(takeEvent.getReturnDate());
        checkBookExists(takeEvent.getBookGuid());
        checkBookIsAvailable(takeEvent.getBookGuid());
        checkClientTakenBookCount(takeEvent.getClientGuid());
    }

    private void checkClientTakenBookCount(String clientGuid) {
        if (takenBooksRepository.countByClientGui(clientGuid) >= MAX_BOOK_COUNT) {
            throw new ValidationException("client has taken " + MAX_BOOK_COUNT + " or more books");
        }
    }

    private void checkBookIsAvailable(String bookGuid) {
        if (takenBooksRepository.eventExistsByBookGuid(bookGuid)) {
            throw new ValidationException("book guid=" + bookGuid + " is already taken");
        }
    }

    private void checkBookExists(String bookGuid) {
        if (!bookRepository.existsByGuid(bookGuid)) {
            throw new ValidationException("book guid=" + bookGuid + " does not exist");
        }
    }

    private void checkPeriod(LocalDate returnDate) {
        if (Period.between(LocalDate.now(), returnDate).getDays() > MAX_PERIOD) {
            throw new ValidationException("returnDate extends " + MAX_PERIOD + " days period");
        }
    }
}
