package com.example.library.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @NotEmpty
    private String name;
    @NotEmpty
    private String author;
    @NotEmpty
    private String category;
    @NotEmpty
    private String language;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publicationDate;
    @Pattern(regexp = "(ISBN[-]*(1[03])*[ ]*(: )?)*(([0-9Xx][- ]*){13}|([0-9Xx][- ]*){10})")
    private String isbn;
    private String guid;
}
