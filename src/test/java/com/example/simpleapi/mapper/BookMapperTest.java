package com.example.simpleapi.mapper;

import com.example.simpleapi.domain.Book;
import com.example.simpleapi.domain.BookDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BookMapperTest {

    @InjectMocks
    BookMapper bookMapper;

    @Test
    public void mapToBookTest() {
        //Given
        BookDto bookDto = new BookDto(1, "Test_author1", "Test_title1", 2010);
        Book book = new Book(1, "Test_author1", "Test_title1", 2010);

        //When
        Book theBook = bookMapper.mapToBook(bookDto);

        //Then
        assertEquals("Test_title1", theBook.getTitle());
    }

    @Test
    public void mapToBookDtoTest() {
        //Given
        BookDto bookDto = new BookDto(1, "Test_author1", "Test_title1", 2010);
        Book book = new Book(1, "Test_author1", "Test_title1", 2010);

        //When
        BookDto theBookDto = bookMapper.mapToBookDto(book);

        //Then
        assertEquals("Test_author1", theBookDto.getAuthor());
    }

    @Test
    public void mapToBookDtoListTest() {
        //Given
        List<BookDto> bookDtoList = new ArrayList<>();
        bookDtoList.add(new BookDto(1, "Test_author1", "Test_title1", 2010));
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1, "Test_author1", "Test_title1", 2010));

        //When
        List<BookDto> theBookDtoList = bookMapper.mapToBookDtoList(bookList);

        //Then
        assertEquals(1, theBookDtoList.size());
    }
}
