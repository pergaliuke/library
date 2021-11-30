package com.example.library.repositories;

import com.example.library.exceptions.DataException;
import com.example.library.models.TakeEvent;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class TakenBooksRepository {

    private final ObjectMapper objectMapper;

    private static final File FILE = new File("C:\\VK-WCC\\library\\src\\main\\resources\\data\\taken.json");

    public TakenBooksRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    private Set<TakeEvent> getTakeEvents() {
        try {
            return objectMapper.readValue(FILE, new TypeReference<>() {
            });
        } catch (IOException exception) {
            throw new DataException();
        }
    }

    public void addTakenEvent(TakeEvent takeEvent) {
        Set<TakeEvent> takeEvents = getTakeEvents();
        takeEvents.add(takeEvent);
        saveTakeEvents(takeEvents);
    }

    private void saveTakeEvents(Collection<TakeEvent> takeEvents) {
        try {
            objectMapper.writeValue(FILE, takeEvents);
        } catch (IOException ex) {
            throw new DataException();
        }
    }

    public boolean eventExistsByBookGuid(String bookGuid) {
        return getTakeEvents().stream().anyMatch(event -> event.getBookGuid().equals(bookGuid));
    }

    public int countByClientGui(String clientGuid) {
        return (int) getTakeEvents().stream().filter(event -> event.getClientGuid().equals(clientGuid)).count();
    }

    public Set<String> getBookGuids() {
        return getTakeEvents().stream().map(TakeEvent::getBookGuid).collect(Collectors.toSet());
    }

    public void deleteByBookGuid(String guid) {
        Set<TakeEvent> takeEvents = getTakeEvents();
        takeEvents.removeIf(event -> event.getBookGuid().equals(guid));
        saveTakeEvents(takeEvents);
    }
}
