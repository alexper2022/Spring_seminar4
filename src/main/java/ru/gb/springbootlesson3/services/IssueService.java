package ru.gb.springbootlesson3.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.gb.springbootlesson3.controllers.IssueRequest;
import ru.gb.springbootlesson3.entity.Issue;
import ru.gb.springbootlesson3.repository.BookRepository;
import ru.gb.springbootlesson3.repository.IssueRepository;
import ru.gb.springbootlesson3.repository.ReaderRepository;

import java.util.List;
import java.util.NoSuchElementException;
/*
 * 2.1 В сервис IssueService добавить проверку, что у пользователя на руках нет книг.
 * Если есть - не выдавать книгу (статус ответа - 409 Conflict)
 */
//---------------------------------------------------------------------------------------
/*
 * 3.3** Пункт 2.1 расширить параметром, сколько книг может быть на руках у пользователя.
 * Должно задаваться в конфигурации (параметр application.issue.max-allowed-books).
 * Если параметр не задан - то использовать значение 1.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class IssueService {
    private final BookRepository bookRepository;
    private final IssueRepository issueRepository;
    private final ReaderRepository readerRepository;

    public Issue createIssue(IssueRequest request){
        if (bookRepository.findById(request.getBookId()) == null){
            log.info("Не удалось найти книгу с id " + request.getBookId());
            throw new NoSuchElementException("Не удалось найти книгу с id " + request.getBookId());
        }
        if (readerRepository.findById(request.getReaderId()) == null){
            log.info("Не удалось найти читателя с id " + request.getReaderId());
            throw new NoSuchElementException("Не удалось найти читателя с id " + request.getReaderId());
        }

        Issue issue = new Issue(request.getReaderId(), request.getBookId());
        issueRepository.createIssue(issue);
        return issue;
    }
//    public Issue returnedBook(long id){
//        if (bookRepository.findById(id) == null){
//            log.info("Не удалось найти книгу с id " + id);
//            throw new NoSuchElementException("Не удалось найти книгу с id " + id);
//        }
//        issueRepository.findById(id);
//        return null;
//
//    }

    public Issue findBookInIssue(long bookId) {
        return issueRepository.findBookInIssue(bookId);
    }
    public boolean isReaderIssueBook(long id){
        return !issueRepository.findByReader(id).isEmpty();
    }
    public List<Issue> findIssueForReader(long id){
        return issueRepository.findByReader(id);
    }

    public List<Issue> allIssues() {
        return issueRepository.allIssues();
    }
}
