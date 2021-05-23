package com.example.SecondService.service;

import com.example.SecondService.model.Report;
import com.example.SecondService.model.Ship;
import com.example.SecondService.model.SimpleUnloadingReport;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class SchedulerService {

    @Autowired
    private ObjectMapper objectMapper;
    String schedulesURL = "http://localhost:8081/scheduler";

    public List<Ship> getSchedulesFromService() {
        List<Ship> ships = downloadSchedules();
        saveSchedules(ships);

        return ships;
    }

    @SneakyThrows
    public String getSchedulesFromCurrentFile(String filename) {
        try {
            return Files.readString(Path.of(filename));
        } catch (Exception e) {
            return "";
        }
    }

    private void saveSchedules(List<Ship> ships) {
        try {
            objectMapper.writeValue(new File(UUID.randomUUID().toString() + ".json"), ships);
        } catch (Exception e) {
            System.out.println("can't save");
        }
    }

    @Async
    public void saveScheduleFromConsole() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String json = scanner.nextLine();

            try {
                List<Ship> schedulesFromFile = downloadSchedules();
                Ship ship = objectMapper.readValue(json, Ship.class);

                schedulesFromFile.add(ship);
                objectMapper.writeValue(new File(UUID.randomUUID().toString() + ".json"), schedulesFromFile);
            } catch (Exception e) {
                System.out.println("Something wrong!");
            }
        }
    }

    @SneakyThrows
    public void saveReport(List<SimpleUnloadingReport> reports) {
        objectMapper.writeValue(new File(UUID.randomUUID().toString() + ".json"), reports);
    }

    @SneakyThrows
    private List<Ship> downloadSchedules() {
        return objectMapper.readValue(new URL(schedulesURL), new TypeReference<List<Ship>>() {});
    }

}
