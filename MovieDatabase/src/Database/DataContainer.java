package Database;

import Movie.*;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class DataContainer {
    private List<Movie> movies;
    private List<UserRating> userRatings;
    private List<Person> people;
    private List<MovieStaff> staff;
    private DatabaseHandler databaseHandler;
    public DataContainer() {
        databaseHandler = new DatabaseHandler();
        movies = databaseHandler.getMovieList();
        userRatings = databaseHandler.getRatingList();
        people = databaseHandler.getPersonList();
        staff = databaseHandler.getStaffList();
    }

    public void addMovie(Movie movie) {
        movie.setId(generateID());
        movies.add(movie);
    }

    public void editMovie(String movieTitle, Movie newMovie) {
        movies.remove(movies.stream().filter(movie -> movieTitle.equals(movie.getName())).findAny().orElse(null));
        addMovie(newMovie);
    }

    public Movie getMovieByTitle(String movieTitle) {
        return movies.stream().filter(movie -> movie.getName().equals(movieTitle)).findAny().orElse(null);
    }

    public Movie getMovieById(String movieId) {
        return movies.stream().filter(movie -> movie.getId().equals(movieId)).findAny().orElse(null);
    }

    public void saveDatabase() {
        databaseHandler.saveDatabase(movies, people, staff, userRatings);
    }

    public boolean removeMovie(String movieName) {
        return movies.remove(movies.stream().filter(movie -> movie.getName().equals(movieName)).findAny().orElse(null));
    }

    public void printAllMovies() {
        for (Movie movie : movies) {
            System.out.println("------------------------------------");
            System.out.println(movie);
        }
    }

    private String generateID() {
        String characters = "abcdefghijklmnopqrstuvwxyz123456789";
        Random random = new Random();
        StringBuilder ID = new StringBuilder();

        for (int i = 0; i < 36; i++) {
            ID.append(characters.toCharArray()[random.nextInt(35)]);
        }

        return getMovieById(ID.toString()) == null ? ID.toString() : generateID();
    }
}
