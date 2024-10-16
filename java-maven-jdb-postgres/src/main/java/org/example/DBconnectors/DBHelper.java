package org.example.DBconnectors;
import java.sql.*;

class DBHelper {
    //TODO удобно но опасно работать через ResultSet переписать классы через более безопасный PrepareStatement
    private static final String jdbcUrl = "jdbc:postgresql://localhost:5432/FitnessHome";
    private static final String username = "postgres";
    private static final String password = "1234";
    private Connection connection = null;
    private Statement statement = null;
    private void connect(){
        // Register the PostgreSQL driver
        try {
            Class.forName("org.postgresql.Driver");
        }  catch (ClassNotFoundException e) {
            System.out.println("ошибка регистрации драйвера PostgreSQL");
        }
        // Connect to the database
        try {
            this.connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            System.out.println("ошибка подключения к базе данных" + jdbcUrl + " " + username + " " + password);
        }
    }
    private Statement getStatement() {
        try {
            connect();
            this.statement = connection.createStatement();
            return statement;
        } catch (SQLException e) {
            System.out.println("ошибка получения Statement");
        }
        throw new RuntimeException();
    }
    ResultSet getResultSet(String SQLQuery){
        ResultSet resultSet = null;
        try {
            resultSet = getStatement().executeQuery(SQLQuery);
        }catch (SQLException e)
        {
            System.out.println("ошибка в тексте запроса "+SQLQuery);
        }
        return resultSet;
    }
    void executeUpdate(String SQLQuery) throws SQLException {
        getStatement().executeUpdate(SQLQuery);
        close();
    }
    void printResultSet(ResultSet resultSet) {
        try {
            int columns = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i < columns; i++) {
                    System.out.print(resultSet.getString(i) + " ");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("ошибка вывода ResultSet");
        }
    }
    void close() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
