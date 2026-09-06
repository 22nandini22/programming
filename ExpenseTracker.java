import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Expense {
    double amount;
    String category;
    String description;

    Expense(double amount, String category, String description) {
        this.amount = amount;
        this.category = category;
        this.description = description;
    }
}

public class ExpenseTracker {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Expense> expenses = new ArrayList<>();

        while (true) {

            System.out.println("\n================================");
            System.out.println("         EXPENSE TRACKER");
            System.out.println("================================");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Show Summary");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice;

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {

                case 1:
                    double amount;

                    while (true) {
                        System.out.print("Enter amount: ");

                        try {
                            amount = Double.parseDouble(sc.nextLine());

                            if (amount <= 0) {
                                System.out.println("Amount must be greater than 0.");
                            } else {
                                break;
                            }

                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid amount.");
                        }
                    }

                    System.out.print("Enter category: ");
                    String category = sc.nextLine().trim();

                    while (category.isEmpty()) {
                        System.out.print("Category cannot be empty. Enter category: ");
                        category = sc.nextLine().trim();
                    }

                    System.out.print("Enter short description: ");
                    String description = sc.nextLine().trim();

                    while (description.isEmpty()) {
                        System.out.print("Description cannot be empty. Enter description: ");
                        description = sc.nextLine().trim();
                    }

                    Expense newExpense =
                            new Expense(amount, category, description);

                    expenses.add(newExpense);

                    System.out.println("Expense added successfully!");
                    break;

                case 2:
                    if (expenses.isEmpty()) {
                        System.out.println("\nNo expenses recorded yet.");
                    } else {
                        System.out.println("\n========== ALL EXPENSES ==========");

                        for (int i = 0; i < expenses.size(); i++) {

                            Expense expense = expenses.get(i);

                            System.out.println(
                                    (i + 1) + ". Rs" +
                                    String.format("%.2f", expense.amount) +
                                    " | " + expense.category +
                                    " | " + expense.description
                            );
                        }
                    }
                    break;

                case 3:
                    if (expenses.isEmpty()) {
                        System.out.println("\nNo expenses recorded yet.");
                        break;
                    }

                    double total = 0;

                    HashMap<String, Double> categoryTotals = new HashMap<>();

                    for (Expense expense : expenses) {

                        total += expense.amount;

                        categoryTotals.put(
                                expense.category,
                                categoryTotals.getOrDefault(expense.category, 0.0)
                                        + expense.amount
                        );
                    }

                    String highestCategory = "";
                    double highestAmount = 0;

                    for (String categoryName : categoryTotals.keySet()) {

                        double categoryAmount = categoryTotals.get(categoryName);

                        if (categoryAmount > highestAmount) {
                            highestAmount = categoryAmount;
                            highestCategory = categoryName;
                        }
                    }

                    System.out.println("\n========== SUMMARY ==========");
                    System.out.printf("Total amount spent: Rs%.2f%n", total);

                    System.out.println("\nAmount spent by category:");

                    for (String categoryName : categoryTotals.keySet()) {
                        System.out.printf(
                                "%s: Rs%.2f%n",
                                categoryName,
                                categoryTotals.get(categoryName)
                        );
                    }

                    System.out.println(
                            "\nHighest spending category: " +
                            highestCategory +
                            " (Rs" +
                            String.format("%.2f", highestAmount) +
                            ")"

                    );

                    break;

                case 4:
                    System.out.println("\nThank you for using Expense Tracker!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please choose 1-4.");
            }
        }
    }
}