package com.pluralsight;

import java.util.Scanner;

public class AccountingLedgerApp {

    // creates scanner for user input
    static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {


        boolean homeRunning = true;
        while (homeRunning) {
            // calls home screen method that returns user input
            String homeScreenOption = homeScreen();
            // compares user input to the different cases
            switch (homeScreenOption) {
                case "D":
                    break;
                case "P":
                    break;
                case "L":
                    boolean ledgerRunning = true;
                    while (ledgerRunning) {

                        String ledgerScreenOption = ledgerScreen();

                        switch (ledgerScreenOption) {
                            case "A":
                                break;
                            case "D":
                                break;
                            case "P":
                                break;
                            case "R":
                                boolean reportsRunning = true;
                                while (reportsRunning) {

                                    int reportsScreenOption = reportsScreen();

                                    switch (reportsScreenOption) {
                                        case 1:
                                            break;
                                        case 2:
                                            break;
                                        case 3:
                                            break;
                                        case 4:
                                            break;
                                        case 5:
                                            break;
                                        case 0:
                                            reportsRunning = false;
                                            break;
                                        default:
                                            System.out.println("Invalid option - Please choose one of the valid options!");
                                            break;
                                    }
                                }
                                break;
                            case "H":
                                ledgerRunning = false;
                                break;
                            default:
                                System.out.println("Invalid option - Please choose one of the valid options!");
                                break;
                        }
                    }
                    break;
                case "X":
                    homeRunning = false;
                    break;
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

        int userOption = userInput.nextInt();
        userInput.nextLine();
        // returns user input
        return userOption;
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
