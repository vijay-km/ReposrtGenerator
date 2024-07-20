package com.example.accounts.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import com.example.accounts.service.impl.ScheduledTaskService;
@Configuration
@EnableScheduling
public class SchedulerConfig {

    @Autowired
    private ScheduledTaskService scheduledTaskService;

    @Scheduled(fixedRate = 120000) // Every 10 minutes
    public void scheduleFixedRateTask() {
        scheduledTaskService.processFiles();
    }


}
