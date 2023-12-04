package com.batchImplementation.BatchProcessing.batch;

import com.batchImplementation.BatchProcessing.entity.WorkDetailsEntity;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class FirstNameProcessor implements ItemProcessor<WorkDetailsEntity, WorkDetailsEntity> {

    private final Logger logger = LoggerFactory.getLogger(FirstNameProcessor.class);

    @Override
    public WorkDetailsEntity process(WorkDetailsEntity item) throws Exception {
        logger.info("Processing title {} ", item);
        item.setFirstName(item.getFirstName().toUpperCase());
        return item;
    }
}
