package com.example.springboottest.controllers;

import com.example.springboottest.dtos.BookDto;
import com.example.springboottest.entities.Book;
import com.example.springboottest.services.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LibraryRestController {
    private BookService bookService;

    public LibraryRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/api/books", produces = "application/json")
    public List<BookDto> getBooks() {
        return bookService.getAll().stream().map(Book::toDto).toList();
    }

    @PostMapping(value = "/api/books", consumes = "application/json")
    public ResponseEntity<String> addBook(@Valid @RequestBody BookDto bookDto) {
        bookService.save(bookDto);
        return ResponseEntity.ok("Added Book");
    }

    @DeleteMapping(value = "/api/books/{isbn}")
    public ResponseEntity<String> removeBook(@PathVariable String isbn) {
        bookService.remove(isbn);
        return ResponseEntity.ok("Removed book");
    }
}
