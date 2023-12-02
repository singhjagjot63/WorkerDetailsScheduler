package com.batchImplementation.BatchProcessing.batch;

import com.batchImplementation.BatchProcessing.entity.BookEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class BookAuthorProcessor implements ItemProcessor<BookEntity, BookEntity> {

    private Logger logger = LoggerFactory.getLogger(BookAuthorProcessor.class);

    @Override
    public BookEntity process(BookEntity item) throws Exception {
        logger.info("Process author: {}", item);
        item.setSecondName("By " + item.getSecondName());
        return item;
    }
}
