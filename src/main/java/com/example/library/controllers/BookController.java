package com.example.library.controllers;

import com.example.library.services.BookService;
import java.io.IOException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
public class BookController {

    @GetMapping
    public String getBooks () throws IOException {
        return "Hello";
    }

}
