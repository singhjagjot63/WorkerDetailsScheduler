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
    private int nextWorkerDetail;
    private List<WorkDetailsEntity> workDetailsEntityList;

    public RestWorkerDetails(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public WorkDetailsEntity read() throws Exception {
        if(this.workDetailsEntityList == null) {
            workDetailsEntityList = fetchWorkerDetails();
        }
        WorkDetailsEntity workDetails = null;
        if(nextWorkerDetail < workDetailsEntityList.size()) {
            workDetails = workDetailsEntityList.get(nextWorkerDetail);
            nextWorkerDetail++;
        } else {
            nextWorkerDetail = 0;
            workDetailsEntityList = null;
        }
        return workDetails;
    }

    public List<WorkDetailsEntity> fetchWorkerDetails() {
        ResponseEntity<WorkDetailsEntity[]> response = restTemplate.getForEntity(this.url, WorkDetailsEntity[].class);
        WorkDetailsEntity[] workerDetails = response.getBody();
        if(workerDetails != null) {
            return Arrays.asList(workerDetails);
        }
        return null;
    }
}
