
import PackageWalletSystem.User;

import java.util.Scanner;

import java.util.ArrayList;

public class PaymentSystem {
    static ArrayList<User> userList = new ArrayList<User>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Payment System ---\n");
            System.out.println("1. Create account");
            System.out.println("2. Check balance by username");
            System.out.println("3. Check balance by account ID");
            System.out.println("4. Add funds");
            System.out.println("5. Deduct funds");
            System.out.println("6. Transfer funds");
            System.out.println("7. Display all user details");
            System.out.println("0. Exit");

            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createUser();
                    break;
                case 2:
                    checkBalanceByUsername();
                    break;
                case 3:
                    checkBalanceByAccountId();
                    break;
                case 4:
                    addFunds();
                    break;
                case 5:
                    deductFunds();
                    break;
                case 6:
                    transferFunds();
                    break;
                case 7:
                    displayAllUserDetails();
                    break;
                case 0:
                    System.out.println("\nExiting program. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }

    public static void displayAllUserDetails() {
        System.out.println("\n--- All User Details ---");
        for (User user : userList) {
            System.out.printf("\nUsername: %s\nAccount Type: %s\nAccount ID: %s\nBalance: $%.2f\n",
                    user.getUsername(), user.getAccountType(), user.getAccountId(), user.getBalance());
        }
    }

    private static void transferFunds() {
        System.out.print("Enter username of sender: ");
        Scanner scanner = new Scanner(System.in);
        String senderUsername = scanner.nextLine();

// Find sender user by username
        User sender = null;
        for (User user : userList) {
            if (user.getUsername().equals(senderUsername)) {
                sender = user;
                break;
            }
        }

        if (sender == null) {
            System.out.println("\nSender user not found. Please try again.");
            return;
        }

        System.out.print("Enter username of receiver: ");
        String receiverUsername = scanner.nextLine();

// Find receiver user by username
        User receiver = null;
        for (User user : userList) {
            if (user.getUsername().equals(receiverUsername)) {
                receiver = user;
                break;
            }
        }

        if (receiver == null) {
            System.out.println("\nReceiver user not found. Please try again.");
            return;
        }

        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();

        if (sender.getBalance() < amount) {
            System.out.println("\nInsufficient balance. Please try again.");
            return;
        }

        sender.getWallet().deductFunds(amount);
        receiver.getWallet().addFunds(amount);

        System.out.printf("\n$%.2f transferred from %s to %s successfully.\nSender's new balance: $%.2f\nReceiver's new balance: $%.2f\n",
                amount, sender.getUsername(), receiver.getUsername(), sender.getBalance(), receiver.getBalance());

    }

    // Method to deduct funds from user account
    public static void deductFunds() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Deduct Funds ---");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        // Find user by username and deduct funds
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                System.out.print("Enter amount to deduct: ");
                double amount = scanner.nextDouble();
                if (user.getBalance() < amount) {
                    System.out.println("\nInsufficient balance. Please try again.");
                    return;
                }
                user.getWallet().deductFunds(amount);
                System.out.printf("\n$%.2f deducted from account successfully. New balance: $%.2f\n",
                        amount, user.getBalance());
                return;
            }
        }

        System.out.println("\nUser not found. Please try again.");

    }

    // Method to create a new user account
    public static void createUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Create User Account ---");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        // Check if username already exists
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                System.out.println("\nUsername already exists. Please try again.");
                return;
            }
        }

        System.out.print("Enter account type (savings/checking): ");
        String accountType = scanner.nextLine();

        System.out.print("Enter account ID: ");
        String accountId = scanner.nextLine();

        System.out.print("Enter initial balance: ");
        double initialBalance = scanner.nextDouble();

        // Create new user and add to user list
        User user = new User(username, accountType, accountId, initialBalance);
        userList.add(user);

        System.out.println("\nUser account created successfully.");
    }

    // Method to check balance by username
    public static void checkBalanceByUsername() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Check Balance by Username ---");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        // Find user by username and print balance
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                System.out.printf("\nAccount ID: %s\nAccount Type: %s\nBalance: $%.2f\n",
                        user.getAccountId(), user.getAccountType(), user.getBalance());
                return;
            }
        }

        System.out.println("\nUser not found. Please try again.");
    }

    // Method to check balance by account ID
    public static void checkBalanceByAccountId() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Check Balance by Account ID ---");

        System.out.print("Enter account ID: ");
        String accountId = scanner.nextLine();

        // Find user by account ID and print balance
        for (User user : userList) {
            if (user.getAccountId() == Integer.parseInt(accountId)) {
                System.out.printf("\nUsername: %s\nAccount Type: %s\nBalance: $%.2f\n",
                        user.getUsername(), user.getAccountType(), user.getBalance());
                return;
            }
        }

        System.out.println("\nUser not found. Please try again.");
    }


    // Method to add funds to user account
    public static void addFunds() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Add Funds ---");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        // Find user by username and add funds
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                System.out.print("Enter amount to add: ");
                double amount = scanner.nextDouble();
                user.getWallet().addFunds(amount);
                System.out.printf("\n$%.2f added to account successfully. New balance: $%.2f\n",
                        amount, user.getBalance());
                return;
            }
        }

        System.out.println("\nUser not found. Please try again.");
    }
}