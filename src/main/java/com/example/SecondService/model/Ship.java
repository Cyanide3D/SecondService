package com.example.SecondService.model;

import java.util.Random;

public class Ship
{
    private String name;
    public Cargo cargo;
    private int arrivalTimeInHours;
    private int arrivalTime;
    private int unloadingTime;
    private int cranePerformance;
    private String month;
    private int day;
    private int h;
    private int m;

    public Ship(String name, Cargo cargo, int arrivalTimeInHours, int arrivalTime, int unloadingTime, int cranePerformance, String month, int day, int h, int m) {
        this.name = name;
        this.cargo = cargo;
        this.arrivalTime = arrivalTime;
        this.arrivalTimeInHours = arrivalTimeInHours;
        this.unloadingTime = unloadingTime;
        this.cranePerformance = cranePerformance;
        this.month = month;
        this.day = day;
        this.h = h;
        this.m = m;
    }

    Ship()
    {}
    public int getM()
    {
        return m;
    }

    public void setM(int m)
    {
        this.m = m;
    }

    public int getH()
    {
        return h;
    }

    public void setH(int h)
    {
        this.h = h;
    }

    public int getCranePerformance()
    {
        return cranePerformance;
    }

    public void setCranePerformance(int cranePerformance)
    {
        this.cranePerformance = cranePerformance;
    }

    public int getArrivalTime()
    {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime)
    {
        this.arrivalTime = arrivalTime;
    }

    public int getUnloadingTime()
    {
        return unloadingTime;
    }

    public void setUnloadingTime(int unloadingTime)
    {
        this.unloadingTime = unloadingTime;
    }

    public int getArrivalTimeInHours()
    {
        return arrivalTimeInHours;
    }

    public void setArrivalTimeInHours(int hour)
    {
        this.arrivalTimeInHours = hour;
    }

    public String getMonth()
    {
        return month;
    }

    public void setMonth(String month)
    {
        this.month = month;
    }

    public int getDay()
    {
        return day;
    }

    public void setDay(int day)
    {
        this.day = day;
    }

    public Cargo getCargo()
    {
        return cargo;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public static Ship createShip()
    {
        Random rand = new Random();
        int cranePerformance = 0;
        int maxMinutes = 43200;
        String[] variantsOfNames = new String[]{"First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eighth", "Ninth", "Tenth", "Eleventh", "Twelfth", "Thirteenth", "Fourteenth", "Fifteenth", "Sixteenth", "Seventeenth", "Eighteenth", "Nineteenth", "Twentieth"};
        String name = variantsOfNames[rand.nextInt(variantsOfNames.length)];
        Cargo cargo = Cargo.createCargo();
        int arrivalTime = rand.nextInt(maxMinutes);
        String month = "June";
        int day = 1;
        int hoursInADay= 0;
        int minutesInADay = 0;
        cranePerformance = switch (cargo.getCargoType())
                {
                    case LOOSE -> 1;
                    case LIQUID -> 2;
                    case CONTAINER -> 3;
                };
        int unloadingTime = cargo.getWeightAmount() / cranePerformance;
        Ship ship = new Ship(name, cargo, arrivalTime, arrivalTime, unloadingTime, cranePerformance, month, day, hoursInADay, minutesInADay);
        return ship;
    }
}





