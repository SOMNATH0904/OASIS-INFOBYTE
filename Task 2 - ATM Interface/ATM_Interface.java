package Circular_LinkedList;

import java.util.*;

class Transaction {
    String type;
    double amount;
    Date timestamp;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
        this.timestamp = new Date();
    }
}

class Account {
    String userId;
    String userPin;
    double balance;
    List<Transaction> transactions;

    public Account(String userId, String userPin) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction("Deposit", amount));
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactions.add(new Transaction("Withdraw", amount));
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void transfer(Account recipient, double amount) {
        if (balance >= amount) {
            balance -= amount;
            recipient.deposit(amount);
            transactions.add(new Transaction("Transfer", amount));
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void showTransactionHistory() {
        System.out.println("Transaction History:");
        for (Transaction transaction : transactions) {
            System.out.println(transaction.type + " - $" + transaction.amount + " at " + transaction.timestamp);
        }
    }
}

public class ATM_Interface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Account account = new Account("user123", "1234");

        System.out.print("Enter User ID: ");
        String userIdInput = scanner.nextLine();

        System.out.print("Enter User PIN: ");
        String userPinInput = scanner.nextLine();

        if (userIdInput.equals(account.userId) && userPinInput.equals(account.userPin)) {
            System.out.println("Login successful!");
            boolean continueRunning = true;

            while (continueRunning) {
                System.out.println("\nATM Menu:");
                System.out.println("1. Transactions History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Quit");
                System.out.print("Select an option: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        account.showTransactionHistory();
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        account.withdraw(withdrawAmount);
                        break;
                    case 3:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        account.deposit(depositAmount);
                        break;
                    case 4:
                        System.out.print("Enter recipient's User ID: ");
                        String recipientId = scanner.next();
                        System.out.print("Enter amount to transfer: ");
                        double transferAmount = scanner.nextDouble();
                        // For simplicity, assuming recipient's account is the same as the current account
                        account.transfer(account, transferAmount);
                        break;
                    case 5:
                        continueRunning = false;
                        System.out.println("Exiting ATM. Thank you!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            }
        } else {
            System.out.println("Login failed. Incorrect User ID or User PIN.");
        }
    }
}
