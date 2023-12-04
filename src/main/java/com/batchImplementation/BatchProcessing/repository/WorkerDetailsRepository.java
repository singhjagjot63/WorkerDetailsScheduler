package com.batchImplementation.BatchProcessing.repository;

import com.batchImplementation.BatchProcessing.entity.WorkDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerDetailsRepository extends JpaRepository<WorkDetailsEntity, Long> {
}
