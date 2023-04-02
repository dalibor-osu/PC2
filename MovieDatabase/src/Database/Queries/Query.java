package Database.Queries;

import java.sql.Connection;

public abstract class Query {
    Connection connection;

    public Query(Connection connection) {
        this.connection = connection;
    }
}
