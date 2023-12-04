package com.batchImplementation.BatchProcessing.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@Slf4j
public class SchedulerConfig {

    private final Logger logger = LoggerFactory.getLogger(SchedulerConfig.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @Scheduled(cron = "30 */1 * * * *")
    public void scheduledJob() throws Exception {
        logger.info("Job scheduler started to work");
        jobLauncher.run(job, new JobParametersBuilder()
                .addLong("uniqueness", System.nanoTime()).toJobParameters());
        logger.info("Job scheduler stopped");
    }
}
