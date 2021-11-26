package com.example.library.models;

import java.time.LocalDate;

public interface BookDto {

    String getName();

    String getAuthor();

    String getCategory();

    String getLanguage();

    LocalDate getPublicationDate();

    String getIsbn();

    String getGuid();
}
