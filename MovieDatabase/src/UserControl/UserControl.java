package UserControl;

import Database.DataContainer;
import Movie.*;
import Movie.Rating.AnimatedMovieRating;
import Movie.Rating.FeatureMovieRating;
import Movie.Rating.UserRating;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            case 1 -> addMovie();
            case 2 -> editMovie();
            case 3 -> deleteMovie();
            case 4 -> addUserRating();
            case 5 -> searchMovie();
            case 6 -> data.printMovies();
            case 7 -> data.printStaffMembersWithMovies();
            case 8 -> printMoviesWithPerson();
            case 9 -> saveMovieToFile();
            case 10 -> saveAllMoviesToDirectory();
            case 11 -> loadMovieFromFile();
            case 12 -> exit();
            default -> out.println("Unknown command. Please try again...");
        }

        if (!end) { Menu(); }
    }

    private void loadMovieFromFile() {
        out.println("Enter file path:");
        String path = input.getStringFromUserInput();

        if (path.length() < 1) {
            return;
        }

        if (path.toCharArray()[path.length() - 1] == '/') {
            loadMoviesFromDirectory(path);
        } else {
            loadSingeMovieFromFile(path);
        }
    }

    private void loadMoviesFromDirectory(String path) {
        List<String> files = listFilesUsingJavaIO(path);
        for (String file : files) {
            loadSingeMovieFromFile(path + file);
        }
    }

    private List<String> listFilesUsingJavaIO(String dir) {
        return Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .filter(name -> name.contains(".txt"))
                .collect(Collectors.toList());
    }

    private void loadSingeMovieFromFile(String path) {
        Movie movie;

        try {
            List<String> lines = Files.readAllLines(Path.of(path), StandardCharsets.UTF_8);
            checkIfValidFile(lines);
            String movieTitle = lines.get(0);
            String movieId = lines.get(1).trim().split(": ")[1];
            String movieDirector = lines.get(2).trim().split(": ")[1];
            int movieYear = Integer.parseInt(lines.get(3).trim().split(": ")[1]);
            int age = Integer.parseInt(lines.get(lines.size() - 1).trim().split(": ")[1]);

            movie = age < 0 ? new FeatureMovie(movieTitle, movieDirector, movieYear) : new AnimatedMovie(movieTitle, movieDirector, movieYear, age);

            if (!lines.get(4).contains("No")) {
                for (int i = 5; i < lines.size() - 1; i++) {
                    String[] name = lines.get(i).trim().split(" ");
                    movie.getStaff().add(new Person(name[0], name[1]));
                }
            }

            if (data.addMovie(movie, movieId)) {
                out.println("Successfully added movie:");
                out.println(movie);
            } else {
                out.println("This movie already exists in database.");
            }
        } catch (MovieException e) {
            out.println(e.getMessage() + ", File: " + path);
        } catch (Exception e) {
            out.println("Failed to load movie from file " + path);
        }
    }

    private void saveMovieToFile() {
        Movie movie = getMovieFromUser(" to save");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("./" + movie.getTitle() + ".txt"));
            writer.write(movie.toString());
            if (!movie.isAnimated()) {
                writer.write("\n\tAge: -1");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveAllMoviesToDirectory() {
        out.println("Enter folder path:");
        String path = input.getStringFromUserInput();

        for (Movie movie : data.getMovies()) {
            saveMovieToFile(path, movie);
        }
    }

    private void saveMovieToFile(String path, Movie movie) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path + movie.getTitle() + ".txt"));
            writer.write(movie.toString());
            if (!movie.isAnimated()) {
                writer.write("\n\tAge: -1");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addUserRating() {
        Movie movie = getMovieFromUser(" to add the rating to");
        UserRating rating = getUserRating(movie);
        data.addRating(rating);
    }

    private UserRating getUserRating(Movie movie) {
        out.println("Enter your rating (" + (movie.isAnimated() ? "1 - 10" : "1 - 5") + "):");
        int points = input.getIntFromUserInput();

        out.println("Enter your comment (or leave blank):");
        String comment = input.getStringFromUserInput();
        UserRating rating;

        try {
            if (movie.isAnimated()) {
                rating = new AnimatedMovieRating(movie.getId(), points, comment);
            } else {
                rating = new FeatureMovieRating(movie.getId(), points, comment);
            }

            return rating;
        } catch (Exception e) {
            out.println(e.getMessage());
            return getUserRating(movie);
        }
    }

    private void printMoviesWithPerson() {
        Person person = getPersonFromUser();
        data.printMoviesWithPerson(person);
    }

    private void editMovie() {
        Movie movie = getMovieFromUser(" to edit");

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
        List<UserRating> ratings;

        if (movie == null) {
            out.println("This movie doesn't exist");
        } else {
            ratings = data.getRatingsForMovie(movie.getId());
            out.println(movie);

            if (ratings.size() > 0) {
                out.println("\n\tRatings:");
                for (UserRating rating : ratings) {
                    out.println(rating);
                }
            }
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

    private Movie getMovieFromUser(String action) {
        out.println("Enter name of a movie" + action + ":");
        String movieName = input.getStringFromUserInput();
        return data.getMovieByTitle(movieName);
    }

    private void checkIfValidFile(List<String> lines) throws MovieException {
        String line = lines.get(1);
        boolean valid = true;
        String exceptionMessage = "";

        if (!line.contains("ID") || line.split(": ").length != 2 || line.split(": ")[1].length() != 36) {
            valid = false;
            exceptionMessage += "ID";
        }

        line = lines.get(2);
        if (!line.contains("Director") || line.split(": ").length != 2) {
            valid = false;
            exceptionMessage += (exceptionMessage.isEmpty() ? "" : ", ") + "Director";
        }

        line = lines.get(3);
        if (!line.contains("Year") || line.split(": ").length != 2) {
            valid = false;
            exceptionMessage += (exceptionMessage.isEmpty() ? "" : ", ") + "Year";
        } else {
            try {
                Integer.parseInt(line.trim().split(": ")[1]);
            } catch (Exception e) {
                valid = false;
                exceptionMessage += (exceptionMessage.isEmpty() ? "" : ", ") + "Year";
            }
        }

        line = lines.get(4);
        if (!line.contains("Actors") && !line.contains("Animators")) {
            valid = false;
            exceptionMessage += (exceptionMessage.isEmpty() ? "" : ", ") + "Staff";
        }

        line = lines.get(lines.size() - 1);
        if (!line.contains("Age") && line.split(": ").length != 2) {
            valid = false;
            exceptionMessage += (exceptionMessage.isEmpty() ? "" : ", ") + "Age";
        } else {
            try {
                Integer.parseInt(line.trim().split(": ")[1]);
            } catch (Exception e) {
                valid = false;
                exceptionMessage += (exceptionMessage.isEmpty() ? "" : ", ") + "Age";
            }
        }

        if (!valid) {
            throw new MovieException("File corrupted (" + exceptionMessage + ")");
        }
    }
}
