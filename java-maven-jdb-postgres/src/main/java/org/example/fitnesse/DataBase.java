package org.example.fitnesse;
import java.sql.*;

public class DataBase {
    protected Connection connection;
    protected  Statement statement;
    protected ResultSet result;
    public Statement getConnect() throws SQLException {
        String jdbcUrl = "jdbc:postgresql://localhost:5433/FitnessCentre";
        String username = "postgres";
        String password = "1234";
        connection = DriverManager.getConnection(
                jdbcUrl,
                username,
                password);

        return connection.createStatement();
    }
    public void print(ResultSet result) throws SQLException {
        int columns = result.getMetaData().getColumnCount();
        while(result.next()){
            for (int i = 1;i<=columns;i++){
                System.out.print(result.getString(i)+ "\t");
            }
            System.out.println();
        }
    }
    public void closeConnection(Statement statement) throws SQLException {
        connection.close();
        statement.close();
    }
}
