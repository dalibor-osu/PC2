package UserControl;

import java.util.Scanner;

public class InputParser {
    private Scanner scanner;
    public InputParser() {
        scanner = new Scanner(System.in);
    }

    public int getIntFromUserInput() {
        try {
            int i = scanner.nextInt();
            scanner.nextLine();
            return i;
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Your input was not a valid integer. Please try again...");
            scanner.next();
            return getIntFromUserInput();
        }
    }

    public String getStringFromUserInput() {
        try {
            return scanner.nextLine();
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Something went wrong. Please try again...");
            return getStringFromUserInput();
        }
    }
}
