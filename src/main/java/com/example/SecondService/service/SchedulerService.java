package com.example.SecondService.service;

import com.example.SecondService.model.Cargo;
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

    @PostConstruct
    @SneakyThrows
    public void init() {
        Files.createDirectories(Path.of("scheduled/"));
        Files.createDirectories(Path.of("reports/"));
    }

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
            objectMapper.writeValue(new File("scheduled/" + UUID.randomUUID().toString() + ".json"), ships);
        } catch (Exception e) {
            System.out.println("can't save");
        }
    }

//    @Async
//    public void saveScheduleFromConsole() {
//        while (true) {
//            Scanner scanner = new Scanner(System.in);
//            String json = scanner.nextLine();
//
//            try {
//                List<Ship> schedulesFromFile = downloadSchedules();
//                Ship ship = objectMapper.readValue(json, Ship.class);
//
//                schedulesFromFile.add(ship);
//                objectMapper.writeValue(new File("scheduled/" + UUID.randomUUID().toString() + ".json"), schedulesFromFile);
//            } catch (Exception e) {
//                System.out.println("Something wrong!");
//            }
//        }
//    }

    @Async
    public void addShip() {
        try {
            List<Ship> schedulesFromFile = downloadSchedules();
            Scanner in = new Scanner(System.in);
            String userAnswer = "n";
            System.out.println("Add ship? y/n: ");
            userAnswer = in.next();
            while (!userAnswer.equals("n")) {
                if (userAnswer.equals("y")) {
                    System.out.println("Ship's name: ");
                    String name = in.next();
                    System.out.println("0->loose 1->liquid 2->containers: ");
                    int cargoTypeAsInt = in.nextInt();
                    System.out.println("Cargo's amount: ");
                    int weightOrQuantity = in.nextInt();
                    System.out.println("Arrival time: ");
                    int arrivalTime = in.nextInt();
                    Cargo.CargoType cargoType = switch (cargoTypeAsInt) {
                        case 0 -> Cargo.CargoType.LOOSE;
                        case 1 -> Cargo.CargoType.LIQUID;
                        case 2 -> Cargo.CargoType.CONTAINER;
                        default -> throw new IllegalStateException("Unexpected value: " + cargoTypeAsInt);
                    };
                    int workingCranesPerformance = switch (cargoTypeAsInt) {
                        case 0 -> 1;
                        case 1 -> 2;
                        case 2 -> 3;
                        default -> 0;
                    };
                    //  int hours = arrivalTime;
                    int unloadingTime = weightOrQuantity / workingCranesPerformance;
                    String month = "June";
                    Cargo cargo = new Cargo(cargoType, weightOrQuantity);
                    int day = 0;
                    int hoursInADay = arrivalTime / 60;
                    int minutesInADay = arrivalTime;

                    if (arrivalTime < 0) {
                        month = "May";
                        int TOTAL_MONTH_MINUTES = 43200;
                        day = ((TOTAL_MONTH_MINUTES + arrivalTime) / 60) / 24 + 1;
                    }
                    if (arrivalTime >= 0) {
                        month = "June";
                        day = (int) Math.floor(((arrivalTime) / 60) / 24) + 1;
                    }
                    if ((arrivalTime > 43200)) {
                        month = "July";
                        day = (int) Math.floor(((arrivalTime) / 60) / 24) - 29;
                    }
                    if (minutesInADay / 60 > 0) {
                        while ((hoursInADay) > 24) {
                            hoursInADay = hoursInADay - 24;
                        }
                        while ((minutesInADay > 60)) {
                            minutesInADay = minutesInADay - 60;
                        }
                    } else {
                        while (hoursInADay < 0) {
                            hoursInADay = hoursInADay + 24;
                        }
                        while ((minutesInADay < 0)) {
                            minutesInADay = minutesInADay + 60;
                        }
                    }
                    if (hoursInADay % 24 == 0) {
                        hoursInADay = 23;
                    }
                    Ship ship = new Ship(name, cargo, arrivalTime / 60, arrivalTime, unloadingTime, workingCranesPerformance, month, day, hoursInADay, minutesInADay);
                    schedulesFromFile.add(ship);
                    System.out.println("Add another ship? y/n: ");
                    userAnswer = in.next();
                    if (userAnswer.equals("y")) {
                        continue;
                    }
                }
            }

            objectMapper.writeValue(new File("scheduled/" + UUID.randomUUID().toString() + ".json"), schedulesFromFile);
        } catch (Exception e) {
            System.out.println("Something wrong!");
        }
    }

    @SneakyThrows
    public void saveReport(List<SimpleUnloadingReport> reports) {
        objectMapper.writeValue(new File("reports/" + UUID.randomUUID().toString() + ".json"), reports);
    }

    @SneakyThrows
    private List<Ship> downloadSchedules() {
        return objectMapper.readValue(new URL(schedulesURL), new TypeReference<List<Ship>>() {
        });
    }

}
