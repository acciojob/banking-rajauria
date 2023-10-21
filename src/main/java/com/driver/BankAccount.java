package com.driver;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;

    public BankAccount(String name, double balance, double minBalance) {
        this.name = name;
        this.balance = balance;
        this.minBalance = minBalance;
    }

    public String getName (){
        return name;
    }

    public double getBalance(){
        return  balance;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception {
        if (digits <= 0 || sum < 0 || sum > 9 * digits) {
            throw new Exception("Account Number cannot be generated");
        }

        StringBuilder accountNumber = new StringBuilder();
        int remainingSum = sum;

        for (int i = 0; i < digits - 1; i++) {
            int maxPossibleDigit = Math.min(remainingSum, 9);
            int randomDigit = (int) (Math.random() * (maxPossibleDigit + 1));
            accountNumber.append(randomDigit);
            remainingSum -= randomDigit;
        }

        accountNumber.append(remainingSum);

        return accountNumber.toString();
    }

    public void deposit(double amount) {
        // Add amount to balance
        this.balance += amount;
    }

    public void withdraw(double amount) throws Exception {
        if (this.balance - amount > minBalance) {
            this.balance -= amount;
        }
        else throw new Exception("Insufficient Balance");
        // Withdraw the specified amount

    }
}
