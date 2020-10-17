package com.company;

import java.util.ArrayList;
import java.util.Scanner;


//to use the method(s) of other classes

public class Main {
    private ArrayList<Employee> employeeList = new ArrayList<>();
    private ArrayList<Game> gameList = new ArrayList<>();
    private ArrayList<Customer> customerList = new ArrayList<>();
    private ArrayList<Request> requestList = new ArrayList<>();
    Manager manager;
    Scanner input;
    int position;
    String title;
    String genre;
    Double dailyRent;

    public Main(Manager manager, Scanner input){
        this.manager = manager; //Program need manager. create manager by default. COMPOSITE!!!
        this.input = input;
    }


    public void startMainMenu(){
        // So, create a menu for the different users, store Manager, store Employee, and Customer.
        // This is the entry point and should enable user to register new types of users, or register new games or items
        // for DART.

        System.out.println("Main Menu: ");
        System.out.println("Welcome to DART, your good old game rental system. The competition has no steam to keep up!");
        System.out.println("Please specify your role by entering one of the options given: ");
        System.out.println("Enter 'M' for Manager");
        System.out.println("Enter 'E' for Employee");
        System.out.println("Enter 'C' for Customer");
        System.out.println("Enter 'X' to exit system");

        //taking user's input
        String userChoice = input.nextLine();

        //switch case deals with all possible user choices
        // work out the or thing for all cases for capital and lowercase letters (users be dumb)
        switch (userChoice) {
            case "M", "m" -> managerMenu(); //calling the main method of Manager class
            case "E", "e" -> employeeMenu(); //calling the main method of Employee class
            case "C", "c" -> customerMenu(); //calling the main method of Customer class
            case "X", "x" -> exitProgram(); //successfully terminates the program
            default -> System.out.println("Invalid choice"); // in case user enters an invalid option
        }
        backToMenu();
    }

    private void backToMenu() {
        startMainMenu(); //method to go back to main menu
    }

    private void managerMenu(){
        String password = "admin1234";
        System.out.println("Please type the correct password:");

        String userPass = input.nextLine(); //taking user's password
        if (userPass.equals(password)) { //if the user's password matches the admin password
            System.out.println("Manager Screen - Type one of the options below: \n1. Add an employee " +
                    "\n2. View all employees \n3. Return to main menu");
            String userOption1 = input.nextLine(); //taking user's choice

            //switch case to handle all possibilities from the manager
            switch (userOption1) {
                case "1" -> manager.addEmployee(employeeList,input);
                case "2" -> {
                    if (employeeList.size() <= 0)
                        System.out.println("No employees registered");
                    else {
                        manager.printAllEmployee(employeeList);
                        System.out.println("Do you want to remove an employee?Y/N");
                        String YorN = input.nextLine();

                        if (YorN.equals("Y") || YorN.equals("y")) {
                            System.out.println("Enter EmployeeID:");
                            String ID = input.nextLine();
                            manager.removeEmployee(employeeList,ID);
                        }else if(YorN.equals("N") || YorN.equals("n")){
                            backToMenu();
                        }
                    }
                }
                case "3" -> {
                    System.out.println("Go back to menu");
                    backToMenu();
                }
                //in case of an invalid option
                default -> System.out.println("Invalid choice");
            }
        }//if statement ends here
        else { // if the password is incorrect
            System.out.println("Invalid password");
            backToMenu();
        }
    }

