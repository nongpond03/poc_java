package com.example.demo.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.support.JdbcTransactionManager;

//@Configuration
//@Import(DataSourceConfiguration.class)
public class HelloWorldJob {

    @Bean
    public Step step(JobRepository jobRepository, JdbcTransactionManager transactionManager) {
        return new StepBuilder("step", jobRepository).tasklet((contribution, chunkContext) -> {
            System.out.println("Hello world!");
            return RepeatStatus.FINISHED;
        }, transactionManager).build();
    }

    @Bean
    public Job job(JobRepository jobRepository, Step step) {
        return new JobBuilder("job", jobRepository).start(step).build();
    }
}
