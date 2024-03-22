package ru.gb.springbootlesson3.controllers;
/*
 * 1.2 Реализовать контроллер по управлению читателями (аналогично контроллеру с книгами из 1.1)
 * Реализовать контроллер по управлению читателями с ручками:
 * GET    /reader/{id} - получить описание читателя,
 * DELETE /reader/{id} - удалить читателя,
 * POST   /reader - создать читателя
 */

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springbootlesson3.entity.Issue;
import ru.gb.springbootlesson3.entity.Reader;
import ru.gb.springbootlesson3.services.IssueService;
import ru.gb.springbootlesson3.services.ReaderService;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("reader")
//@RequiredArgsConstructor
@AllArgsConstructor
public class ReadreController {
//    @Autowired
    private ReaderService readerService;
//    @Autowired
    private IssueService issueService;

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteReader(@PathVariable long id) {
        log.info("Поступил запрос на удаление читателя: readerId={}", id);
        try {
            readerService.deleteReader(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Reader> createReader(@RequestBody ReaderRequest readerRequest) {
        log.info("Поступил запрос на добавление читателя: readerName={}", readerRequest.getReaderName());
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(readerService.addReader(readerRequest.getReaderName()));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Reader> infoReader(@PathVariable long id) {
        log.info("Поступил запрос на информацию о читателе: readerId={}", id);
        if (readerService.findById(id) != null) {
            try {
                return ResponseEntity.status(HttpStatus.CREATED).body(readerService.findById(id));
            } catch (NoSuchElementException e) {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

/*
 * 2.2 В сервис читателя добавить ручку
 * GET /reader/{id}/issue - вернуть список всех выдачей для данного читателя
 */

    @GetMapping("{id}/issue")
    public ResponseEntity<List<Issue>> findBookIssueReader(@PathVariable long id) {
        log.info("Поступил запрос на информацию о читателе: readerId={}", id);
        if (readerService.findById(id) != null) {
            try {
                return ResponseEntity.status(HttpStatus.CREATED).body(issueService.findIssueForReader(id));
            } catch (NoSuchElementException e) {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.notFound().build();
    }


}
