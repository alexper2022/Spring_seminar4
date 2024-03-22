package ru.gb.springbootlesson3.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springbootlesson3.entity.Issue;
import ru.gb.springbootlesson3.services.IssueService;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("issue")
@RequiredArgsConstructor
public class IssueController {

    @Autowired
    private IssueService service;

    @PostMapping
    public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest issueRequest) {
        log.info("Поступил запрос на выдачу: readerId={}, bookId={}"
                , issueRequest.getReaderId(), issueRequest.getBookId());
        if (service.isReaderIssueBook(issueRequest.getReaderId())) {
            return ResponseEntity.status(409).build();
        } else {
            try {
                return ResponseEntity.status(HttpStatus.CREATED).body(service.createIssue(issueRequest));
            } catch (NoSuchElementException e) {
                return ResponseEntity.notFound().build();
            }
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Issue> findBookInIssues(@PathVariable long id) {
        log.info("Поступил запрос на проверку выдачи книги: bookId={}", id);
        if (service.findBookInIssue(id) != null) {
            try {
                return ResponseEntity.status(HttpStatus.OK).body(service.findBookInIssue(id));
            } catch (NoSuchElementException e) {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
}
