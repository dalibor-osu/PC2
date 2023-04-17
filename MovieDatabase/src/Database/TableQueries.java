package Database;

 public class TableQueries {
        public static final String CreateMovieTableQuery =  "CREATE TABLE IF NOT EXISTS Movie(" +
                                                                "ID CHAR(36) PRIMARY KEY NOT NULL," +
                                                                "Title VARCHAR(255) NOT NULL," +
                                                                "Director VARCHAR(255) NOT NULL," +
                                                                "Year INT NOT NULL," +
                                                                "Staff VARCHAR(1000)," +
                                                                "Age INT NOT NULL DEFAULT -1" +
                                                            ");";

     public static final String CreateRatingTableQuery =    "CREATE TABLE IF NOT EXISTS Rating(" +
                                                                 "ID VARCHAR(36) PRIMARY KEY NOT NULL," +
                                                                 "ID_Movie VARCHAR(36) NOT NULL," +
                                                                 "Points INT NOT NULL," +
                                                                 "Comment VARCHAR(1000) DEFAULT NULL," +
                                                                 "CONSTRAINT ID_Movie_Rating_Constr FOREIGN KEY(ID_Movie) REFERENCES Movie(ID)" +
                                                             ");";
}
