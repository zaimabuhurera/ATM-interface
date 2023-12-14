import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATMinterface {

    private static Map<String, Double> accountBalances = new HashMap<>();
    private static Map<String, String> accountPins = new HashMap<>();
    private static String currentUser;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeAccounts();

        System.out.println("Welcome to the ATM!");

        while (true) {
            if (authenticateUser()) {
                displayMenu();
                int choice = getUserChoice();

                switch (choice) {
                    case 1:
                        displayBalance();
                        break;
                    case 2:
                        deposit();
                        break;
                    case 3:
                        withdraw();
                        break;
                    case 4:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Authentication failed. Please try again.");
            }
        }
    }

    private static void initializeAccounts() {
        // Initialize sample accounts
        accountBalances.put("123456", 1000.0);
        accountPins.put("123456", "1234");

        accountBalances.put("789012", 1500.0);
        accountPins.put("789012", "5678");
    }

    private static boolean authenticateUser() {
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.next();

        if (accountBalances.containsKey(accountNumber)) {
            System.out.print("Enter your PIN: ");
            String pin = scanner.next();

            if (accountPins.get(accountNumber).equals(pin)) {
                currentUser = accountNumber;
                return true;
            }
        }

        return false;
    }

    private static void displayMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    private static int getUserChoice() {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    private static void displayBalance() {
        System.out.println("Your balance is: $" + accountBalances.get(currentUser));
    }

    private static void deposit() {
        System.out.print("Enter the amount to deposit: $");
        double amount = scanner.nextDouble();

        if (amount > 0) {
            accountBalances.put(currentUser, accountBalances.get(currentUser) + amount);
            System.out.println("Deposit successful. New balance: $" + accountBalances.get(currentUser));
        } else {
            System.out.println("Invalid amount. Please enter a positive value.");
        }
    }

    private static void withdraw() {
        System.out.print("Enter the amount to withdraw: $");
        double amount = scanner.nextDouble();

        if (amount > 0 && amount <= accountBalances.get(currentUser)) {
            accountBalances.put(currentUser, accountBalances.get(currentUser) - amount);
            System.out.println("Withdrawal successful. New balance: $" + accountBalances.get(currentUser));
        } else if (amount > accountBalances.get(currentUser)) {
            System.out.println("Insufficient funds. Withdrawal failed.");
        } else {
            System.out.println("Invalid amount. Please enter a positive value.");
        }
    }
}



