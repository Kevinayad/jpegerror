package com.company;

import java.time.LocalDate;
import java.util.UUID;

public class Game {

    private String id;
    private String title;
    private String genre;
    private double dailyRent;
    private LocalDate date;
    private double totalProfit=0;
    private boolean isAvailableStatus = true;
    private double totalRent;

    public Game(String title, String genre, double dailyRent) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.genre = genre;
        this.dailyRent = dailyRent;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public double getDailyRent() {
        return dailyRent;
    }

    public double getTotalRent() {
        return totalRent;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public boolean getIsAvailableStatus() {
        return isAvailableStatus;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setIsAvailableStatus(boolean status) {
        this.isAvailableStatus = status;
    }

}
