package com.example.simpleapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "BOOKS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private int id;

    @Column(name = "Author", nullable = false)
    private String author;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "PublicationYear")
    private int publicationYear;
}
