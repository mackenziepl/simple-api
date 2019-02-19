package com.example.simpleapi.mapper;

import com.example.simpleapi.domain.Book;
import com.example.simpleapi.domain.BookDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    public Book mapToBook(final BookDto bookDto) {
        return new Book(
                bookDto.getId(), bookDto.getAuthor(), bookDto.getTitle(), bookDto.getPublicationYear());
    }

    public BookDto mapToBookDto(final Book book) {
        return new BookDto(
                book.getId(),
                book.getAuthor(),
                book.getTitle(),
                book.getPublicationYear());
    }

    public List<BookDto> mapToBookDtoList(final List<Book> bookList) {
        return bookList.stream()
                .map(b -> new BookDto(b.getId(), b.getAuthor(), b.getTitle(), b.getPublicationYear()))
                .collect(Collectors.toList());
    }
}
