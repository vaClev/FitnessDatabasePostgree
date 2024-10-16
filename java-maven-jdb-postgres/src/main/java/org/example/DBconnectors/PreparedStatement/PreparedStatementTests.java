package org.example.DBconnectors.PreparedStatement;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatementTests {
    private static final DBPrepareStatement db = new DBPrepareStatement();
    public static void main(String[] args) {
        db.connect();
        String sqlTemplate = """
                INSERT INTO members (id, name, secondname, age, gender, wallet, visitedlist, ticketdate)
                VALUES(?, ?, ?, ?, ?, ?, ?, ?);""";
        PreparedStatement statement = db.getPreparedStatement(sqlTemplate);
        short age = 30;
        try {
            statement.setString(1,"rtyui");
            statement.setString(2,"Ivan");
            statement.setString(3,"Popov");
            statement.setShort(4, age);
            statement.setShort(5, (short) 1);
            statement.setInt(6,1000);
            statement.setArray(7,null);
            statement.setDate(8,null);
            statement.executeUpdate(); //
            /*
            *Для выполнения запроса PreparedStatement имеет три метода:
            boolean execute(): выполняет любую SQL-команду
            ResultSet executeQuery(): выполняет команду SELECT, которая возвращает данные в виде ResultSet
            int executeUpdate(): выполняет такие SQL-команды, как INSERT, UPDATE, DELETE, CREATE и возвращает количество измененных строк
            * */
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        db.close();
    }
}
