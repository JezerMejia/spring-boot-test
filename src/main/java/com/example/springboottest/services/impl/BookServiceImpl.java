package com.example.springboottest.services.impl;

import com.example.springboottest.dtos.BookDto;
import com.example.springboottest.entities.Book;
import com.example.springboottest.services.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    public static final List<Book> bookList = new ArrayList<>();

    static {
        bookList.add(new Book("121212", "Harry Potter"));
        bookList.add(new Book("812301", "Padre Rico, Padre Pobre"));
        bookList.add(new Book("109123", "Viaje al centro de la tierra"));
    }

    @Override
    public void save(BookDto dto) {
        var book = get(dto.ISBN);
        if (book == null) {
            book = new Book(dto.ISBN, dto.title);
            bookList.add(book);
        } else {
            book.setTitle(dto.title);
        }
    }

    @Override
    public Book get(String isbn) {
        var book = bookList.stream().filter(v -> v.ISBN.equals(isbn)).findAny().orElse(null);
        return book;
    }

    @Override
    public List<Book> getAll() {
        return bookList;
    }

    @Override
    public void remove(String isbn) {
        var book = get(isbn);
        if (book != null) {
            bookList.remove(book);
        }
    }
}
