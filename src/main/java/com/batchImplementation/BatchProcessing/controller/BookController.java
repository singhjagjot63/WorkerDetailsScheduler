package com.batchImplementation.BatchProcessing.controller;

import com.batchImplementation.BatchProcessing.batch.BookWriter;
import com.batchImplementation.BatchProcessing.entity.BookEntity;
import com.batchImplementation.BatchProcessing.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<BookEntity> getAll() {
        logger.info("Handling find all");
        return bookRepository.findAll();
    }
}
