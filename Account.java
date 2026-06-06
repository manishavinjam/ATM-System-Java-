package atm;

public class Account {

    private String accountHolder;
    private String accountNumber;
    private String pin;
    private double balance;

    // Constructor
    public Account(String accountHolder, String accountNumber, String pin, double initialBalance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = initialBalance;
    }

    // Validate PIN
    public boolean validatePin(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    // Deposit money
    public String deposit(double amount) {
        if (amount <= 0) {
            return "Invalid amount! Please enter a positive value.";
        }
        balance += amount;
        return "Successfully deposited ₹" + String.format("%.2f", amount);
    }

    // Withdraw money
    public String withdraw(double amount) {
        if (amount <= 0) {
            return "Invalid amount! Please enter a positive value.";
        }
        if (amount > balance) {
            return "Insufficient balance! Your current balance is ₹" + String.format("%.2f", balance);
        }
        balance -= amount;
        return "Successfully withdrawn ₹" + String.format("%.2f", amount);
    }

    // Check balance
    public double getBalance() {
        return balance;
    }

    // Getters
    public String getAccountHolder() {
        return accountHolder;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}