    private void employeeMenu(){
        System.out.println("Please type the correct password");
        String userPass2 = input.nextLine(); //taking password from the user

        if (userPass2.equals("password123")) //checking if the password = password123
        {
            //displaying the menu to an employee
            System.out.println("Employee Screen - Type one of the options below: " +
                    "\n1. Register a game \n2. Remove a game" +
                    "\n3. Register a customer \n4. Remove a customer " +
                    "\n5. Show total rent profit \n6. View all games \n7. View all membership requests \n8. Print all customers \n9.Return to main menu");

            String userOption3 = input.nextLine(); //taking user's password
            switch (userOption3) {
                case "1" -> {
                    System.out.println("Register a game");
                    employeeList.get(0).addGame(gameList, input);
                }
                case "2" -> {
                    System.out.println("Remove a game");
                    if (gameList.size() <= 0)
                        System.out.println("No games registered");
                    else {
                        employeeList.get(0).printAllGames(gameList);
                        System.out.println("Which game should be removed? ID: ");
                        String ID= input.nextLine();

                        employeeList.get(0).removeGame(gameList, ID);
                    }
                }
                case "3" -> {
                    System.out.println("Register a customer");
                    employeeList.get(0).addCustomer(customerList,input);
                }
                case "4" -> {
                    if (customerList.size() <= 0)
                        System.out.println("No customers registered");
                    else {
                        System.out.println("Remove a customer");
                        employeeList.get(0).printAllCustomers(customerList);
                        System.out.println("Enter Customer Id to Remove: ");
                        String ID = input.nextLine();
                        employeeList.get(0).removeCustomer(customerList, ID);
                    }
                }
                case "5" -> {System.out.println("Show total profit rent");
                    System.out.printf("Total rent profit is: %.2f\n",manager.getTotalProfit());
                }
                case "6" -> {
                    System.out.println("View all games");
                    if (gameList.size() <= 0)
                        System.out.println("No games registered");
                    else
                        employeeList.get(0).printAllGames(gameList);
                }
                case "7" -> {
                    System.out.println("Show all membership requests");
                    //for all the membership requests received
                    for (int i = 0; i < requestList.size(); i++) {
                        System.out.printf("Do you want to accept (a) or reject (r) this request?\nID: %s\nName: %s\n" , requestList.get(i).getId(), requestList.get(i).getName());
                        String choice = input.nextLine();
                        if (choice.equals("r") || choice.equals("R")) { //if request rejected
                            System.out.println("Membership request rejected!");
                        } else if (choice.equals("a") || choice.equals("A")) { //if request accepted
                            Customer customer = null;
                            for (int index = 0; index < customerList.size(); index++) {
                                if (requestList.get(i).getId().equals(customerList.get(index).getId()))
                                    customer = customerList.get(index);
                            }
                            if (customer != null) {
                                employeeList.get(0).upgradeMembership(requestList.get(i), requestList.get(i).getId(), customer); //call the upgrade function to cater to the request
                            }
                        }
                    }
                    requestList.clear(); //removes all requests after handling them
                }
                case "8" -> {
                    System.out.println("Customer List");
                    employeeList.get(0).printAllCustomers(customerList);
                }
                case "9" -> {
                    System.out.println("Return to main menu");
                    backToMenu();
                }
                default -> {
                    System.out.println("Invalid choice"); // invalid option
                    backToMenu();
                }
            }
        } else {
            System.out.println("Invalid password"); //in case the password is incorrect
            backToMenu();
        }
    }

    private void customerMenu(){
        employeeList.get(0).printAllCustomers(customerList);
        System.out.println("Please put in your id");
        Customer customer = findCustomerId();
        String givenId = toString(customer);
        System.out.println("What's your password?");
        String password = input.nextLine();

        if(customer.getPassword().equals(password)) {
            System.out.println("Customer Screen - Type one of the options below: \n1. Rent a game " +
                    "\n2. Return a game \n3. Return to Main Menu \n4. Send message \n5. Read message \n6. Remove message \n7.Request membership upgrade \n8. Search game or album.");

            String userOption5 = input.nextLine();
            switch (userOption5) {
                case "1": //renting a game
                    int ci = 0, mi = 0; //current items and max items
                    System.out.println("Rent a game");
                    //fetching the customer's current rented items and max allowed items
                    for (int i = 0; i < customerList.size(); i++)
                    {
                        if (givenId.equals(customerList.get(i).getId())) {
                            ci = customerList.get(i).getCurrentItems();
                            mi = customerList.get(i).getMaxItems();
                        }
                    }
                    //checking to see if the current rented items are less than the max limit
                    if (ci < mi) //if renting is allowed
                    {
                        customerList.get(0).printAllGames(gameList);
                        System.out.println("Enter the ID of the game to rent: ");
                        String Id = input.nextLine();
                        customerList.get(0).rentGame(gameList, Id);
                        ci = ci + 1;

                        for (int i = 0; i < customerList.size(); i++)
                        {
                            if (givenId.equals(customerList.get(i).getId()))
                            {
                                customerList.get(i).setCurrentItems(ci);
                            }
                        }
                        System.out.println("You have successfully rented this game!");
                    }
                    else
                        System.out.println("You are not allowed to rent more items.");
                    break;
                case "2":
                    int cc = 0, cpi = 0, curr_i = 0; //current credits, credit per item, current items
                    int index = 0;
                    boolean free = false;
                    boolean isRegistered2 = false; //to check whether a customer with a given ID is registered

                    //fetching the no. of current items rented by the customer
                    for (int i = 0; i < customerList.size(); i++) {
                        if (givenId.equals(customerList.get(i).getId())) {
                            cc = customerList.get(i).getCurrentCredits();
                            cpi = customerList.get(i).getCreditPerItem();
                            curr_i = customerList.get(i).getCurrentItems();
                            isRegistered2 = true;
                        }
                    }
                    if (isRegistered2 == true) {
                        //adding the credits upon return of an item
                        for (int k = 0; k < customerList.size(); k++) {
                            if (givenId.equals(customerList.get(k).getId())) {
                                cc = cc + cpi;
                                customerList.get(k).setCurrentCredits(cc);
                                index = k;
                            }
                        }
                        if (cc >= 5) {
                            free = true;
                            cc = cc - 5;
                            customerList.get(index).setCurrentCredits(cc);

                        }
                        String id2= customerList.get(0).returnGame(gameList,input,free);
                        String id3=String.valueOf(id2);
                        curr_i = curr_i - 1;
                        customerList.get(index).setCurrentItems(curr_i);
                        System.out.printf("\nYour credits have updated to %d!\n", cc);
                        manager.addTotalProfit(gameList);
                        int position = customerList.get(0).findGame(gameList,id3);
                        customerList.get(0).rateGame(gameList, input, position);
                        title = gameList.get(position).getTitle();
                        genre = gameList.get(position).getGenre();
                        dailyRent = gameList.get(position).getDailyRent();

                    } else
                        System.out.println("This customer ID does not exist.\n");

                    break;
                case "3":
                    backToMenu();
                    break;
                case "4":
                    sendMessage(customer);
                    break;
                case "5":
                    readMessage(customer);
                    break;
                case "6":
                    removeMessage(customer);
                    break;
                case "7":
                    //adding the membership upgrade request to the request list
                    String membershipStatus = null, username = null;
                    for (int j = 0; j < customerList.size(); j++) {
                        if (givenId.equals(customerList.get(j).getId())) {
                            membershipStatus = customerList.get(j).getMembership();
                            username = customerList.get(j).getName();
                        }
                    }
                    requestList.add(new Request(givenId, membershipStatus, username));
                    System.out.println("Membership request sent!");
                    break;
                case "8":
                    boolean whileLoop = true;
                    while(whileLoop) {
                        System.out.println("Press 1 for search and 2 for average rating 3 for sorted base on rating");
                        int userInput = input.nextInt();
                        input.nextLine();
                        if (userInput == 1) {
                            customerList.get(0).searchGame(gameList, input);
                            whileLoop = false;
                        } else if (userInput == 2) {
                            customerList.get(0).printAverageRatingNumber(gameList);
                            whileLoop = false;
                        } else if (userInput == 3) {
                            customerList.get(0).sortedBaseOnRating(gameList, input);
                            whileLoop = false;
                        } else {
                            System.out.println("Please type 1, 2 or 3. ");
                        }
                    }
                    break;

                default:
                    System.out.println("Invalid choice");
                    backToMenu();
            }
        }
        else {
            System.out.println("Wrong password");
            backToMenu();
        }
    }


