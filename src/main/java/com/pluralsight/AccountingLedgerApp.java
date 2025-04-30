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

    // creates and formats the time stamps for make deposit and payment
    static DateTimeFormatter timeStampFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss");

    // creates scanner for user input
    static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {

        // load up bar
//        for (int i = 0; i <= 100; i++) {
//            Thread.sleep(30);
//            System.out.print("\rLoading... " + i + "%");
//        }

        ArrayList<Transaction> transactions = getTransaction();

        boolean homeRunning = true;
        while (homeRunning) {
            // calls home screen that returns user input
            String homeScreenOption = homeScreen();
            // matches user input main four options: D, P, L, X
            switch (homeScreenOption) {
                // Add Deposit
                case "D":
                    makeDeposit(transactions);
                    break;
                // Make Payment
                case "P":
                    makePayment(transactions);
                    break;
                // Ledger Screen
                case "L":
                    // variable for the ledger screen to keep running or return to home screen (case H)
                    boolean ledgerRunning = true;
                    while (ledgerRunning) {
                        // calls ledger screen that returns user input
                        String ledgerScreenOption = ledgerScreen();
                        // matches user input into one of five options: A, D, P, R, H
                        switch (ledgerScreenOption) {
                            // All
                            case "A":
                                viewAll(transactions);
                                break;
                            // Deposits
                            case "D":
                                viewDeposits(transactions);
                                break;
                            // Payments
                            case "P":
                                viewPayments(transactions);
                                break;
                            // Reports Screen
                            case "R":
                                // variable for the reports screen to keep running or return to ledger screen (case 0)
                                boolean reportsRunning = true;
                                while (reportsRunning) {
                                    // calls reports screen that returns user input
                                    int reportsScreenOption = reportsScreen();
                                    // matches user input to 0-5
                                    switch (reportsScreenOption) {
                                        // Month To Date
                                        case 1:
                                            monthToDate(transactions);
                                            break;
                                        // Previous Month
                                        case 2:
                                            previousMonth(transactions);
                                            break;
                                        // Year To Date
                                        case 3:
                                            yearToDate(transactions);
                                            break;
                                        // Previous Year
                                        case 4:
                                            previousYear(transactions);
                                            break;
                                        // Search by Vendor
                                        case 5:
                                            searchVendor(transactions);
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

        System.out.println("\n===============================================================");

        // prompts user what they would like to do or exit the app
        System.out.println("\nH) Home Screen - What may I assist you with today?\n" +
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

        double amount;

        while (true) {
            System.out.print("\nAmount: ");

            // checks if user entered a double
            if (userInput.hasNextDouble()) {
                amount = userInput.nextDouble();
                // eats leftover newline
                userInput.nextLine();
                // checks if amount is a positive number
                if (amount > 0) {
                    break;
                } else {
                    System.out.println("Invalid amount - Please enter a positive number!");
                    continue;
                }
            } else {
                System.out.println("Invalid entry - Please enter an amount using numeric numbers!");
                userInput.nextLine();
            }
        }


        try {
            // creates the time stamp and takes in current date and time
            LocalDateTime dateAndTime = LocalDateTime.now();

            // creates a new line to write on
            bufferWriter.newLine();

            // concatenates the time stamp with the formatter and the user inputs of their deposit information
            bufferWriter.write(dateAndTime.format(timeStampFormatter) + "|" + description + "|" + vendor + "|" + amount);
            // displays out the deposit just made
            System.out.println("Deposit: " + dateAndTime.format(timeStampFormatter) + "|" + description + "|" + vendor + "|" + amount +
                    " successfully made!");

            // closes out the writer
            bufferWriter.close();
        } catch (IOException e) {
            System.out.println("Deposit was not successfully made!" + e.getMessage());
        }

    }

    // makes a payment (negative number)
    public static void makePayment(ArrayList<Transaction> transactions) {

        // calls file writer method to starting writing to the file
        BufferedWriter bufferWriter = fileWriter("transactions.csv");

        // prompts user for payment information and stores the user inputs
        System.out.println("P) Make a Payment - Please enter your debit information:");

        System.out.print("\nDescription: ");
        String description = userInput.nextLine().trim();

        System.out.print("\nVendor: ");
        String vendor = userInput.nextLine().trim();

        double amount;

        while (true) {
            System.out.print("\nAmount: ");

            // checks if user entered a double
            if (userInput.hasNextDouble()) {
                amount = userInput.nextDouble();
                // eats leftover newline
                userInput.nextLine();
                // if amount is greater than zero then it turns it into a negative
                if (amount > 0) {
                    amount *= -1;
                    break;
                } else if (amount < 0){
                    break;
                }
            } else {
                System.out.println("Invalid entry - Please enter an amount using numeric numbers!");
                userInput.nextLine();
            }
        }

        try {
            // creates the time stamp and takes in current date and time
            LocalDateTime dateAndTime = LocalDateTime.now();

            // creates a new line to write on
            bufferWriter.newLine();

            // concatenates the time stamp with the formatter and the user inputs of their payment information
            bufferWriter.write(dateAndTime.format(timeStampFormatter) + "|" + description + "|" + vendor + "|" + amount);
            // displays out the payment just made
            System.out.println("Payment: " + dateAndTime.format(timeStampFormatter) + "|" + description + "|" + vendor + "|" + amount +
                    " successfully made!");

            // closes out the writer
            bufferWriter.close();
        } catch (Exception e) {
            System.out.println("Payment was not successfully made! " + e.getMessage());
        }

    }

    // displays ledger screen
    public static String ledgerScreen() {

        System.out.println("\n===============================================================");

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

    // displays all the ledgers
    public static void viewAll(ArrayList<Transaction> transactions) {

        System.out.println("===============================================================");

        // loops through the file and displays all transactions
        for (Transaction t : transactions) {
            System.out.printf("%tF|%tT|%s|%s|$%.2f\n",
                    t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
        }
        // displays exit question
        ledgerScreenExit();
    }

    // displays only deposits made
    public static void viewDeposits(ArrayList<Transaction> transactions) {

        System.out.println("===============================================================");

        // loops through the file and displays deposits
        for (Transaction t : transactions) {
            // if the amount is greater than 0 (positive) then it gets displayed
            if (t.getAmount() > 0) {
                System.out.printf("%tF|%tT|%s|%s|$%.2f\n",
                        t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            }
        }
        // displays exit question
        ledgerScreenExit();
    }

    // displays only payments made
    public static void viewPayments(ArrayList<Transaction> transactions) {

        System.out.println("===============================================================");

        // loops through the file and displays payments
        for (Transaction t : transactions) {
            // if the amount is less than 0 (negative) then it gets displayed
            if (t.getAmount() < 0) {
                System.out.printf("%tF|%tT|%s|%s|$%.2f\n",
                        t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            }
        }
        // displays exit question
        ledgerScreenExit();
    }

    // displays reports screen
    public static int reportsScreen() {

        System.out.println("\n===============================================================");

        // prompts user of how they would like their report or go back to the ledger screen
        System.out.println("R) Reports - What kind of report would you like to run?\n" +
                "\t1) Month To Date\n" +
                "\t2) Previous Month\n" +
                "\t3) Year To Date\n" +
                "\t4) Previous Year\n" +
                "\t5) Search by Vendor\n" +
                "\t0) Back to Ledger Screen");

        // returns user input
        int option = userInput.nextInt();

        // eats leftover newline
        userInput.nextLine();

        return option;
    }

    // displays report of month to current date
    public static void monthToDate(ArrayList<Transaction> transactions) {

        System.out.println("===============================================================");

        // gets current date and gets month number and year from current date
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();

        // goes through array and gets transaction dates
        for (Transaction t : transactions) {
            LocalDate target = t.getDate();
            // compares dates in array to current month and year to display transactions only of this month
            if (target.getMonthValue() == currentMonth && target.getYear() == currentYear) {
                System.out.printf("%tF|%tT|%s|%s|$%.2f\n",
                        t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            }
        }
        // displays exit question
        reportScreenExit();
    }

    // displays report of previous month
    public static void previousMonth(ArrayList<Transaction> transactions) {

        System.out.println("===============================================================");

        // gets current date to subtract month by 1 to get previous month value
        LocalDate currentDate = LocalDate.now();
        LocalDate previous = currentDate.minusMonths(1);
        int previousMonth = previous.getMonthValue();
        int currentYear = currentDate.getYear();

        // goes through array and gets transaction dates
        for (Transaction t : transactions) {
            LocalDate target = t.getDate();
            // compares dates in array to previous month and current year to display transactions only of previous month
            if (target.getMonthValue() == previousMonth && target.getYear() == currentYear) {
                System.out.printf("%tF|%tT|%s|%s|$%.2f\n",
                        t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            }
        }
        // displays exit question
        reportScreenExit();
    }

    // displays report of year to current date
    public static void yearToDate(ArrayList<Transaction> transactions) {

        System.out.println("===============================================================");

        // gets current year from the current date
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        // goes through array and gets transaction dates
        for (Transaction t : transactions) {
            LocalDate target = t.getDate();
            // compares dates in array to current year
            if (target.getYear() == currentYear) {
                System.out.printf("%tF|%tT|%s|%s|$%.2f\n",
                        t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            }
        }
        // displays exit question
        reportScreenExit();
    }

    // displays report of previous year
    public static void previousYear(ArrayList<Transaction> transactions) {

        System.out.println("===============================================================");

        // gets previous year by subtracting 1 from current year
        LocalDate currentDate = LocalDate.now();
        LocalDate previous = currentDate.minusYears(1);
        int previousYear = previous.getYear();

        // goes through array and gets transaction dates
        for (Transaction t : transactions) {
            LocalDate target = t.getDate();
            // compares dates in array to previous year
            if (target.getYear() == previousYear) {
                System.out.printf("%tF|%tT|%s|%s|$%.2f\n",
                        t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            }
        }
        // displays exit question
        reportScreenExit();
    }

    // displays vendor when searched
    public static void searchVendor(ArrayList<Transaction> transactions) {

        System.out.println("===============================================================");

        // prompts user to enter vendor name
        System.out.println("5) Search by Vendor - Please enter Vendor's name");

        while (true) {
            // stores user input
            System.out.print("\nVendor: ");
            String vendorName = userInput.nextLine().trim();

            // displays user input
            System.out.println("\n" + vendorName + "'s Transactions:");
            System.out.println("===============================================================");

            // loops through transactions and gets the vendor to match user input and displays them
            for (Transaction t : transactions) {
                if (t.getVendor().equalsIgnoreCase(vendorName)) {
                    System.out.printf("%tF|%tT|%s|%s|$%.2f\n",
                            t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
                }
            }

            System.out.println("===============================================================");

            // asks user if they want to search again or exit to go back to Reports Screen
            System.out.println("\nWould you like to search for another Vendor? (Y/N)");
            String choice = userInput.nextLine();

            // if user doesn't enter yes then exits
            if (!choice.equalsIgnoreCase("Y")) {
                break;
            }
        }

    }

    // exit question for ledger screen options
    public static void ledgerScreenExit() {

        System.out.println("===============================================================");

        // simple question to keep user inside the ledger view instead of immediately coming back to Ledgers Screen
        while (true) {
            System.out.println("\nWould you like to move back to - L) Ledgers? (Y)");
            String choice = userInput.nextLine();
            if (choice.equalsIgnoreCase("Y")) {
                break;
            }
        }
    }

    // exit question for reports screen options
    public static void reportScreenExit() {

        System.out.println("===============================================================");

        // simple question to keep user inside the report instead of immediately coming back to Reports Screen
        while (true) {
            System.out.println("\nWould you like to move back to - R) Reports? (Y)");
            String choice = userInput.nextLine();
            if (choice.equalsIgnoreCase("Y")) {
                break;
            }
        }
    }

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
            // reverses order from newest at top to the oldest at bottom
            Collections.reverse(transactions);
            // closes buffered reader
            bufferReader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return transactions;
    }
}
