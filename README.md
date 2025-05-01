# Accounting Ledger App

## Description

The **Accounting Ledger App** is a simple command-line application designed for managing financial transactions for business or personal use. It allows users to make deposits, make payments, view ledgers and previous transactions, and view reports based on different time frames (like month-to-date, year-to-date, etc.). This app stores all data in a .csv file, which is utilized throughout the program for both reading and writing operations.

## Features

### 1. **Home Screen**
```
H) Home Screen - How may I assist you today?
---------------------------------------------------------------
	D) Make a Deposit
	P) Make a Payment (Debit)
	L) View Ledgers and Previous Transactions
	X) Exit the Application
```

### 2. **Make a Deposit**
- Add a positive transaction to the ledger (e.g., income or deposits).
- You can provide a description, vendor name, and the deposit amount.

### 3. **Make a Payment**
- Record a payment (negative transaction) to the ledger.
- Similar to deposits, you can provide a description, vendor name, and the payment amount.

### 4. **Ledger Screen**
```
L) Ledgers - How would you like to view your Transactions?
---------------------------------------------------------------
	A) All Transactions
	D) Deposits Only
	P) Payments Only
	R) View Reports
	H) Back to Home Screen
```

### 5. **View Transactions (Ledgers)**
- View a list of all transactions.
- Filter to view only **deposits** or **payments**.

### 6. **Reports Screen**
```
R) Reports - What kind of Report would you like to run?
---------------------------------------------------------------
	1) Month To Date
	2) Previous Month
	3) Year To Date
	4) Previous Year
	5) Search by Vendor
	0) Back to Ledger Screen
```

### 7. **View Reports**
- Generate reports based on time periods:
    - **Month-to-Date:** View transactions from the current month.
    - **Previous Month:** View transactions from the previous month.
    - **Year-to-Date:** View transactions from the current year.
    - **Previous Year:** View transactions from the previous year.
    - **Search by Vendor:** View all transactions related to a specific vendor.

### 8. **Exit the Application**
- Safely exit the app with a goodbye message.

## Interesting piece of code
- This code is most interesting to me because it introduces the concept of methods and the process of invoking them, which is a new area for me. I found this experience to be both challenging and rewarding, as it pushed me beyond my comfort zone. It enabled me to integrate the knowledge I have gained over the past three weeks into a single cohesive piece of code. Rather than relying solely on writing everything within switch statements, this approach allowed me to modularize the code by calling methods from various parts of the program.



```
// gets transactions list to use in switch statements
        ArrayList<Transaction> transactions = getTransaction();

        // variable for the home screen to keep running or exit the app (case X)
        boolean homeRunning = true;
        while (homeRunning) {
            // calls home screen that returns user input
            String homeScreenOption = homeScreen();
            // matches user input main four options: D, P, L, X
            switch (homeScreenOption) {
                // Add Deposit
                case "D":
                    makeDeposit(transactions);
                    Thread.sleep(500);
                    break;
                // Make Payment
                case "P":
                    makePayment(transactions);
                    Thread.sleep(500);
                    break;
                // Ledger Screen
                case "L":
                    Thread.sleep(500);
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
                                Thread.sleep(500);
                                break;
                            // Deposits
                            case "D":
                                viewDeposits(transactions);
                                Thread.sleep(500);
                                break;
                            // Payments
                            case "P":
                                viewPayments(transactions);
                                Thread.sleep(500);
                                break;
                            // Reports Screen
                            case "R":
                                Thread.sleep(500);
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
                                            Thread.sleep(500);
                                            break;
                                        // Previous Month
                                        case 2:
                                            previousMonth(transactions);
                                            Thread.sleep(500);
                                            break;
                                        // Year To Date
                                        case 3:
                                            yearToDate(transactions);
                                            Thread.sleep(500);
                                            break;
                                        // Previous Year
                                        case 4:
                                            previousYear(transactions);
                                            Thread.sleep(500);
                                            break;
                                        // Search by Vendor
                                        case 5:
                                            searchVendor(transactions);
                                            Thread.sleep(500);
                                            break;
                                        // Returns back to Ledger Screen
                                        case 0:
                                            System.out.println("\n0) Returning back to Ledger Screen!");
                                            System.out.println("---------------------------------------------------------------");
                                            Thread.sleep(1500);
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
                                System.out.println("\nH) Returning back to Home Screen!");
                                System.out.println("---------------------------------------------------------------");
                                Thread.sleep(1500);
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
                    System.out.println("\nX) You Exited the app, have a great day!");
                    System.out.println("---------------------------------------------------------------");
                    Thread.sleep(1500);
                    homeRunning = false;
                    break;
                // displays if user didn't choose a correct option
                default:
                    System.out.println("Invalid option - Please choose one of the valid options!");
                    break;
            }
        }
```