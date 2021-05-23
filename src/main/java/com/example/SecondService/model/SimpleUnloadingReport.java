package com.example.SecondService.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimpleUnloadingReport {
    private List<SimpleShipUnloadingReport> reports;
    private Cargo.CargoType cargoType;
    private int fine;
    private int cranesQuantity;
    private int totalFine;
    private int queueSize;
    private int amountOfShips;
    private int staticDelay;
    private int staticAverageDelay;

    public SimpleUnloadingReport() {
        reports = Collections.synchronizedList(new ArrayList<>());
    }

    public void add(SimpleShipUnloadingReport report) {
        reports.add(report);
    }

    public List<SimpleShipUnloadingReport> getReports() {
        return reports;
    }

    public void setReports(List<SimpleShipUnloadingReport> reports) {
        this.reports = reports;
    }

    public Cargo.CargoType getCargoType() {
        return cargoType;
    }

    public void setCargoType(Cargo.CargoType cargoType) {
        this.cargoType = cargoType;
    }

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    public int getCranesQuantity() {
        return cranesQuantity;
    }

    public void setCranesQuantity(int cranesQuantity) {
        this.cranesQuantity = cranesQuantity;
    }

    public int getTotalFine() {
        return totalFine;
    }

    public void setTotalFine(int totalFine) {
        this.totalFine = totalFine;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }

    public int getAmountOfShips() {
        return amountOfShips;
    }

    public void setAmountOfShips(int amountOfShips) {
        this.amountOfShips = amountOfShips;
    }

    public int getStaticDelay() {
        return staticDelay;
    }

    public void setStaticDelay(int staticDelay) {
        this.staticDelay = staticDelay;
    }

    public int getStaticAverageDelay() {
        return staticAverageDelay;
    }

    public void setStaticAverageDelay(int staticAverageDelay) {
        this.staticAverageDelay = staticAverageDelay;
    }
}
