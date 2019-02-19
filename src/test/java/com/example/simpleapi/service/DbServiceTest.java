package com.example.simpleapi.service;

import com.example.simpleapi.controller.BookController;
import com.example.simpleapi.domain.Book;
import com.example.simpleapi.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookController bookController;

    @Test
    public void shouldGetAllBooks() {
        //Given
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "Test_author1", "Test_title1", 2010));

        when(bookRepository.findAll()).thenReturn(books);

        //When
        List<Book> theList = dbService.getAllBooks();

        //Then
        assertEquals(1, theList.size());
    }

    @Test
    public void shouldGetBook() {
        //Given
        Book book = new Book(1, "Test_author1", "Test_title1", 2010);
        int id = 1;
        when(bookRepository.findById(id)).thenReturn(Optional.ofNullable(book));

        //When
        Optional<Book> theList = dbService.getBook(id);
        int idTheList = theList.get().getId();

        //Then
        assertEquals(1, idTheList);
    }

    @Test
    public void shouldSaveBook() {
        //Given
        Book book = new Book(1, "Test_author1", "Test_title1", 2010);
        when(bookRepository.save(any())).thenReturn(book);

        //When
        Book theBook = dbService.saveBook(book);

        //Then
        assertEquals("Test_title1", theBook.getTitle());
    }

    @Test
    public void shouldDeleteBook() {
        //Given
        Book book = new Book(1, "Test_author1", "Test_title1", 2010);
        int id = book.getId();
        //When
        bookController.deleteBook(id);

        //Then
        verify(bookController, times(1)).deleteBook(id);
    }
}
