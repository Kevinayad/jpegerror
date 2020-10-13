package com.company;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Customer {
    private String name;
    private String id;
    private String password;

    private String membership;
    private double discount;
    private int maxItems;
    private int currentItems;
    private int creditPerItem;
    private int currentCredits;


    private ArrayList<Message> sentMessageList = new ArrayList();
    private ArrayList<Message> receivedMessageList = new ArrayList();

    public Customer(String name, String password){
        this.name=name;
        this.password = password;
        this.id= UUID.randomUUID().toString();
        this.name=name;
        this.membership= "regular";
        this.discount = 1;
        this.creditPerItem = 0;
        this.maxItems = 1;
        this.currentItems = 0;
        this.currentCredits = 0;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPassword(){
        return password;
    }



    public String getMembership()  { return membership; }

    public int getCreditPerItem() { return creditPerItem; }

    public int getMaxItems() { return maxItems; }

    public int getCurrentItems(){ return currentItems; }

    public int getCurrentCredits() {return currentCredits; }

    public void setMembership(String membership) {this.membership = membership; }

    public void setCurrentCredits(int currentCredits) {this.currentCredits = currentCredits; }

    public void setCurrentItems(int currentItems) {this.currentItems = currentItems; }

    public void setDiscount(double discount) { this.discount = discount; }

    public void setMaxItems(int maxItems) {this.maxItems = maxItems; }

    public void setCreditPerItem(int creditPerItem) {this.creditPerItem = creditPerItem; }


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

    public void rentGame(ArrayList<Game> gameList, String id) {
        boolean isGameFound = false;

        for (int i = 0; i < gameList.size(); i++) {
            if (gameList.get(i).getId().equals(id) && gameList.get(i).getIsAvailableStatus() == false) {
                isGameFound = true;
                System.out.printf(" Game with id %s is already rented.\n", gameList.get(i).getId());

            } else if (gameList.get(i).getId().equals(id)) {
                isGameFound = true;
                gameList.get(i).setIsAvailableStatus(false);
                gameList.get(i).setDate(LocalDate.now());
            }
        }
        if (isGameFound == false) {
            System.out.printf(" Game with id %s not found.\n", id);
        }
    }

    public void returnGame(ArrayList<Game> gameList, Scanner input, boolean free) {
        printAllGames(gameList);
        System.out.print("Enter game Id you want to return: ");
        String id = input.nextLine();

        System.out.println("Which year is it?");
        int returnYear = input.nextInt();
        input.nextLine();

        System.out.println("Which month is it?");
        int returnMonth = input.nextInt();
        input.nextLine();

        System.out.println("Which date is it today? ");
        int returnDate = input.nextInt();
        input.nextLine();

        LocalDate customerReturnDate = LocalDate.of(returnYear, returnMonth, returnDate);

        boolean isGameFound = false;

        for (int i = 0; i < gameList.size(); i++) {
            if (gameList.get(i).getId().equals(id)) {
                isGameFound = true;

                double rentDays = ChronoUnit.DAYS.between(LocalDate.now(),customerReturnDate);
                if (rentDays == 0) {
                    rentDays = 1;
                } else if (rentDays < 0) {
                    System.out.println("You need to return it after "+ LocalDate.now());
                    break; //So he can't return the game and jumps out of loop
                }

                double totalRent = gameList.get(i).getDailyRent() * (rentDays) * (discount);
                if (free == true)
                {
                    totalRent = 0;
                    System.out.println("You get this item for free in exchange of 5 credits!\n");
                }
                gameList.get(i).setTotalProfit(totalRent);
                if(discount != 1){

                    System.out.printf("You get a discount of %.0f percent due to your membership status!", (1-discount)*100);
                }
                System.out.printf("%nPay %.2f SEK\n", totalRent);

                gameList.get(i).setIsAvailableStatus(true);
            }
        }

        if(!isGameFound){
            System.out.println("The game with ID: "+ id +" not found");
        }
    

        if(isGameFound == false){
            System.out.println("The game with ID: "+ id +" not found");
        }
    }

    public void addSentMessage(Message message) {
        sentMessageList.add(message);
    }

    public void addReceivedMessage(Message message){
        receivedMessageList.add(message);
    }

    public void printAllUnreadReceivedMessages(){
        int messageCounter = 1;
        for(int i = 0; i < receivedMessageList.size(); i++){
            boolean readedStatus = receivedMessageList.get(i).getReadedStatus();
            if(readedStatus==false) {
                System.out.println( messageCounter + ". Sender is " + receivedMessageList.get(i).getSender() );
                System.out.println(receivedMessageList.get(i).getContent() + "\n");
                receivedMessageList.get(i).setReadedStatus(true);
                messageCounter++;
            }
        }
    }

    public void printAllReceivedMessages(){
        for(int i = 0; i < receivedMessageList.size(); i++){
            int messageNumber = (i+1);
            System.out.println( messageNumber + ". Sender is " + receivedMessageList.get(i).getSender() );
            System.out.println(receivedMessageList.get(i).getContent() + "\n");
        }
    }

    public void removeInboxMessage(int messageNumber){
        if(messageNumber <= 0 || messageNumber > receivedMessageList.size()){
            System.out.println("Message with the number " + messageNumber + " does not exist");
            return; //goes back to where it is called
        }
        int messageIndex = messageNumber-1;
        receivedMessageList.remove(messageIndex);
        System.out.println("The message has successfully been removed!");
    }


}
