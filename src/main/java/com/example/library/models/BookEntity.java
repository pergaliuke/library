package com.example.library.models;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BookEntity {

    private String name;
    private String author;
    private String category;
    private String language;
    private LocalDate publicationDate;
    private String isbn;
    private String guid;
    private String takenByUserGuid;
}
