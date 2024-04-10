package com.example.springboottest.controllers;

import com.example.springboottest.dtos.BookDto;
import com.example.springboottest.entities.Book;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LibraryController {
    public static final List<Book> books = new ArrayList<>();

    static {
        books.add(new Book("121212", "Harry Potter"));
        books.add(new Book("812301", "Padre Rico, Padre Pobre"));
        books.add(new Book("109123", "Viaje al centro de la tierra"));
    }

    @GetMapping("/library-all")
    public String getAllBooks(Model model) {
        model.addAttribute("books", books);
        System.out.println("Data: ");
        System.out.println(books);
        return "books";
    }

    @GetMapping("/form")
    public String getForm(Model model) {
        return "form";
    }

    @PostMapping("/save-book")
    ResponseEntity<String> saveBook(@Valid BookDto book) {
        books.add(Book.from(book));
        return ResponseEntity.ok("Book added successfully");
    }
}