import java.io.*;
import java.util.*;
import java.time.LocalDate;

class Expense {
    String date;
    String category;
    double amount;

    public Expense(String date, String category, double amount) {
        this.date = date;
        this.category = category;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return date + "," + category + "," + amount;
    }
}

public class ExpenseTracker {
    private static final String FILE_NAME = "expenses.csv";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== CLI Expense Tracker ===");

        while (true) {
            System.out.println("\n1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addExpense(scanner);
                    break;
                case "2":
                    viewExpenses();
                    break;
                case "3":
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addExpense(Scanner scanner) {
        System.out.print("Enter category (e.g., Food, Transport): ");
        String category = scanner.nextLine();
        
        System.out.print("Enter amount: ");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Returning to menu.");
            return;
        }

        String date = LocalDate.now().toString();
        Expense expense = new Expense(date, category, amount);

        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(expense.toString());
            System.out.println("Expense added successfully!");
        } catch (IOException e) {
            System.out.println("Error saving expense: " + e.getMessage());
        }
    }

    private static void viewExpenses() {
        System.out.println("\n--- Expense History ---");
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            double total = 0;
            System.out.printf("%-15s %-15s %-10s%n", "Date", "Category", "Amount");
            System.out.println("------------------------------------------");
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    System.out.printf("%-15s %-15s $%-10.2f%n", parts[0], parts[1], Double.parseDouble(parts[2]));
                    total += Double.parseDouble(parts[2]);
                }
            }
            System.out.println("------------------------------------------");
            System.out.printf("Total Expenses: $%.2f%n", total);
        } catch (FileNotFoundException e) {
            System.out.println("No expenses recorded yet.");
        } catch (IOException e) {
            System.out.println("Error reading expenses: " + e.getMessage());
        }
    }
}
