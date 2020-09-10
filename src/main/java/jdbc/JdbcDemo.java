package jdbc;

import java.sql.*;

public class JdbcDemo {
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "appUser";
    private static final String password = "password";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static void main(String[] args) {
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();

            new DatabaseSeed(stmt);
            showResult(findAll());
            showResult(findOne(2));
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }

    private static ResultSet findAll() throws SQLException {
        String query = "select id, first_name, last_name, points from participants";
        System.out.println("All table:");
        return stmt.executeQuery(query);
    }

    private static ResultSet findOne(long id) throws SQLException {
        String query = "select * from participants where id=" + id;
        System.out.println("One entry:");
        return stmt.executeQuery(query);
    }

    private static void showResult(ResultSet rs) throws SQLException {
        try {
            while (rs.next()) {
                int id = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                long points = rs.getLong(4);

                System.out.println("id: " + id + ", FirstName: " + firstName + ", LastName: "
                        + lastName + ", Points: " + points);
            }
        } finally {
            rs.close();
        }
    }
}
