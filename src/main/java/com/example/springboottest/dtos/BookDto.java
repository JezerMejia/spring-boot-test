package com.example.springboottest.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BookDto {
    @Pattern(regexp = "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$", message = "Invalid ISBN")
    @NotEmpty(message = "ISBN is mandatory")
    public String ISBN;
    @NotEmpty(message = "Title is mandatory")
    public String title;
}
