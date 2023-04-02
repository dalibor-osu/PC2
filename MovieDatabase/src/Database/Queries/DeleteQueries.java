package Database.Queries;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DeleteQueries extends Query {

    public DeleteQueries(Connection connection) {
        super(connection);
    }
}
