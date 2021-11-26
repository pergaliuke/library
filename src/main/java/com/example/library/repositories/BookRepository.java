package com.example.library.repositories;

import com.example.library.models.BookEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class BookRepository {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String FILE_PATH = "C:\\VK-WCC\\library\\src\\main\\resources\\data\\books.json";

    public List<BookEntity> findAll() throws IOException {
        return objectMapper.readValue(new File(FILE_PATH), new TypeReference<>() {
        });
    }
}
