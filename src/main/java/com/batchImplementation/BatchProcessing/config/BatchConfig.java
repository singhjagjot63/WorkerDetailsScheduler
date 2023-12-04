package com.batchImplementation.BatchProcessing.config;

import com.batchImplementation.BatchProcessing.batch.SecondNameProcessor;
import com.batchImplementation.BatchProcessing.batch.FirstNameProcessor;
import com.batchImplementation.BatchProcessing.batch.WorkerDetailsWriter;
import com.batchImplementation.BatchProcessing.batch.RestWorkerDetails;
import com.batchImplementation.BatchProcessing.entity.WorkDetailsEntity;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class BatchConfig {

    @Bean
    public Job workerDetailsReaderJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("workerDetailsReadJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(chunkStep(jobRepository, transactionManager))
                .build();
    }

    @Bean
    public Step chunkStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("workerDetailsReaderStep", jobRepository)
                .<WorkDetailsEntity, WorkDetailsEntity>chunk(10, transactionManager)
    //            .reader(reader())
                .reader(restWorkerDetailsReader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @StepScope
    @Bean
    public ItemReader<WorkDetailsEntity> restWorkerDetailsReader() {
        return new RestWorkerDetails("http://localhost:8081/workerdetails", new RestTemplate());
    }

    @StepScope
    @Bean
    public ItemWriter<WorkDetailsEntity> writer() {
        return new WorkerDetailsWriter();
    }

    @StepScope
    @Bean
    public ItemProcessor<WorkDetailsEntity, WorkDetailsEntity> processor() {
        CompositeItemProcessor<WorkDetailsEntity, WorkDetailsEntity> processor = new CompositeItemProcessor<>();
        processor.setDelegates(List.of(new FirstNameProcessor(), new SecondNameProcessor()));
        return processor;
    }

    @Bean
    @StepScope
    public FlatFileItemReader<WorkDetailsEntity> reader() {

        return new FlatFileItemReaderBuilder<WorkDetailsEntity>()
                .name("workerDetailsReader")
                .resource(new ClassPathResource("worker_details.csv"))
                .delimited()
                .names(new String[]{"first_name","second_name","birth_year"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {
                    {
                        setTargetType(WorkDetailsEntity.class);
                    }
                })
                .build();
    }
}
