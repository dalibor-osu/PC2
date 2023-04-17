package Database;

import Movie.*;
import Movie.Rating.AnimatedMovieRating;
import Movie.Rating.FeatureMovieRating;
import Movie.Rating.UserRating;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseController {
    private Connection connection;

    public DatabaseController() {
        createConnection();
        createTables();
    }

    private void createConnection() {
        if (connection != null) return;

        synchronized (DatabaseController.class) {
            if (connection != null) return;

            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:db/movie.db");
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void createTables() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(TableQueries.CreateMovieTableQuery);
            statement.executeUpdate(TableQueries.CreateRatingTableQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Movie> getMovieList() {
        try {
            List<Movie> movies = new ArrayList<>();
            Movie movie;
            String query = " SELECT * FROM Movie";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                if (result.getInt("Age") < 0) {
                    movie = new FeatureMovie(
                            result.getString("Title"),
                            result.getString("Director"),
                            result.getInt("Year")
                    );
                } else {
                    movie = new AnimatedMovie(
                            result.getString("Title"),
                            result.getString("Director"),
                            result.getInt("Year"),
                            result.getInt("Age")
                    );
                }

                movie.setStaff(getStaffListFromString(result.getString("Staff")));

                try {
                    movie.setId(result.getString("ID"));
                } catch (MovieException e) {
                    e.printStackTrace();
                }

                movies.add(movie);
            }

            return movies;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return new ArrayList<>();
        }
    }

    public List<UserRating> getRatingList() {
        try {
            List<UserRating> ratings = new ArrayList<>();
            UserRating rating;
            String query = "SELECT * FROM Rating";
            String movieQuery = "SELECT Age FROM Movie WHERE ID = ?";
            Statement statement = connection.createStatement();
            PreparedStatement movieStatement = connection.prepareStatement(movieQuery);
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                String movieId = result.getString("ID_Movie");
                movieStatement.setString(1, movieId);
                boolean isAnimated = movieStatement.executeQuery().getInt("Age") < 0;

                if (isAnimated) {
                    rating = new AnimatedMovieRating(
                            result.getString("ID_Movie"),
                            result.getInt("Points")
                    );
                } else {
                    rating = new FeatureMovieRating(
                            result.getString("ID_Movie"),
                            result.getInt("Points")
                    );
                }

                rating.setId(result.getString("ID"));

                String text = result.getString("Comment");
                if (!text.isEmpty()) rating.setTextReview(text);

                ratings.add(rating);
            }

            return ratings;
        } catch (Exception e) {
            System.out.println(e.toString());
            return new ArrayList<>();
        }
    }

    public void saveDatabase(List<Movie> movies, List<UserRating> ratings) {
        resetTables();
        saveMovies(movies);
        saveRatings(ratings);
    }

    private void saveRatings(List<UserRating> ratings) {
        for (UserRating rating : ratings) {
            try {
                String query = rating.hasTextReview() ? "INSERT INTO Rating(ID, ID_Movie, Points, Comment) VALUES(?, ?, ?, ?)"
                                                      : "INSERT INTO Rating(ID, ID_Movie, Points) VALUES(?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(query);

                statement.setString(1, rating.getId());
                statement.setString(2, rating.getMovieId());
                statement.setInt(3, rating.getRating());
                if (rating.hasTextReview()) statement.setString(4, rating.getTextReview());

                statement.executeUpdate();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    private void saveMovies(List<Movie> movies) {
        for (Movie movie : movies) {
            try {
                boolean isAnimated = movie instanceof AnimatedMovie;
                String query = !isAnimated ? "INSERT INTO Movie(ID, Title, Director, Year, Staff) VALUES(?, ?, ?, ?, ?)"
                                           : "INSERT INTO Movie(ID, Title, Director, Year, Staff, Age) VALUES(?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(query);

                statement.setString(1, movie.getId());
                statement.setString(2, movie.getTitle());
                statement.setString(3, movie.getDirector());
                statement.setInt(4, movie.getYear());
                statement.setString(5, getStringFromStaffList(movie.getStaff()));
                if (isAnimated) statement.setInt(6, ((AnimatedMovie)movie).getAge());

                statement.executeUpdate();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    private String getStringFromStaffList(List<Person> staff) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < staff.size(); i++) {
            stringBuilder.append(staff.get(i).toString());
            if (i + 1 < staff.size()) {
                stringBuilder.append(",");
            }
        }

        return stringBuilder.toString();
    }

    private List<Person> getStaffListFromString(String str) {
        List<Person> staff = new ArrayList<>();

        if(str.isEmpty()) return staff;

        String[] inputs = str.split(",");

        for (String input : inputs) {
            String[] names = input.split(" ");
            staff.add(new Person(names[0], names[1]));
        }

        return staff;
    }

    private void resetTables() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM Movie");
            statement.executeUpdate("DELETE FROM Rating");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
