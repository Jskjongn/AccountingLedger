package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class AccountingLedgerApp {

    // creates and formats the time stamps for the logger
    static DateTimeFormatter timeStampFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss");

    // creates scanner for user input
    static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {

        ArrayList<Transaction> transactions = getTransaction();

        // variable for the home screen to keep running until false (case X)
        boolean homeRunning = true;
        while (homeRunning) {
            // calls home screen method that returns user input
            String homeScreenOption = homeScreen();
            // matches user input main four options: D, P, L, X
            switch (homeScreenOption) {
                // Add Deposit
                case "D":
                    // calls make deposit method that adds deposits to file (positive numbers)
                    makeDeposit(transactions);
                    break;
                // Make Payment
                case "P":
                    // calls make payment method that adds payments to file(negative numbers
                    makePayment(transactions);
                    break;
                // Ledger Screen
                case "L":
                    // variable for the ledger screen to keep running or return to home screen (case H)
                    boolean ledgerRunning = true;
                    while (ledgerRunning) {
                        // calls ledger screen method that returns user input
                        String ledgerScreenOption = ledgerScreen();
                        // matches user input into one of five options: A, D, P, R, H
                        switch (ledgerScreenOption) {
                            // All
                            case "A":
                                // displays all transactions made
                                viewAll(transactions);
                                break;
                            // Deposits
                            case "D":
                                // displays only deposits made
                                viewDeposits(transactions);
                                break;
                            // Payments
                            case "P":
                                // displays only payments made
                                viewPayments(transactions);
                                break;
                            // Reports Screen
                            case "R":
                                // variable for the reports screen to keep running or return to ledger screen (case 0)
                                boolean reportsRunning = true;
                                while (reportsRunning) {
                                    // calls reports screen method that returns user input
                                    int reportsScreenOption = reportsScreen();
                                    // matches user input to 0-5
                                    switch (reportsScreenOption) {
                                        // Month To Date
                                        case 1:
                                            break;
                                        // Previous Month
                                        case 2:
                                            break;
                                        // Year To Date
                                        case 3:
                                            break;
                                        // Previous Year
                                        case 4:
                                            break;
                                        // Search by Vendor
                                        case 5:
                                            break;
                                        // Returns back to Ledger Screen
                                        case 0:
                                            reportsRunning = false;
                                            break;
                                        // displays if user didn't choose a correct option
                                        default:
                                            System.out.println("Invalid option - Please choose one of the valid options!");
                                            break;
                                    }
                                }
                                break;
                            // Returns back to Home Screen
                            case "H":
                                ledgerRunning = false;
                                break;
                            // displays if user didn't choose a correct option
                            default:
                                System.out.println("Invalid option - Please choose one of the valid options!");
                                break;
                        }
                    }
                    break;
                // Exit the App completely
                case "X":
                    homeRunning = false;
                    break;
                // displays if user didn't choose a correct option
                default:
                    System.out.println("Invalid option - Please choose one of the valid options!");
                    break;
            }
        }
    }

    // displays the home screen with four options
    public static String homeScreen() {
        // prompts user what they would like to do or exit the app
        System.out.println("H) Home Screen - What may I assist you with today?\n" +
                "\tD) Make a Deposit\n" +
                "\tP) Make a Payment (Debit)\n" +
                "\tL) View Ledgers and Previous Transactions\n" +
                "\tX) Exit the Application");
        // returns user input
        return userInput.nextLine().toUpperCase().trim();
    }

    // makes a deposit (positive number)
    public static void makeDeposit(ArrayList<Transaction> transactions) {
        // calls file writer method to starting writing to the file
        BufferedWriter bufferWriter = fileWriter("transactions.csv");
        // prompts user for deposit information and stores the user inputs
        System.out.println("D) Make a Deposit - Please enter your deposit information:");
        System.out.print("Description: ");
        String description = userInput.nextLine().trim();
        System.out.print("\nVendor: ");
        String vendor = userInput.nextLine().trim();
        System.out.print("\nAmount: ");
        double amount = userInput.nextDouble();
        userInput.nextLine();

        try {
            // creates the time stamp and takes in current date and time
            LocalDateTime timeStamp = LocalDateTime.now();
            // creates a new line to write on
            bufferWriter.newLine();
            // concatenates the time stamp with the formatter and the user inputs of their deposit information
            bufferWriter.write(timeStamp.format(timeStampFormatter) + "|" + description + "|" + vendor + "|" + amount);
            // displays out the deposit just made
            System.out.println("Deposit: " + timeStamp.format(timeStampFormatter) + "|" + description + "|" + vendor + "|" + amount +
                    " successfully made!");
            // closes out the writer
            bufferWriter.close();
        } catch (Exception e) {
            System.out.println("Deposit was not successfully made!" + e.getMessage());
        }

    }

    // makes a payment (negative number)
    public static void makePayment(ArrayList<Transaction> transactions) {
        // calls file writer method to starting writing to the file
        BufferedWriter bufferWriter = fileWriter("transactions.csv");
        // prompts user for payment information and stores the user inputs
        System.out.println("P) Make a Payment - Please enter your debit information:");
        System.out.print("Description: ");
        String description = userInput.nextLine().trim();
        System.out.print("\nVendor: ");
        String vendor = userInput.nextLine().trim();
        System.out.print("\nAmount: ");
        double amount = userInput.nextDouble();
        userInput.nextLine();

        try {
            // creates the time stamp and takes in current date and time
            LocalDateTime timeStamp = LocalDateTime.now();
            // creates a new line to write on
            bufferWriter.newLine();
            // turns amount entered into a negative
            double payment = -1 * amount;
            // concatenates the time stamp with the formatter and the user inputs of their payment information
            bufferWriter.write(timeStamp.format(timeStampFormatter) + "|" + description + "|" + vendor + "|" + payment);
            // displays out the payment just made
            System.out.println("Payment: " + timeStamp.format(timeStampFormatter) + "|" + description + "|" + vendor + "|" + payment +
                    " successfully made!");
            // closes out the writer
            bufferWriter.close();
        } catch (Exception e) {
            System.out.println("Payment was not successfully made!" + e.getMessage());
        }

    }

    // displays ledger screen to view previous transactions
    public static String ledgerScreen() {
        // prompts user how they would like to view their ledgers or go back home
        System.out.println("L) Ledgers - How would you like to view your ledgers?\n" +
                "\tA) All\n" +
                "\tD) Deposits\n" +
                "\tP) Payments\n" +
                "\tR) Reports\n" +
                "\tH) Back to Home Screen");
        // returns user input
        return userInput.nextLine().toUpperCase().trim();
    }

    // displays all the ledgers in the transactions file
    public static void viewAll(ArrayList<Transaction> transactions) {
        // loops through the file and displays all transactions
        for (Transaction t : transactions) {
            System.out.printf("%tF|%tT|%s|%s|$%.2f\n",
                    t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
        }
    }

    // displays only deposits made
    public static void viewDeposits(ArrayList<Transaction> transactions) {
        // loops through the file and displays deposits
        for (Transaction t : transactions) {
            // if the amount is greater than 0 (positive) then it gets displayed
            if (t.getAmount() > 0) {
                System.out.printf("%tF|%tT|%s|%s|$%.2f\n",
                        t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            }

        }
    }

    // displays only payments made
    public static void viewPayments(ArrayList<Transaction> transactions) {
        // loops through the file and displays payments
        for (Transaction t : transactions) {
            // if the amount is less than 0 (negative) then it gets displayed
            if (t.getAmount() < 0) {
                System.out.printf("%tF|%tT|%s|%s|$%.2f\n",
                        t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            }

        }
    }

    // displays reports screen
    public static int reportsScreen() {

        // prompts user of how they would like their report or go back to the ledger screen
        System.out.println("R) Reports - What kind of report would you like to run?\n" +
                "\t1) Month To Date\n" +
                "\t2) Previous Month\n" +
                "\t3) Year To Date\n" +
                "\t4) Previous Year\n" +
                "\t5) Search by Vendor\n" +
                "\t0) Back to Ledger Screen");
        // returns user input
        return userInput.nextInt();
    }

    /*
    method for month to date report
     */


    /*
    method for previous month report
     */


    /*
    method for year to date report
     */


    /*
    method for previous year report
     */


    /*
    method for searching by vendor
     */

    // creates file reader
    public static BufferedReader fileReader(String fileName) {
        try {
            // finds the file in the specific path and creates file and buffered reader
            FileReader inventoryFile = new FileReader("src/main/resources/" + fileName);
            return new BufferedReader(inventoryFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //creates file writer
    public static BufferedWriter fileWriter(String fileName) {
        try {
            // finds the file in the specific path and creates file and buffered writer
            // appends all new lines to existing file instead of overriding it
            FileWriter inventoryFile = new FileWriter("src/main/resources/" + fileName, true);
            return new BufferedWriter(inventoryFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // creates array list of transactions to return
    public static ArrayList<Transaction> getTransaction() {
        // creates new array list that takes in the Transaction class
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        try {
            // calls on file reader class and creates the file and buffered reader which takes in the csv file
            BufferedReader bufferReader = fileReader("transactions.csv");
            // reads the first header row of strings
            bufferReader.readLine();
            // variable to store the lines in the file read
            String transactionsFile;
            // reads until the file returns null
            while ((transactionsFile = bufferReader.readLine()) != null) {
                // splits the file at the pipe and stores it in an array
                String[] transactionParts = transactionsFile.split("\\|");
                // creates new transaction object to assign the split parts into the constructor parameters
                Transaction transactionDetails = new Transaction(LocalDate.parse(transactionParts[0]), LocalTime.parse(transactionParts[1]),
                        transactionParts[2], transactionParts[3], Double.parseDouble(transactionParts[4]));
                // adds the details into new transactions array list
                transactions.add(transactionDetails);
            }
            
            // closes buffered reader
            bufferReader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return transactions;
    }
}
