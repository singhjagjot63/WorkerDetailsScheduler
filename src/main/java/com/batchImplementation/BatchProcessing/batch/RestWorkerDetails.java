package com.batchImplementation.BatchProcessing.batch;

import com.batchImplementation.BatchProcessing.entity.WorkDetailsEntity;
import org.springframework.batch.item.ItemReader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class RestWorkerDetails implements ItemReader<WorkDetailsEntity> {

    private final String url;
    private final RestTemplate restTemplate;
    private int nextBook;
    private List<WorkDetailsEntity> workDetailsEntityList;

    public RestWorkerDetails(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public WorkDetailsEntity read() throws Exception {
        if(this.workDetailsEntityList == null) {
            workDetailsEntityList = fetchBooks();
        }
        WorkDetailsEntity book = null;
        if(nextBook< workDetailsEntityList.size()) {
            book = workDetailsEntityList.get(nextBook);
            nextBook++;
        } else {
            nextBook = 0;
            workDetailsEntityList = null;
        }
        return book;
    }

    private List<WorkDetailsEntity> fetchBooks() {
        ResponseEntity<WorkDetailsEntity[]> response = restTemplate.getForEntity(this.url, WorkDetailsEntity[].class);
        WorkDetailsEntity[] books = response.getBody();
        if(books != null) {
            return Arrays.asList(books);
        }
        return null;
    }
}
