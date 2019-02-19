package com.example.simpleapi.controller;

import com.example.simpleapi.domain.BookDto;
import com.example.simpleapi.mapper.BookMapper;
import com.example.simpleapi.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private DbService dbService;
    @Autowired
    private BookMapper bookMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/books")
    public List<BookDto> getBooks() {
        return bookMapper.mapToBookDtoList(dbService.getAllBooks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/books/{id}")
    public BookDto getBook(@PathVariable int id) throws BookNotFoundException {
        return bookMapper.mapToBookDto(dbService.getBook(id).orElseThrow(BookNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/books", consumes = APPLICATION_JSON_VALUE)
    public BookDto updateBook(@RequestBody BookDto bookDto) {
        return bookMapper.mapToBookDto(dbService.saveBook(bookMapper.mapToBook(bookDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/books")
    public void createBook(@RequestBody BookDto bookDto) {
        dbService.saveBook(bookMapper.mapToBook(bookDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/books/{id}")
    public void deleteBook(@PathVariable int id) {
        dbService.deleteBook(id);
    }


}
