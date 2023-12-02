package com.batchImplementation.BatchProcessing.repository;

import com.batchImplementation.BatchProcessing.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
