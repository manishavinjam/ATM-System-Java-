package atm;

import java.util.Scanner;

public class ATMSystem {

    private static Scanner scanner = new Scanner(System.in);

    // Display welcome banner
    public static void displayBanner() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║         WELCOME TO TECHNO BANK       ║");
        System.out.println("║           ATM SYSTEM v1.0            ║");
        System.out.println("╚══════════════════════════════════════╝");
    }

    // Display ATM main menu
    public static void displayMenu(String accountHolder) {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║   Hello, " + accountHolder + "!");
        System.out.println("║   Please select an option:           ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║  1. Check Balance                    ║");
        System.out.println("║  2. Deposit Money                    ║");
        System.out.println("║  3. Withdraw Money                   ║");
        System.out.println("║  4. Exit                             ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.print("Enter your choice: ");
    }

    // Login screen
    public static Account login(Account[] accounts) {
        System.out.println("\n--- ACCOUNT LOGIN ---");
        System.out.print("Enter Account Number: ");
        String accNumber = scanner.nextLine().trim();

        // Find account
        Account foundAccount = null;
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equals(accNumber)) {
                foundAccount = acc;
                break;
            }
        }

        if (foundAccount == null) {
            System.out.println("Account not found! Please try again.");
            return null;
        }

        // PIN validation with 3 attempts
        int attempts = 3;
        while (attempts > 0) {
            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine().trim();

            if (foundAccount.validatePin(pin)) {
                System.out.println("Login successful! Welcome, " + foundAccount.getAccountHolder());
                return foundAccount;
            } else {
                attempts--;
                if (attempts > 0) {
                    System.out.println("Incorrect PIN! " + attempts + " attempt(s) remaining.");
                } else {
                    System.out.println("Too many failed attempts! Card blocked.");
                }
            }
        }
        return null;
    }

    // Handle ATM operations
    public static void runATM(Account account) {
        boolean running = true;

        while (running) {
            displayMenu(account.getAccountHolder());

            String choiceStr = scanner.nextLine().trim();
            int choice;

            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            System.out.println();

            switch (choice) {
                case 1:
                    // Check Balance
                    System.out.println("========================================");
                    System.out.println("  Account Holder : " + account.getAccountHolder());
                    System.out.println("  Account Number : " + account.getAccountNumber());
                    System.out.println("  Current Balance: ₹" + String.format("%.2f", account.getBalance()));
                    System.out.println("========================================");
                    break;

                case 2:
                    // Deposit
                    System.out.print("Enter amount to deposit: ₹");
                    try {
                        double depositAmount = Double.parseDouble(scanner.nextLine().trim());
                        String depositResult = account.deposit(depositAmount);
                        System.out.println(depositResult);
                        System.out.println("Updated Balance: ₹" + String.format("%.2f", account.getBalance()));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount! Please enter a valid number.");
                    }
                    break;

                case 3:
                    // Withdraw
                    System.out.print("Enter amount to withdraw: ₹");
                    try {
                        double withdrawAmount = Double.parseDouble(scanner.nextLine().trim());
                        String withdrawResult = account.withdraw(withdrawAmount);
                        System.out.println(withdrawResult);
                        System.out.println("Updated Balance: ₹" + String.format("%.2f", account.getBalance()));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount! Please enter a valid number.");
                    }
                    break;

                case 4:
                    // Exit
                    System.out.println("========================================");
                    System.out.println("  Thank you for using TechnoBank ATM!  ");
                    System.out.println("  Please collect your card. Have a nice day!   ");
                    System.out.println("========================================");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice! Please select 1-4.");
            }
        }
    }

    public static void main(String[] args) {

        // Sample accounts (In real app, this would come from a database)
        Account[] accounts = {
            new Account("Ravi Kumar",   "ACC001", "1234", 25000.00),
            new Account("Priya Sharma", "ACC002", "5678", 50000.00),
            new Account("Arjun Reddy",  "ACC003", "9999", 10000.00)
        };

        displayBanner();

        boolean systemRunning = true;

        while (systemRunning) {
            System.out.println("\n--- SAMPLE ACCOUNTS FOR DEMO ---");
            System.out.println("ACC001 | PIN: 1234 | Balance: ₹25,000");
            System.out.println("ACC002 | PIN: 5678 | Balance: ₹50,000");
            System.out.println("ACC003 | PIN: 9999 | Balance: ₹10,000");
            System.out.println("--------------------------------");

            Account loggedInAccount = login(accounts);

            if (loggedInAccount != null) {
                runATM(loggedInAccount);
            }

            System.out.print("\nDo you want to use ATM again? (yes/no): ");
            String again = scanner.nextLine().trim();
            if (!again.equalsIgnoreCase("yes")) {
                System.out.println("\nThank you for banking with TechnoBank!");
                System.out.println("-- TechnoHacks Internship Project --");
                systemRunning = false;
            }
        }

        scanner.close();
    }
}
