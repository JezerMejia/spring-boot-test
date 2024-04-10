package com.example.springboottest.controllers;

import com.example.springboottest.dtos.BookDto;
import com.example.springboottest.entities.Book;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LibraryRestController {

    @GetMapping(value = "/books", produces = "application/json")
    public List<BookDto> getBooks() {
        return LibraryController.books.stream().map(Book::toDto).toList();
    }

    @PostMapping(value = "/books", consumes = "application/json")
    public ResponseEntity<String> addBook(@Valid @RequestBody BookDto bookDto) {
        if (LibraryController.books.stream().anyMatch(v -> v.ISBN.equals(bookDto.ISBN))) {
            return new ResponseEntity<>("Book with same ISBN already exists", HttpStatus.CONFLICT);
        }
        LibraryController.books.add(Book.from(bookDto));
        return ResponseEntity.ok("Added Book");
    }
}
