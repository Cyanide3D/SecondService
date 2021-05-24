package com.example.SecondService;

import com.example.SecondService.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ContextRefreshListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private SchedulerService schedulerService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        schedulerService.addShip();
    }
}
