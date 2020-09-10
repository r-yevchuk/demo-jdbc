package jdbc;

import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSeed {

    DatabaseSeed(Statement stmt) throws SQLException {

        String sql = "CREATE TABLE IF NOT EXISTS participants " +
                "(id INTEGER not NULL, " +
                " first_name VARCHAR(255), " +
                " last_name VARCHAR(255), " +
                " points INTEGER, " +
                " PRIMARY KEY ( id ))";

        stmt.executeUpdate(sql);

        String query1 = "INSERT INTO participants (id, first_name, last_name, points)" +
                " VALUES (1, 'Ricky', 'Edmonds', 9) ON CONFLICT DO NOTHING";
        stmt.executeUpdate(query1);

        String query2 = "INSERT INTO participants (id, first_name, last_name, points)" +
                " VALUES (2, 'Anees', 'Carter', 10) ON CONFLICT DO NOTHING";
        stmt.executeUpdate(query2);
    }
}
