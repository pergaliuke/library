package com.example.library.services;

import com.example.library.models.TakeEvent;
import com.example.library.repositories.TakenBooksRepository;
import org.springframework.stereotype.Service;

@Service
public class TakeService {

    private final TakenBooksRepository takenBooksRepository;

    public TakeService(TakenBooksRepository takenBooksRepository) {
        this.takenBooksRepository = takenBooksRepository;
    }

    public void takeBook(TakeEvent takeEvent) {
        takenBooksRepository.addTakenEvent(takeEvent);
    }
}
