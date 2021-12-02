package com.example.library.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookFilter {
    private String name;
    private String author;
    private String category;
    private String language;
    private String isbn;
    private Boolean available;
}
