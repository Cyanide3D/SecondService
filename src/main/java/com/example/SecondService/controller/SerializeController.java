package com.example.SecondService.controller;

import com.example.SecondService.model.Report;
import com.example.SecondService.model.Ship;
import com.example.SecondService.service.SchedulerService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SerializeController {

    @Autowired
    private SchedulerService schedulerService;

    @GetMapping("/serialize")
    @SneakyThrows
    public List<Ship> deserializeSchedule() {
        return schedulerService.getSchedulesFromService();
    }

    @GetMapping(value = "getJson/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String deserializeJson(@PathVariable("name") String filename) {
        return schedulerService.getSchedulesFromCurrentFile(filename);
    }

    @PostMapping("/serialize")
    public void serialize(@RequestBody Report report) {
        schedulerService.saveReport(report);
    }

}
