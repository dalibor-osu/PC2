package Database;

import Movie.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DatabaseHandler {
    private Connection connection;

    private final String createMovieTableQuery =    "CREATE TABLE IF NOT EXISTS Movie(" +
                                                        "ID CHAR(36) PRIMARY KEY NOT NULL," +
                                                        "Title VARCHAR(255) NOT NULL," +
                                                        "Director VARCHAR(255) NOT NULL," +
                                                        "Year INT NOT NULL," +
                                                        "Age INT NOT NULL DEFAULT -1" +
                                                    ");";

    private final String createPersonTableQuery =   "CREATE TABLE IF NOT EXISTS Person(" +
                                                        "ID CHAR(36) PRIMARY KEY NOT NULL," +
                                                        "Surname VARCHAR(50) NOT NULL," +
                                                        "Name VARCHAR(50) NOT NULL" +
                                                    ");";

    private final String createStaffTableQuery =    "CREATE TABLE IF NOT EXISTS Staff(" +
                                                        "ID CHAR(36) PRIMARY KEY NOT NULL," +
                                                        "ID_Person CHAR(36) NOT NULL," +
                                                        "ID_Movie CHAR(36) NOT NULL," +
                                                        "CONSTRAINT ID_Person_Constr FOREIGN KEY(ID_Person) REFERENCES Person(ID)," +
                                                        "CONSTRAINT ID_Movie_Constr FOREIGN KEY(ID_Movie) REFERENCES Movie(ID)" +
                                                    ");";

    private final String createRatingTableQuery =   "CREATE TABLE IF NOT EXISTS Rating(" +
                                                        "ID CHAR(36) PRIMARY KEY NOT NULL," +
                                                        "ID_Movie CHAR(36) NOT NULL," +
                                                        "Points INT NOT NULL," +
                                                        "Comment VARCHAR(1000) DEFAULT NULL," +
                                                        "CONSTRAINT ID_Movie_Rating_Constr FOREIGN KEY(ID_Movie) REFERENCES Movie(ID)" +
                                                    ");";

    public DatabaseHandler() {
        createConnection();
        createTables();
    }

    private void createConnection() {
        if (connection != null) return;

        synchronized (DatabaseHandler.class) {
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
        dropTables();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(createMovieTableQuery);
            statement.executeUpdate(createPersonTableQuery);
            statement.executeUpdate(createStaffTableQuery);
            statement.executeUpdate(createRatingTableQuery);
            insertSampleValues();
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

                movie.setId(result.getString("ID"));
                movies.add(movie);
            }

            return movies;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return new ArrayList<>();
        }
    }

    public List<Person> getPersonList() {
        try {
            List<Person> people = new ArrayList<>();
            Person person;
            String query = " SELECT * FROM Person";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                person = new Person(
                        result.getString("ID"),
                        result.getString("Name"),
                        result.getString("Surname")
                );

                people.add(person);
            }

            return people;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return new ArrayList<>();
        }
    }

    public List<MovieStaff> getStaffList() {
        try {
            List<MovieStaff> staffList = new ArrayList<>();
            MovieStaff staff;
            String query = " SELECT * FROM Staff";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                staff = new MovieStaff(
                        result.getString("ID"),
                        result.getString("ID_Movie"),
                        result.getString("ID_Person")
                );

                staffList.add(staff);
            }

            return staffList;
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
                            result.getString("ID"),
                            result.getInt("Rating")
                    );
                } else {
                    rating = new FeatureMovieRating(
                            result.getString("ID"),
                            result.getInt("Rating")
                    );
                }

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

    public void saveDatabase(List<Movie> movies, List<Person> people, List<MovieStaff> staff, List<UserRating> ratings) {

    }

    private void saveMovies(List<Movie> movies) {
        for (Movie movie : movies) {
            try {
                boolean isAnimated = movie instanceof AnimatedMovie;
                String query = "INSERT INTO Movie VALUES(?, ?, ?, ?" + (isAnimated ? ", ?)" : ")");
                PreparedStatement statement = connection.prepareStatement(query);

                statement.setString(1, movie.getId());
                statement.setString(2, movie.getName());
                statement.setString(3, movie.getDirector());
                statement.setInt(4, movie.getYear());
                if (isAnimated) statement.setInt(5, ((AnimatedMovie)movie).getAge());

                statement.executeUpdate();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    private void insertSampleValues() {
        try {
            String query = "INSERT INTO Movie(ID, Title, Director, Year) VALUES(?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, generateID());
            statement.setString(2, "Sample Movie 1");
            statement.setString(3, "Matej Sturma");
            statement.setInt(4, 2029);

            statement.executeUpdate();

            query = "INSERT INTO Movie(ID, Title, Director, Year, Age) VALUES(?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, generateID());
            statement.setString(2, "Sample Movie 2");
            statement.setString(3, "Majo Marek");
            statement.setInt(4, 5246);
            statement.setInt(5, 8);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    private String generateID() {
        String characters = "abcdefghijklmnopqrstuvwxyz123456789";
        Random random = new Random();
        StringBuilder ID = new StringBuilder();

        for (int i = 0; i < 36; i++) {
            ID.append(characters.toCharArray()[random.nextInt(35)]);
        }

        return ID.toString();
    }

    private void dropTables() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("drop table if exists Movie");
            statement.executeUpdate("drop table if exists Person");
            statement.executeUpdate("drop table if exists Rating");
            statement.executeUpdate("drop table if exists Staff");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
