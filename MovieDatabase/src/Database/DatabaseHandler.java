package Database;
import Database.Queries.DeleteQueries;
import Database.Queries.InsertQueries;
import Database.Queries.SelectQueries;
import Database.Queries.UpdateQueries;
import java.sql.*;
import java.util.Random;

public class DatabaseHandler {
    public DeleteQueries Delete;
    public InsertQueries Insert;
    public SelectQueries Select;
    public UpdateQueries Update;
    private Connection connection;

    private final String createMovieTableQuery =    "CREATE TABLE IF NOT EXISTS Movie(" +
                                                        "ID CHAR(36) PRIMARY KEY NOT NULL," +
                                                        "Title VARCHAR(255) NOT NULL," +
                                                        "Year INT NOT NULL" +
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
                                                        "Comment VARCHAR(1000)," +
                                                        "CONSTRAINT ID_Movie_Rating_Constr FOREIGN KEY(ID_Movie) REFERENCES Movie(ID)" +
                                                    ");";

    public DatabaseHandler() {
        createConnection();
        createTables();
        createQueries();
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
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(createMovieTableQuery);
            statement.executeUpdate(createPersonTableQuery);
            statement.executeUpdate(createStaffTableQuery);
            statement.executeUpdate(createRatingTableQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generateID() {
        String characters = "abcdefghijklmnopqrstuvwxyz123456789";
        Random random = new Random();
        StringBuilder ID = new StringBuilder();

        for (int i = 0; i < 36; i++) {
            ID.append(characters.toCharArray()[random.nextInt(35)]);
        }
        System.out.println(ID.length());
        System.out.println(ID);
        return ID.toString();
    }

    private void dropTables() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("drop table Movie");
            statement.executeUpdate("drop table Person");
            statement.executeUpdate("drop table Rating");
            statement.executeUpdate("drop table Staff");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createQueries() {
        Delete = new DeleteQueries(connection);
        Insert = new InsertQueries(connection);
        Select = new SelectQueries(connection);
        Update = new UpdateQueries(connection);
    }
}
