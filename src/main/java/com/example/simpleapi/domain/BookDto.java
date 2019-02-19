package com.example.simpleapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BookDto {
    private int id;
    private String author;
    private String title;
    private int publicationYear;
}
