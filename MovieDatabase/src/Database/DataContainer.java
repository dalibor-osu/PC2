package Database;

import Movie.*;
import Movie.Rating.UserRating;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataContainer {
    private List<Movie> movies;
    private List<UserRating> ratings;
    private DatabaseController databaseHandler;
    public DataContainer() {
        databaseHandler = new DatabaseController();
        movies = databaseHandler.getMovieList();
        ratings = databaseHandler.getRatingList();
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<UserRating> getRatings() {
        return ratings;
    }

    public boolean addMovie(Movie movie) {
        if (movieExists(movie.getTitle())) {
            return false;
        }

        try {
            movie.setId(generateMovieId());
        } catch (MovieException e) {
            System.out.println(e.getMessage());
            return false;
        }

        movies.add(movie);
        return true;
    }

    public boolean addMovie(Movie movie, String id) {
        if (movieExists(movie.getTitle())) {
            return false;
        }

        try {
            movie.setId(id);
        } catch (MovieException e) {
            System.out.println(e.getMessage());
            return false;
        }

        movies.add(movie);
        return true;
    }
    
    public boolean addRating(UserRating rating) {
        try {
            rating.setId(generateRatingId());
        } catch (MovieException e) {
            System.out.println(e.getMessage());
            return false;
        }

        ratings.add(rating);
        return true;
    }


    public Movie getMovieByTitle(String movieTitle) {
        return movies.stream().filter(movie -> movie.getTitle().equals(movieTitle)).findAny().orElse(null);
    }

    public Movie getMovieById(String movieId) {
        return movies.stream().filter(movie -> movie.getId().equals(movieId)).findAny().orElse(null);
    }

    public UserRating getRatingById(String ratingId) {
        return ratings.stream().filter(rating -> rating.getId().equals(ratingId)).findAny().orElse(null);
    }

    public void saveDatabase() {
        databaseHandler.saveDatabase(movies, ratings);
    }

    public boolean removeMovie(String movieName) {
        Movie movie = getMovieByTitle(movieName);

        if (movie == null) {
            return false;
        }

        List<UserRating> userRatings = getRatingsForMovie(movie.getId());
        for (UserRating rating : userRatings) {
            ratings.remove(rating);
        }

        movies.remove(movie);
        return true;
    }

    public List<UserRating> getRatingsForMovie(String movieId) {
        return ratings.stream().filter(rating -> rating.getMovieId().equals(movieId)).toList();
    }

    public void printMovies() {
        printMovies(movies);
    }

    public void printMovies(List<Movie> movies) {
        for (Movie movie : movies) {
            System.out.println("------------------------------------");
            System.out.println(movie);
        }
    }

    public void printMovieStaff(Movie movie) {
        for (Person person : movie.getStaff()) {
            System.out.println(person);
        }
    }

    public void printMoviesWithPerson(Person person) {
        boolean movieFound = false;
        for (Movie movie : movies) {
            if (findPersonInStaffList(movie.getStaff(), person) != null) {
                System.out.println("------------------------------------");
                System.out.println(movie);
                movieFound = true;
            }
        }

        if(!movieFound) {
            System.out.println("No movie with " + person + " found...");
        }
    }

    public void printStaffMembersWithMovies() {
        List<List<Movie>> movieList = new ArrayList<>();
        List<Person> people = new ArrayList<>();

        for (Movie movie : movies) {
            for (Person person : movie.getStaff()) {
                Person member = findPersonInStaffList(people, person);
                if (member == null) {
                    people.add(person);
                    List<Movie> personsMovies = new ArrayList<>();
                    personsMovies.add(movie);
                    movieList.add(personsMovies);
                } else {
                    int idx = people.indexOf(member);
                    movieList.get(idx).add(movie);
                }
            }
        }

        for (int i = 0; i < people.size(); i++) {
            if (movieList.get(i).size() < 2) {
                continue;
            }

            System.out.println("\t" + people.get(i).toString());

            for (Movie movie : movieList.get(i)) {
                System.out.println("\t\t" + movie.getTitle());
            }
        }
    }

    public boolean movieExists(String movieTitle) {
        return getMovieByTitle(movieTitle) != null;
    }

    public boolean addStaffMember(List<Person> staff, Person person) {
        if (findPersonInStaffList(staff, person) != null) return false;

        staff.add(person);
        return true;
    }

    public Person findPersonInStaffList(List<Person> staff, Person person) {
        return staff.stream()
                .filter(member -> (
                        member.getName().equals(person.getName()) &&
                        member.getSurname().equals(person.getSurname())
                    )
                )
                .findAny()
                .orElse(null);
    }

    private String generateMovieId() {
        char[] characters = "abcdefghijklmnopqrstuvwxyz123456789".toCharArray();
        Random random = new Random();
        StringBuilder id = new StringBuilder();

        for (int i = 0; i < 36; i++) {
            id.append(characters[random.nextInt(35)]);
        }

        return getMovieById(id.toString()) == null ? id.toString() : generateMovieId();
    }

    private String generateRatingId() {
        String id = generateMovieId();
        return getRatingById(id) == null ? id : generateRatingId();
    }
}
