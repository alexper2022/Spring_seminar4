package ru.gb.springbootlesson3.entity;

import lombok.Data;

import java.time.LocalDateTime;

/*
 * 3.1* В Issue поле timestamp разбить на 2: issued_at, returned_at - дата выдачи и дата возврата
 * 3.2* К ресурсу /issue добавить запрос
 * PUT /issue/{issueId},
 * который закрывает факт выдачи. (т.е. проставляет returned_at в Issue).
 * Замечание: возвращенные книги НЕ нужно учитывать при 2.1
 */
@Data
public class Issue {
    private static long genId;

    private final long id;
    private final long idReader;
    private final long idBook;
    private final LocalDateTime issuedAt; // дата выдачи
    private final LocalDateTime returnedAt; // дата возврата

    public Issue(long idReader, long idBook) {
        id = genId++;
        this.idBook = idBook;
        this.idReader = idReader;
        issuedAt = LocalDateTime.now();
        returnedAt = null;
    }
}
