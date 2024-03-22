package ru.gb.springbootlesson3.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springbootlesson3.entity.Book;
import ru.gb.springbootlesson3.services.BookService;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("book")
@RequiredArgsConstructor
public class BookController {
    @Autowired
    private BookService bookService;

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable long id) {
        log.info("Поступил запрос на удаление книги: bookId={}", id);
        if (bookService.findById(id) != null) {
            try {
                bookService.deleteBook(id);
                return ResponseEntity.status(HttpStatus.OK).build();
            } catch (NoSuchElementException e) {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BookRequest issueRequest) {
        log.info("Поступил запрос на добавление книги: bookName={}", issueRequest.getBookName());
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addBook(issueRequest.getBookName()));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> infoBook(@PathVariable long id) {
        log.info("Поступил запрос на информацию о книге: bookId={}", id);
        if (bookService.findById(id) != null) {
            try {
                return ResponseEntity.status(HttpStatus.OK).body(bookService.findById(id));
            } catch (NoSuchElementException e) {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
