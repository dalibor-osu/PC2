package Database.Queries;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class InsertQueries extends Query {

    public InsertQueries(Connection connection) {
        super(connection);
    }
}
