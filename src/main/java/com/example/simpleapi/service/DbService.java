package com.example.simpleapi.service;

import com.example.simpleapi.domain.Book;
import com.example.simpleapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBook(final int id) {
        return bookRepository.findById(id);
    }

    public Book saveBook(final Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(final int id) {
        bookRepository.deleteById(id);
    }
}
