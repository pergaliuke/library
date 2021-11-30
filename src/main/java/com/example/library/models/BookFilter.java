package com.example.library.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookFilter {
    private String name;
    private String author;
    private String category;
    private String language;
    private String isbn;
    private Boolean available;
}
