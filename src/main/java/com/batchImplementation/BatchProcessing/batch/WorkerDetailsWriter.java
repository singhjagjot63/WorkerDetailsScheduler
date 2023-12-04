package com.batchImplementation.BatchProcessing.batch;

import com.batchImplementation.BatchProcessing.repository.WorkerDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

public class WorkerDetailsWriter implements ItemWriter {

    private final Logger logger = LoggerFactory.getLogger(WorkerDetailsWriter.class);

    @Autowired
    private WorkerDetailsRepository workerDetailsRepository;

    @Override
    public void write(Chunk chunk) throws Exception {
    logger.info("Writing: {} ", chunk.getItems().size());
    workerDetailsRepository.saveAll(chunk.getItems());
    }
}
