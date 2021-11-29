package com.example.library.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {

//    @NotEmpty
    private String name;
//    @NotEmpty
    private String author;
//    @NotEmpty
    private String category;
//    @NotEmpty
    private String language;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publicationDate;
//    @Pattern(regexp = "(ISBN[-]*(1[03])*[ ]*(: ){0,1})*(([0-9Xx][- ]*){13}|([0-9Xx][- ]*){10})")
    private String isbn;
    private String guid;
}
