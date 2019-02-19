package com.example.simpleapi.controller;

import com.example.simpleapi.domain.Book;
import com.example.simpleapi.domain.BookDto;
import com.example.simpleapi.mapper.BookMapper;
import com.example.simpleapi.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private BookMapper bookMapper;

    @Test
    public void shouldGetBooks() throws Exception {
        //Given
        BookDto bookDto1 = new BookDto(1, "Test_author1", "Test_title1", 2012);
        BookDto bookDto2 = new BookDto(2, "Test_author2", "Test_title2", 2014);
        List<BookDto> bookDtoList = new ArrayList<>();
        bookDtoList.add(bookDto1);
        bookDtoList.add(bookDto2);

        when(bookMapper.mapToBookDtoList(ArgumentMatchers.anyList())).thenReturn(bookDtoList);

        //When & Then
        mockMvc.perform(get("/api/books").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].author", is("Test_author1")))
                .andExpect(jsonPath("$[0].title", is("Test_title1")))
                .andExpect(jsonPath("$[0].publicationYear", is(2012)));
    }

    @Test
    public void shouldGetBook() throws Exception {
        //Given
        BookDto bookDto1 = new BookDto(1, "Test_author1", "Test_title1", 2012);
        BookDto bookDto2 = new BookDto(2, "Test_author2", "Test_title2", 2014);
        List<BookDto> bookDtoList = new ArrayList<>();
        bookDtoList.add(bookDto1);
        bookDtoList.add(bookDto2);

        Book book = new Book();

        when(bookMapper.mapToBookDto(any())).thenReturn(bookDto1);
        when(dbService.getBook(ArgumentMatchers.anyInt())).thenReturn(Optional.ofNullable(book));

        //When & Then
        mockMvc.perform(get("/api/books/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.author", is("Test_author1")))
                .andExpect(jsonPath("$.title", is("Test_title1")))
                .andExpect(jsonPath("$.publicationYear", is(2012)));

    }

    @Test
    public void shouldCreateBook() throws Exception {
        //Given
        BookDto bookDto = new BookDto(1, "Test_author1", "Test_title1", 2012);
        Book book = new Book(1, "Test_author1", "Test_title1", 2012);

        bookMapper.mapToBook(bookDto);
        dbService.saveBook(book);

        //When & Then
        verify(bookMapper, times(1)).mapToBook(bookDto);
        verify(dbService, times(1)).saveBook(book);
    }

    @Test
    public void shouldUpdateBook() throws Exception {
        //Given
        BookDto bookDto = new BookDto(1, "Test_author1", "Test_title1", 2012);
        Book book = new Book();

        when(bookMapper.mapToBook(any())).thenReturn(book);
        when(dbService.saveBook(book)).thenReturn(any());
        when(bookMapper.mapToBookDto(book)).thenReturn(bookDto);

        Gson gson = new Gson();
        String jsonContext = gson.toJson(bookDto);

        //When & Then
        mockMvc.perform(put("/api/books").content(jsonContext).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.author", is("Test_author1")))
                .andExpect(jsonPath("$.title", is("Test_title1")))
                .andExpect(jsonPath("$.publicationYear", is(2012)));
    }

    @Test
    public void shouldDeleteBook() throws Exception {
        //Given
        Book book = new Book(1, "Test_author1", "Test_title1", 2012);
        int id = book.getId();

        //When
        dbService.deleteBook(id);

        //Then
        verify(dbService, times(1)).deleteBook(id);
    }
}
