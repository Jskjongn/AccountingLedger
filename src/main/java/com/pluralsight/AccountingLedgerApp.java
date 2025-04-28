package com.pluralsight;

import java.util.Scanner;

public class AccountingLedgerApp {

    // creates scanner for user input
    static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {

        // variable for the home screen to keep running until false (case X)
        boolean homeRunning = true;
        while (homeRunning) {
            // calls home screen method that returns user input
            String homeScreenOption = homeScreen();
            // matches user input main four options: D, P, L, X
            switch (homeScreenOption) {
                // Add Deposit
                case "D":
                    break;
                // Make Payment
                case "P":
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
                                break;
                            // Deposits
                            case "D":
                                break;
                            // Payments
                            case "P":
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

    /*
    method for making a deposit
     */


    /*
    method for making a payment
     */


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

    /*
    method for viewing all ledgers
     */


    /*
    method for viewing only deposits
     */


    /*
    method for viewing only payments
     */


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
}
