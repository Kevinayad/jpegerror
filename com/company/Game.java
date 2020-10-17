package com.company;

import java.time.LocalDate;
import java.util.ArrayList;
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
    private ArrayList<Integer> ratingNumbers;
    private String reviewText;

    public Game(String title, String genre, double dailyRent) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.genre = genre;
        this.dailyRent = dailyRent;
    }
    public Game(String title, String genre, double dailyRent, String reviewText) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.genre = genre;
        this.dailyRent = dailyRent;
        this.reviewText = reviewText;
        this.ratingNumbers = new ArrayList<>();
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

    public ArrayList<Integer> getRatingNumbers() {
        return ratingNumbers;
    }

    public double getAverageRatingNumber() {
        double averageNumber = 0.00;
        double total = 0;
        ArrayList<Integer> ratingNumbers = getRatingNumbers();
        for (int ratingNumber: ratingNumbers) { // sum all rating number
            total += ratingNumber;
        }
        if (total == 0){ //if game have no rating give 0 as default
            return 0;
        }
        return averageNumber = total / ratingNumbers.size();
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setRatingNumber(int ratingNumber) {
        this.ratingNumbers.add(ratingNumber);
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String toString(){
        return String.format("ID: %s\nTitle: %s\ngenre:%s\nDaily rent fee: %.2f\nAvailable status: %s\n"
                , getId(), getTitle(), getGenre(), getDailyRent(), getIsAvailableStatus());

    }

}
