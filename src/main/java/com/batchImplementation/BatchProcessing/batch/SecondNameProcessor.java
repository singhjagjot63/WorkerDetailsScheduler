package com.batchImplementation.BatchProcessing.batch;

import com.batchImplementation.BatchProcessing.entity.WorkDetailsEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class SecondNameProcessor implements ItemProcessor<WorkDetailsEntity, WorkDetailsEntity> {

    private Logger logger = LoggerFactory.getLogger(SecondNameProcessor.class);

    @Override
    public WorkDetailsEntity process(WorkDetailsEntity item) throws Exception {
        logger.info("Process author: {}", item);
        item.setSecondName("By " + item.getSecondName());
        return item;
    }
}
