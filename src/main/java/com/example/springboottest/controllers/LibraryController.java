package com.example.springboottest.controllers;

import com.example.springboottest.dtos.BookDto;
import com.example.springboottest.services.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LibraryController {
    private BookService bookService;

    public LibraryController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/library-all")
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.getAll());
        return "books";
    }

    @GetMapping("/form")
    public String getForm(Model model) {
        return "form";
    }

    @PostMapping("/save-book")
    ResponseEntity<String> saveBook(@Valid BookDto book) {
        bookService.save(book);
        return ResponseEntity.ok("Book added successfully");
    }
}