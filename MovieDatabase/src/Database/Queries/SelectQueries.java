package Database.Queries;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SelectQueries extends Query {
    public SelectQueries(Connection connection) {
        super(connection);
    }
}
