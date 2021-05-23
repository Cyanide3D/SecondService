package com.example.SecondService.controller;

import com.example.SecondService.model.Report;
import com.example.SecondService.model.Ship;
import com.example.SecondService.service.SchedulerService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/serialize")
public class SerializeController {

    @Autowired
    private SchedulerService schedulerService;

    @GetMapping
    @SneakyThrows
    public List<Ship> deserializeSchedule(@RequestParam(value = "file", required = false) String param) {
        return schedulerService.getSchedulesFromFile(param);
    }

    @PostMapping
    public void serialize(@RequestBody Report report) {
        schedulerService.saveReport(report);
    }

}
