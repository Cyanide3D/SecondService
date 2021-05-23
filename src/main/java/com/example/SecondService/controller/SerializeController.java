package com.example.SecondService.controller;

import com.example.SecondService.model.Report;
import com.example.SecondService.model.Ship;
import com.example.SecondService.service.SchedulerService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SerializeController {

    @Autowired
    private SchedulerService schedulerService;

    @GetMapping("/serialize")
    @SneakyThrows
    public List<Ship> deserializeSchedule(@RequestParam(value = "file", required = false) String param) {
        return schedulerService.getSchedulesFromService();
    }

    @GetMapping("getJson/{name}")
    public String deserializeJson(@PathVariable("name") String filename) {
        return schedulerService.getSchedulesFromCurrentFile(filename);
    }

    @PostMapping("/serialize")
    public void serialize(@RequestBody Report report) {
        schedulerService.saveReport(report);
    }

}
