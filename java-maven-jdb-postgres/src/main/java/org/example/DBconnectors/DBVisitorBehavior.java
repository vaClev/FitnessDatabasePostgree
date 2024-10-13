package org.example.DBconnectors;
import org.example.fitnesse.VisitorInterface;

public interface DBVisitorBehavior extends DBBehavior<VisitorInterface>{

    default String getDeleteSQLQuery(String id, String tableName)
    {
        return switch (tableName) {
            case "Employees" -> "delete from Employees where id='" + id + "';";
            case "Members" -> "delete from Members where id='" + id + "';";
            default -> "error TableName";
        };
    }

}
