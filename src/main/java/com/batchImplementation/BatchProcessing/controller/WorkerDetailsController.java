package com.batchImplementation.BatchProcessing.controller;

import com.batchImplementation.BatchProcessing.entity.WorkDetailsEntity;
import com.batchImplementation.BatchProcessing.repository.WorkerDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/workerdetails")
public class WorkerDetailsController {

    private final Logger logger = LoggerFactory.getLogger(WorkerDetailsController.class);

    @Autowired
    private WorkerDetailsRepository workerDetailsRepository;

    @GetMapping
    public List<WorkDetailsEntity> getAll() {
        logger.info("Handling find all");
        return workerDetailsRepository.findAll();
    }
}
