package ru.gb.springbootlesson3.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.springbootlesson3.entity.Book;
import ru.gb.springbootlesson3.entity.Reader;
import ru.gb.springbootlesson3.repository.ReaderRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReaderService {
    @Autowired
    private ReaderRepository readerRepository;

    public Reader addReader(String name) {
        return readerRepository.addReader(name);
    }

    public Reader deleteReader(long id) {
        if (readerRepository.findById(id) == null) {
            log.info("Не удалось найти книгу с id " + id);
            throw new NoSuchElementException("Не удалось найти книгу с id " + id);
        }

        Reader reader = readerRepository.findById(id);
        readerRepository.deleteReader(reader);
        return reader;
    }

    public Reader findById(long id) {
        System.out.println(id);
        return readerRepository.findById(id);
    }

    public List<Reader> allReaders() {
        return readerRepository.allReaders();
    }
}
