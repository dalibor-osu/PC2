package UserControl;

import Database.DataContainer;
import Movie.*;

import java.util.List;

import static java.lang.System.*;

public class UserControl {
    private InputParser input;
    private DataContainer data;
    private boolean end = false;
    public UserControl() {
        data = new DataContainer();
        input = new InputParser();
    }
    public void Menu() {
        MenuPrinter.printMainMenu();
        handleMenuSelection(input.getIntFromUserInput());
    }

    private void handleMenuSelection(int selection) {
        switch (selection) {
            case 1:
                addMovie();
                break;

            case 2:
                editMovie();
                break;

            case 3:
                deleteMovie();
                break;

            case 4:
                out.println("Adding a movie rating");
                break;

            case 5:
                searchMovie();
                break;

            case 6:
                data.printMovies();
                break;

            case 7:
                data.printStaffMembersWithMovies();
                break;

            case 8:
                printMoviesWithPerson();
                break;

            case 9:
                out.println("Saving movie to file");
                break;

            case 10:
                out.println("Loading movie from file");
                break;

            case 11:
                exit();
                break;

            default:
                out.println("Unknown command. Please try again...");
                break;
        }

        if (!end) { Menu(); }
    }

    private void printMoviesWithPerson() {
        Person person = getPersonFromUser();
        data.printMoviesWithPerson(person);
    }

    private void editMovie() {
        String movieName;
        Movie movie;

        out.println("Enter the name of a movie to edit:");
        movieName = input.getStringFromUserInput();
        movie = data.getMovieByTitle(movieName);

        if (movie == null) {
            out.println("This movie doesn't exists");
            return;
        }

        editMenu(movie);
    }

    private void editMenu(Movie movie) {
        MenuPrinter.printEditMenu();
        if(movie.isAnimated()) out.println("\t5 ... Recommended age");
        handleEditSelection(movie);
    }

    private void handleEditSelection(Movie movie) {
        switch (input.getIntFromUserInput()) {
            case 1:
                editMovieTitle(movie);
                break;

            case 2:
                editMovieDirector(movie);
                break;

            case 3:
                editMovieYear(movie);
                break;

            case 4:
                editMovieStaff(movie);
                break;

            case 5:
                if (movie.isAnimated()) {
                    editMovieAge(movie);
                    break;
                }

            default:
                out.println("Unknown option. Please try again...");
                editMenu(movie);
                break;
        }
    }

    private void editMovieTitle(Movie movie) {
        out.println("Enter new title:");
        movie.setTitle(input.getStringFromUserInput());
        out.println("Title was successfully changed");
    }

    private void editMovieDirector(Movie movie) {
        out.println("Enter new director:");
        movie.setDirector(input.getStringFromUserInput());
        out.println("Director was successfully changed");
    }

    private void editMovieYear(Movie movie) {
        out.println("Enter new year:");
        movie.setYear(input.getIntFromUserInput());
        out.println("Year was successfully changed");
    }

    private void editMovieAge(Movie movie) {
        out.println("Enter new recommended age:");
        ((AnimatedMovie)movie).setAge(input.getIntFromUserInput());
        out.println("Recommended age was successfully changed");
    }

    private void editMovieStaff(Movie movie) {
        out.println("Current movie staff:");
        data.printMovieStaff(movie);
        handleStaffSelection(movie.getStaff());
    }

    private void handleStaffSelection(List<Person> staff) {
        MenuPrinter.printStaffMenu();
        switch (input.getIntFromUserInput()) {
            case 1:
                addStaffMember(staff);
                break;

            case 2:
                removeStaffMember(staff);
                break;

            default:
                out.println("Unknown option. Please try again...");
                handleStaffSelection(staff);
                break;
        }
    }

    private void addStaffMember(List<Person> staff) {
        Person person = getPersonFromUser();

        if (data.addStaffMember(staff, person)){
            out.println("Staff member was successfully added");
        } else {
            out.println("This person is already a staff person");
        }
    }

    private void removeStaffMember(List<Person> staff) {
        Person person = getPersonFromUser();

        if (staff.remove(data.findPersonInStaffList(staff, person))) {
            out.println("Staff member was successfully deleted");
        } else {
            out.println("This person doesn't exist. Please try again...");
            removeStaffMember(staff);
        }
    }

    private void searchMovie() {
        out.println("Enter name of a movie to search:");
        String title = input.getStringFromUserInput();
        Movie movie = data.getMovieByTitle(title);

        if (movie == null) {
            out.println("This movie doesn't exist");
        } else {
            out.println(movie);
        }
    }

    private void deleteMovie() {
        out.println("Enter name of a movie to delete:");
        String title = input.getStringFromUserInput();

        if (data.removeMovie(title)) {
            out.println("Movie was successfully deleted");
        } else {
            out.println("This movie doesn't exist. Please try again...");
        }
    }

    private void exit() {
        out.println("Exiting program");
        end = true;
        data.saveDatabase();
    }

    private Person getPersonFromUser() {
        out.println("Enter name (without surname) of a staff member to add:");
        String name = input.getStringFromUserInput();
        out.println("Enter surname of a staff member to add:");
        String surname = input.getStringFromUserInput();

        return new Person(name, surname);
    }

    private void addMovie() {
        String title, director;
        int year, age;
        Movie movie;

        out.println("Enter movie title:");
        title = input.getStringFromUserInput();

        out.println("Enter movie director:");
        director = input.getStringFromUserInput();

        out.println("Enter movie release year:");
        year = input.getIntFromUserInput();

        out.println("Is this movie animated? ([Y]es/[N]o):");
        if (input.getStringFromUserInput().equals("Y")) {
            out.println("Enter recommended age:");
            age = input.getIntFromUserInput();
            movie = new AnimatedMovie(title, director, year, age);
        } else {
            movie = new FeatureMovie(title, director, year);
        }

        if (data.addMovie(movie)) {
            out.println("Added movie: ");
            out.println(movie);
        } else {
            out.println("Movie with this title already exists. Please try again...");
        }
    }
}
