package ru.gb.springbootlesson3.controllers;

import lombok.Data;

@Data
public class BookRequest {
    private final long bookId;
    private final String bookName;
}
