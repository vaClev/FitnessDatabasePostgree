import java.sql.*;

public class PostgreSQLExample {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/FitnessCenter2209";
        String username = "postgres";
        String password = "1234";
        // Register the PostgreSQL driver
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        // Connect to the database
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            statement = connection.createStatement();
            makeAction(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Close the connection
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void makeAction(Statement statement) throws SQLException {
        // Perform desired database operations
        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees WHERE name ='Arnold'");
        int columns = resultSet.getMetaData().getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i < columns; i++) {
                System.out.print(resultSet.getString(i) + " ");
            }
            System.out.println();
        }
    }
}

