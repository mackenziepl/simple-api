package com.example.simpleapi.repository;

import com.example.simpleapi.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    @Override
    List<Book> findAll();
}
