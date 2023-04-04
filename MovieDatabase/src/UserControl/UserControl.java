package UserControl;

import java.util.Scanner;

public class UserControl {
    private InputParser input;
    public UserControl() {
        input = new InputParser();
    }
    public void Menu() {
        printMenu();
        handleMenuSelection(input.getIntFromUserInput());
    }

    private void printMenu() {
        System.out.println("Choose action from menu:");
        System.out.println("\t1 ... Add new movie");
        System.out.println("\t2 ... Edit movie");
        System.out.println("\t3 ... Delete movie");
        System.out.println("\t4 ... Add movie rating");
        System.out.println("\t5 ... Search movie");
        System.out.println("\t6 ... Print all movies");
        System.out.println("\t7 ... Print staff members who worked on more than 1 movie");
        System.out.println("\t8 ... Print movies with certain staff member");
        System.out.println("\t9 ... Save movie info to file");
        System.out.println("\t10... Load movie from file");
        System.out.println("\t11... Exit program");
    }

    private void handleMenuSelection(int selection) {
        switch (selection) {
            case 1 -> System.out.println("Adding a new movie");
            case 2 -> System.out.println("Editing a movie");
            case 3 -> System.out.println("Deleting a movie movie");
            case 4 -> System.out.println("Adding a movie rating");
            case 5 -> System.out.println("Searching a movie");
            case 6 -> System.out.println("Printing all movies");
            case 7 -> System.out.println("Printing staff members");
            case 8 -> System.out.println("Printing movies with staff member");
            case 9 -> System.out.println("Saving movie to file");
            case 10 -> System.out.println("Loading movie from file");
            case 11 -> System.out.println("Exiting program");
            default -> {
                System.out.println("Unknown command. Please try again...");
                Menu();
            }
        }
    }

    private void clearConsole() {
        try
        {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
        }
        catch (final Exception e)
        {
            e.printStackTrace();
        }
    }
}
