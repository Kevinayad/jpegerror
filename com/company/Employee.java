package com.company;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.UUID;

public class Employee{
    private String id;
    private String name;
    private int birthYear;
    private String address;
    private double salaryGross;
    private int currentYear = Calendar.getInstance().get(Calendar.YEAR);

    public Employee(String name, int birthYear, String address, double salaryGross){
        this.id= UUID.randomUUID().toString();
        this.name = name;
        this.birthYear = birthYear;
        this.address = address;
        this.salaryGross = salaryGross;
    }

    public int getAge(){
        return this.currentYear-this.birthYear;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getSalaryNet(){
        final double TAX_PERCENT = 0.7;
        final double SALARY_TAX_LIMIT = 100000;
        final double MONTHS_IN_YEAR = 12;
        double yearlySalary = this.salaryGross * MONTHS_IN_YEAR;
        double salaryBonus = 0;
        if(getAge() < 22){
            salaryBonus = 4000;
        }
        else if (getAge() < 30){
            salaryBonus = 6000;
        }
        else if(getAge()>30) {
            salaryBonus = 7500;
        }

        if (yearlySalary < SALARY_TAX_LIMIT){
            Double Salary=yearlySalary+salaryBonus;
            return Salary;
        }
        else{
            Double Salary=yearlySalary*TAX_PERCENT+salaryBonus;
            return Salary;
        }
    }

    public void addCustomer(ArrayList<Customer> customerList, Scanner input) {
        System.out.print("Enter customer's name: ");
        String name = input.nextLine();

        System.out.println("Enter the customers password");
        String password = input.nextLine();
        customerList.add(new Customer(name, password));
    }

    public void removeCustomer(ArrayList<Customer> customerList, String userId){
        boolean isCustomerFound = false;

        for(int i = 0; i < customerList.size(); i++){
            if (customerList.get(i).getId().equals(userId)){
                isCustomerFound = true;
                customerList.remove(i);
                System.out.println("customer with ID: " + userId + " is successfully removed!");
            }
        }
        if (isCustomerFound == false){
            System.out.println("Game with ID:" +  userId + " is not found" );
        }
    }

    public void addGame(ArrayList<Game> Gamelist, Scanner input) {
        System.out.println("Please enter the game's:");
        System.out.print("title: ");
        String title = input.nextLine();

        System.out.print("Genre: ");
        String genre = input.nextLine();

        System.out.print("Daily rent fee: ");

        double dailyRent = input.nextDouble();
        input.nextLine();

        Gamelist.add(new Game(title, genre, dailyRent));
        System.out.println("The game " + title + " has successfully been registered!");
    }

    public void removeGame(ArrayList<Game> gameList, String userId) {
        boolean isGamefound = false;

        for (int i = 0; i < gameList.size(); i++) {
            if (gameList.get(i).getId().equals(userId)) {
                isGamefound = true;
                gameList.remove(i);
                System.out.println("Game with ID: " + userId + " is successfully removed!");
            }
        }
        if (!isGamefound) {
            System.out.println("Game with ID:" + userId + " is not found");
        }
    }

    public void printAllGames(ArrayList<Game> gameList) {
        String status1;
        for (int i = 0; i < gameList.size(); i++) {
            if (gameList.get(i).getIsAvailableStatus() == true) {
                status1 = "Available";
            } else {
                status1 = "Not Available";
            }

            System.out.println(String.format("ID: %s\nTitle: %s\ngenre:%s\nDaily rent fee: %.2f\nStatus: %s\n"
                    , gameList.get(i).getId(), gameList.get(i).getTitle(),
                    gameList.get(i).getGenre(), gameList.get(i).getDailyRent()
                    , status1));
        }
    }

    public void printAllCustomers(ArrayList<Customer> customerList) {
        for (Customer value : customerList) {
            System.out.printf("ID: %s\nName: %s\nMembership: %s\n\n", value.getId(), value.getName(), value.getMembership());
        }
    }
    public String findCustomerId(ArrayList<Customer> customerList, String customerId){

        Boolean isIdFound = false;
        String pass = null;

        for(int i = 0; i < customerList.size(); i++){
            if (customerList.get(i).getId().equals(customerId)){
                pass = customerList.get(i).getPassword();
                isIdFound = true;
            }
        }
        if (isIdFound == false){
            System.out.println("Your Id " +  customerId + " is not found" );
        }

        return pass;
    }
    public void upgradeMembership(Request request, String userId, Customer customer) {

        switch (request.getMembership()) {
            case "regular" -> {
                customer.setMembership("silver");
                //for discount, using 100 - required percentage of discount because we're multiplying this value with the final rent
                customer.setDiscount(0.9); //10% discount
                customer.setMaxItems(3);
                customer.setCreditPerItem(1);
                System.out.println("Your membership has been upgraded to Silver!");

            }
            case "silver" -> {
                customer.setMembership("gold");
                customer.setDiscount(0.85); //15% discount
                customer.setMaxItems(5);
                customer.setCreditPerItem(2);
                System.out.println("Your membership has been upgraded to Gold!");
            }
            case "gold" -> {
                customer.setMembership("platinum");
                customer.setDiscount(0.75); //25% discount
                customer.setMaxItems(7);
                customer.setCreditPerItem(3);
                System.out.println("Your membership has been upgraded to Platinum!");
            }
            case "platinum" -> {
                customer.setMembership("platinum");
                customer.setDiscount(0.75); //25% discount
                customer.setMaxItems(7);
                customer.setCreditPerItem(3);
                System.out.println("Your membership is already set to Platinum!");
            }
            default -> customer.setMembership("regular"); //if a platinum member sends upgrade request, do nothing
        }

        System.out.println("Membership of customer with ID: " + userId + " is successfully upgraded!");
        //once the upgrade is done, remove that request from the request list
    }
}