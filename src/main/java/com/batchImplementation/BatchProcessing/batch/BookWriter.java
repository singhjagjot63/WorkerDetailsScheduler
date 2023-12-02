package com.batchImplementation.BatchProcessing.batch;

import com.batchImplementation.BatchProcessing.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

public class BookWriter implements ItemWriter {

    private final Logger logger = LoggerFactory.getLogger(BookWriter.class);

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void write(Chunk chunk) throws Exception {
    logger.info("Writing: {} ", chunk.getItems().size());
    bookRepository.saveAll(chunk.getItems());
    }
}
