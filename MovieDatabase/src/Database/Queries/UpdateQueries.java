package Database.Queries;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UpdateQueries extends Query {
    public UpdateQueries(Connection connection) {
        super(connection);
    }
}
