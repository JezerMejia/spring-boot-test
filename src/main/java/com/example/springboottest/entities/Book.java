package com.example.springboottest.entities;

import com.example.springboottest.dtos.BookDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    public String ISBN;
    public String title;

    public static Book from(BookDto dto) {
        return new Book(dto.ISBN, dto.title);
    }
    public BookDto toDto() {
        return new BookDto(this.ISBN, this.title);
    }
}
