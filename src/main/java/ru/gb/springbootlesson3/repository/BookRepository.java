package ru.gb.springbootlesson3.repository;

import org.springframework.stereotype.Repository;
import ru.gb.springbootlesson3.entity.Book;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {
    private List<Book> list = new ArrayList<>();

    public BookRepository() {
        list.add(new Book("Война и мир"));
        list.add(new Book("Мастер и Маргарита"));
        list.add(new Book("Приключения Буратино"));
    }

    public void deleteBook(Book book){
        list.remove(book);
    }
    public Book findById(long id){
        return list.stream().filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }
    public Book addBook(String name){
        Book newBook = new Book(name);
        list.add(newBook);
        return newBook;
    }

    public List<Book> allBooks() {
        return list;
    }
}
