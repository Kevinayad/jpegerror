package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Manager {

    //need to be outside main method because of backToMenu method since it reset totalProfit
    private double totalProfit = 0;

    public double getTotalProfit() {
        return totalProfit;
    }

    //method to add an employee
    public void addEmployee(ArrayList<Employee> employeeList, Scanner input) {
        System.out.println("Please type the Employee’s:");
        System.out.print("Name: ");
        String name = input.nextLine();

        System.out.print("birth year: ");
        int birthYear = input.nextInt();
        input.nextLine();

        System.out.print("address: ");
        String address = input.nextLine();

        System.out.print("monthly gross salary: ");
        Double salaryGross = input.nextDouble();
        input.nextLine();

        employeeList.add(new Employee(name, birthYear, address, salaryGross));

        System.out.println("The employee " + name + " has successfully been registered!");
    }

    //method to view all employees
    public void printAllEmployee(ArrayList<Employee> employeeList) {
        for (int i = 0; i < employeeList.size(); i++) {
            System.out.printf("ID:%s\nName: %s\nAge: %d\nAdress: %s\nYearly gross salary: %.2f SEK.\n"
                    , employeeList.get(i).getId(), employeeList.get(i).getName(),
                    employeeList.get(i).getAge(),employeeList.get(i).getAddress()
                    , employeeList.get(i).getSalaryNet());
        }
    }

    public void removeEmployee(ArrayList<Employee> employeeList, String userId){
        boolean isEmployeefound = false;

        for(int i = 0; i < employeeList.size(); i++){
            if (employeeList.get(i).getId().equals(userId)){
                isEmployeefound = true;
                employeeList.remove(i);
                System.out.println("Employee with ID: " + userId + " is successfully removed!");
            }
        }
        if (isEmployeefound == false){
            System.out.println("Emloyee with ID:" +  userId + " is not found" );
        }
    }

    public void addTotalProfit(ArrayList<Game> gameList){
        for(int i=0;i<gameList.size();i++) {
            totalProfit = totalProfit + gameList.get(i).getTotalProfit();
        }
    }
}