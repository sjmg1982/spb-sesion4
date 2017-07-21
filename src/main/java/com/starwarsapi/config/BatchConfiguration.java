package com.starwarsapi.config;

import com.starwarsapi.tasker.HelloWorldTasker;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sjmg on 21/07/17.
 */
//@Configuration
//@EnableBatchProcessing
public class BatchConfiguration {

    @Bean
    public Step helloWorldStep(StepBuilderFactory stepBuilderFactory,
                               HelloWorldTasker helloWorldTasker) {
        return stepBuilderFactory
            .get("helloWorldStep")
            .tasklet(helloWorldTasker)
            .build();
    }

    @Bean
    public Job helloWorldJob(JobBuilderFactory jobBuilderFactory,
                             Step helloWorldStep) {
        return jobBuilderFactory
            .get("helloWorldJob")
            .incrementer(new RunIdIncrementer())
            .start(helloWorldStep)
            .build();
    }

}
