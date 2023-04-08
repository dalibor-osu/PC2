package UserControl;

import Database.DataContainer;
import Movie.AnimatedMovie;
import Movie.FeatureMovie;
import Movie.Movie;

import java.util.Scanner;

public class UserControl {
    private InputParser input;
    private DataContainer data;
    private boolean end = false;
    public UserControl() {
        data = new DataContainer();
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
            case 1:
                addMovie();
                break;

            case 2:
                System.out.println("Editing a movie");
                break;

            case 3:
                System.out.println("Deleting a movie movie");
                break;

            case 4:
                System.out.println("Adding a movie rating");
                break;

            case 5:
                System.out.println("Searching a movie");
                break;

            case 6:
                System.out.println("Printing all movies");
                break;

            case 7:
                System.out.println("Printing staff members");
                break;

            case 8:
                System.out.println("Printing movies with staff member");
                break;

            case 9:
                System.out.println("Saving movie to file");
                break;

            case 10:
                System.out.println("Loading movie from file");
                break;

            case 11:
                System.out.println("Exiting program");
                end = true;
                break;

            default:
                System.out.println("Unknown command. Please try again...");
                break;
        }

        if (!end) { Menu(); }
    }

    private void addMovie() {
        String title, director;
        int year, age;
        Movie movie;

        System.out.println("Enter movie title:");
        title = input.getStringFromUserInput();

        System.out.println("Enter movie director:");
        director = input.getStringFromUserInput();

        System.out.println("Enter movie release year:");
        year = input.getIntFromUserInput();

        System.out.println("Is this movie animated? ([Y]es/[N]o):");
        if (input.getStringFromUserInput().equals("Y")) {
            System.out.println("Enter recommended age:");
            age = input.getIntFromUserInput();
            movie = new AnimatedMovie(title, director, year, age);
        } else {
            movie = new FeatureMovie(title, director, year);
        }

        data.addMovie(movie);

        System.out.println("Added movie: ");
        System.out.println(movie);
    }
}
