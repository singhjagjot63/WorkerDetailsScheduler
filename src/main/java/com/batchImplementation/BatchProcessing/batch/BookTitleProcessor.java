package com.batchImplementation.BatchProcessing.batch;

import com.batchImplementation.BatchProcessing.entity.BookEntity;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class BookTitleProcessor implements ItemProcessor<BookEntity, BookEntity> {

    private final Logger logger = LoggerFactory.getLogger(BookTitleProcessor.class);

    @Override
    public BookEntity process(BookEntity item) throws Exception {
        logger.info("Processing title {} ", item);
        item.setFirstName(item.getFirstName().toUpperCase());
        return item;
    }
}
