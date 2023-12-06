package com.batchImplementation.BatchProcessing;


import com.batchImplementation.BatchProcessing.batch.RestWorkerDetails;
import com.batchImplementation.BatchProcessing.entity.WorkDetailsEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class RestWorkerDetailsTest {

    @Mock
    RestWorkerDetails restWorkerDetails;

    @Test
    public void testFetchWorkerDetails() {
        Mockito.when(restWorkerDetails.fetchWorkerDetails()).
                thenReturn(fetchWorkerDetailsList());
        List<WorkDetailsEntity> workDetailsEntityList = restWorkerDetails.fetchWorkerDetails();
        Assertions.assertNotNull(workDetailsEntityList);
    }

    private List<WorkDetailsEntity> fetchWorkerDetailsList() {
        List<WorkDetailsEntity> workDetailsEntityList = new ArrayList<>();
        WorkDetailsEntity workDetailsFirstObj = new WorkDetailsEntity();
        WorkDetailsEntity workDetailsSecondObj = new WorkDetailsEntity();
        workDetailsFirstObj.setFirstName("Tom");
        workDetailsFirstObj.setSecondName("Reddy");
        workDetailsFirstObj.setBirthYear(1898);
        workDetailsSecondObj.setFirstName("John");
        workDetailsSecondObj.setSecondName("Snow");
        workDetailsSecondObj.setBirthYear(1999);
        workDetailsEntityList.add(workDetailsFirstObj);
        workDetailsEntityList.add(workDetailsSecondObj);
        return workDetailsEntityList;
    }

}
