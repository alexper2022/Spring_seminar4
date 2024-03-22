package ru.gb.springbootlesson3.entity;

import lombok.Data;
import lombok.Getter;
import org.springframework.context.annotation.Bean;

@Data
public class Book {
    private static long genId;

    @Getter
    private final long id;
    private final String name;

    public Book(String name) {
        id = genId++;
        this.name = name;
    }

}
