package com.tips.batch.scheduler;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler
{
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    Job processJob;
    
    @Bean
  //@Scheduled(cron = "*/10 * * * * *")
    @Scheduled(cron = "0 30 * ? * *")
    public void batchRun() throws Exception
    {
        System.out.println("------------------------------- SpringBatch Start!!! -------------------------------");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월dd일 HH시mm분ss초");
        Calendar         calendar         = Calendar.getInstance();
        String           startTime        = simpleDateFormat.format(calendar.getTime());
        
        JobParameters jobParameters = new JobParametersBuilder().addString("StartTime", startTime)
                                                                .toJobParameters();

        jobLauncher.run(processJob, jobParameters);
    }
}
