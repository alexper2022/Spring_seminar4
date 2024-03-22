package ru.gb.springbootlesson3.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.springbootlesson3.entity.Book;
import ru.gb.springbootlesson3.entity.Issue;
import ru.gb.springbootlesson3.entity.Reader;
import ru.gb.springbootlesson3.services.BookService;
import ru.gb.springbootlesson3.services.IssueService;
import ru.gb.springbootlesson3.services.ReaderService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("ui")
@AllArgsConstructor
public class UiController {

    private IssueService issueService;
    private ReaderService readerService;
    private BookService bookService;

    @GetMapping("/issues")
    public String issuesAll(Model model) {
        List<Issue> issuesList = issueService.allIssues();
        model.addAttribute("issues", issuesList);
        return "issues";
    }

    @GetMapping("/readers")
    public String readersAll(Model model) {
        List<Reader> readersList = readerService.allReaders();
        model.addAttribute("readers", readersList);
        return "readers";
    }

    @GetMapping("/books")
    public String booksAll(Model model) {
        List<Book> booksList = bookService.allBooks();
        model.addAttribute("books", booksList);
        return "books";
    }
}
