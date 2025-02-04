import java.io.*;
import java.util.*;

public class BankManagement {
    private static Map<String, BankAccount> accounts = new HashMap<>();
    private static List<Transaction> transactionHistory = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Bank Management System =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Transfer Funds");
            System.out.println("6. View Transaction History");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    transferFunds();
                    break;
                case 6:
                    viewTransactionHistory();
                    break;
                case 7:
                    saveTransactionHistory();
                    System.out.println("Exiting system. Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again!");
            }
        }
    }

    // Create Account
    private static void createAccount() {
        System.out.print("\nEnter account holder's name: ");
        sc.nextLine();  // To consume the newline character
        String name = sc.nextLine();
        System.out.print("Enter account number: ");
        String accountNumber = sc.nextLine();
        System.out.print("Enter initial deposit amount: ");
        double initialDeposit = sc.nextDouble();

        if (accounts.containsKey(accountNumber)) {
            System.out.println("Account already exists with this number!");
        } else {
            BankAccount newAccount = new BankAccount(name, accountNumber, initialDeposit);
            accounts.put(accountNumber, newAccount);
            System.out.println("Account created successfully!");
        }
    }

    // Deposit Money
    private static void depositMoney() {
        System.out.print("\nEnter account number: ");
        String accountNumber = sc.next();
        if (accounts.containsKey(accountNumber)) {
            System.out.print("Enter deposit amount: ");
            double amount = sc.nextDouble();
            BankAccount account = accounts.get(accountNumber);
            account.deposit(amount);
            transactionHistory.add(new Transaction(accountNumber, "Deposit", amount));
            System.out.println("Deposited successfully!");
        } else {
            System.out.println("Account not found!");
        }
    }

    // Withdraw Money
    private static void withdrawMoney() {
        System.out.print("\nEnter account number: ");
        String accountNumber = sc.next();
        if (accounts.containsKey(accountNumber)) {
            System.out.print("Enter withdrawal amount: ");
            double amount = sc.nextDouble();
            BankAccount account = accounts.get(accountNumber);
            account.withdraw(amount);
            transactionHistory.add(new Transaction(accountNumber, "Withdraw", amount));
            System.out.println("Withdrawal successful!");
        } else {
            System.out.println("Account not found!");
        }
    }

    // Check Balance
    private static void checkBalance() {
        System.out.print("\nEnter account number: ");
        String accountNumber = sc.next();
        if (accounts.containsKey(accountNumber)) {
            BankAccount account = accounts.get(accountNumber);
            System.out.println("Balance: " + account.getBalance());
        } else {
            System.out.println("Account not found!");
        }
    }

    // Transfer Funds
    private static void transferFunds() {
        System.out.print("\nEnter your account number: ");
        String fromAccountNumber = sc.next();
        if (accounts.containsKey(fromAccountNumber)) {
            System.out.print("Enter recipient account number: ");
            String toAccountNumber = sc.next();
            if (accounts.containsKey(toAccountNumber)) {
                System.out.print("Enter amount to transfer: ");
                double amount = sc.nextDouble();
                BankAccount fromAccount = accounts.get(fromAccountNumber);
                BankAccount toAccount = accounts.get(toAccountNumber);
                if (fromAccount.getBalance() >= amount) {
                    fromAccount.withdraw(amount);
                    toAccount.deposit(amount);
                    transactionHistory.add(new Transaction(fromAccountNumber, "Transfer", amount));
                    System.out.println("Transfer successful!");
                } else {
                    System.out.println("Insufficient balance for transfer!");
                }
            } else {
                System.out.println("Recipient account not found!");
            }
        } else {
            System.out.println("Your account not found!");
        }
    }

    // View Transaction History
    private static void viewTransactionHistory() {
        System.out.println("\n===== Transaction History =====");
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    // Save transaction history to file
    private static void saveTransactionHistory() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("transactions.txt"))) {
            oos.writeObject(transactionHistory);
        } catch (IOException e) {
            System.out.println("Error saving transaction history.");
        }
    }
}
