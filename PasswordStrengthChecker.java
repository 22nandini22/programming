import java.util.Scanner;

public class PasswordStrengthChecker {

    // Function to check password strength
    public static int checkPassword(String password) {

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        // Check each character
        for (int i = 0; i < password.length(); i++) {

            char ch = password.charAt(i);

            if (Character.isUpperCase(ch)) {
                hasUppercase = true;
            }
            else if (Character.isLowerCase(ch)) {
                hasLowercase = true;
            }
            else if (Character.isDigit(ch)) {
                hasNumber = true;
            }
            else {
                hasSpecial = true;
            }
        }

        // Check minimum length
        boolean hasMinimumLength = password.length() >= 8;

        // Display analysis
        System.out.println("\n========== PASSWORD ANALYSIS ==========");

        if (hasMinimumLength)
            System.out.println("[T] At least 8 characters");
        else
            System.out.println("[F] At least 8 characters");

        if (hasUppercase)
            System.out.println("[T] Contains uppercase letter");
        else
            System.out.println("[F] Contains uppercase letter");

        if (hasLowercase)
            System.out.println("[T] Contains lowercase letter");
        else
            System.out.println("[F] Contains lowercase letter");

        if (hasNumber)
            System.out.println("[T] Contains a number");
        else
            System.out.println("[F] Contains a number");

        if (hasSpecial)
            System.out.println("[T] Contains a special character");
        else
            System.out.println("[F] Contains a special character");

        // Calculate score
        int score = 0;

        if (hasMinimumLength)
            score++;

        if (hasUppercase)
            score++;

        if (hasLowercase)
            score++;

        if (hasNumber)
            score++;

        if (hasSpecial)
            score++;

        // Display strength
        System.out.print("\nStrength: ");

        if (score <= 2) {
            System.out.println("WEAK");
        }
        else if (score <= 4) {
            System.out.println("MEDIUM");
        }
        else {
            System.out.println("STRONG");
        }

        System.out.println("Requirements satisfied: " + score + "/5");
        System.out.println("========================================");

        return score;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("       PASSWORD STRENGTH CHECKER");
        System.out.println("========================================");

        boolean testing = true;

        while (testing) {

            System.out.print("\nEnter your password: ");
            String password = sc.nextLine();

            int score = checkPassword(password);

            // If password is weak
            if (score <= 2) {

                System.out.println("\nYour password is WEAK.");

                System.out.print("Do you want to re-enter the password or exit?");
                System.out.print("\nEnter R for Re-enter or E for Exit: ");

                String choice = sc.nextLine();

                if (choice.equalsIgnoreCase("R")) {
                    System.out.println("\nLet's try again!");
                    continue;
                }
                else if (choice.equalsIgnoreCase("E")) {
                    testing = false;
                }
                else {
                    System.out.println("\nInvalid choice. Exiting...");
                    testing = false;
                }
            }

            // If password is medium or strong
            else {
                testing = false;
            }
        }

        System.out.println("\nThankyou for testing your password");
        System.out.println("(: Have a nice day :)");

        sc.close();
    }
}