    private void exitProgram(){
        System.out.print("Exiting the system");
        input.close();
        System.exit(0);
    }

    private void sendMessage(Customer senderCustomer){
        System.out.println("Who do you want to send the message to? (Enter ID)");
        Customer receiverCustomer = findCustomerId();

        System.out.println("Enter your message!");
        String messageContent = input.nextLine(); //The content of message
        Message message = new Message(senderCustomer.getId(),receiverCustomer.getId()); //Create new message (object) message require sender and receiver ID
        message.setContent(messageContent); // it sets the content
        senderCustomer.addSentMessage(message); //Adds to the sender list
        receiverCustomer.addReceivedMessage(message); //Adds to the receivers list
    }

    private void readMessage(Customer reader){
        System.out.println("Here are your messages: ");
        reader.printAllUnreadReceivedMessages();
    }

    private void removeMessage(Customer customer){
        System.out.println("This is your inbox: ");
        customer.printAllReceivedMessages();

        System.out.println("Which messages do you want to remove?");
        String messageNumberString = input.nextLine();
        if (messageNumberString.contains("[a-zA-Z]+")){ //checks if the user input has any letters in it (regex command)
            System.out.println("Your input \""+ messageNumberString+ "\" is not an integer");
            backToMenu();
        }
        int messageNumber = Integer.parseInt(messageNumberString);
        customer.removeInboxMessage(messageNumber);
    }

    private Customer findCustomerId(){
        Customer customer = null;
        Boolean isIdFound = false;
        String customerId = input.nextLine();

        for(int i = 0; i < customerList.size(); i++){
            if (customerList.get(i).getId().equals(customerId)){
                customer = customerList.get(i);
                isIdFound = true;
            }
        }
        if (isIdFound == false){
            System.out.println("Your Id is not found " +  customerId + " is not found" );
            backToMenu();
        }
        return customer;
    }
    public String toString(Customer customerId){//overriding the toString() method
        for(int i=0;i<=customerList.size();i++)
            if(customerList.get(i).getId().equals(customerId.getId()))
        return customerList.get(i).getId();
            return null;
    }

    public static void main(String[] args) {
        Manager manager = new Manager(); //We have to have manager in the program otherwise the code can't run
        Scanner input = new Scanner(System.in);

        Main main = new Main(manager, input);
        main.startMainMenu();
    }
}