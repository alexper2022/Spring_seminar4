package ru.gb.springbootlesson3.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.springbootlesson3.controllers.BookRequest;
import ru.gb.springbootlesson3.entity.Book;
import ru.gb.springbootlesson3.repository.BookRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book addBook(String name) {
        return bookRepository.addBook(name);
    }

    public Book deleteBook(long id) {
        if (bookRepository.findById(id) == null) {
            log.info("Не удалось найти книгу с id " + id);
            throw new NoSuchElementException("Не удалось найти книгу с id " + id);
        }

        Book book = bookRepository.findById(id);
        bookRepository.deleteBook(book);
        return book;
    }

    public Book findById(long id) {
        return bookRepository.findById(id);
    }

    public List<Book> allBooks() {
        return bookRepository.allBooks();
    }
}
