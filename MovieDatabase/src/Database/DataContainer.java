package Database;

import Movie.Movie;
import java.util.HashMap;

public class DataContainer {
    private HashMap<String, Movie> movies;

    public DataContainer() {
        movies = new HashMap<String, Movie>();
    }

    public void addMovie(Movie movie) {
        movies.put(movie.getName(), movie);
    }

    public void editMovie(String movieName, Movie newMovie) {
        movies.remove(movieName);
        addMovie(newMovie);
    }

    public Movie getMovieByName(String movieName) {
        return movies.getOrDefault(movieName, null);
    }
}
