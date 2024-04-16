package com.example.springboottest.services;

import com.example.springboottest.dtos.BookDto;
import com.example.springboottest.entities.Book;

import java.util.List;

public interface BookService {
    void save(BookDto dto);

    Book get(String isbn);

    List<Book> getAll();

    void remove(String isbn);
}
