package org.example.DBconnectors;

import org.example.fitnesse.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBEmployeeBehavior implements DBVisitorBehavior {
    private final DBHelper db = new DBHelper();
    private ResultSet resultSet;
    private VisitorInterface foundedEmployee;
    private ArrayList<VisitorInterface> employees;

    @Override
    public ArrayList<VisitorInterface> getAll() {
        employees = new ArrayList<>();
        resultSet = db.getResultSet("""
                SELECT Employees.id, Employees.name, Employees.secondname, Employees.age, Employees.status,
                Positions.positionName
                FROM Employees
                JOIN Positions ON Employees.status = Positions.Id;"""
        );
        try {
            collectEmployees();
        } catch (SQLException ignored) {
        }
        db.close();
        return employees;
    }
    private void collectEmployees() throws SQLException {
        boolean isEmpty = true;
        while (resultSet.next()) {
            isEmpty = false;
            foundedEmployee = buildEmployeeFromResultSet();
            employees.add(foundedEmployee);
        }
        if (isEmpty) System.out.println("Таблица Employees пустая");
    }
    private VisitorInterface buildEmployeeFromResultSet() throws SQLException {
        String positionName = resultSet.getString("positionName");
        Map<String, String> initParams = getInitParams();
        return getEmployeeByPosition(positionName, initParams);
    }
    private Map<String, String> getInitParams() throws SQLException {
        Map<String, String> initParams = new HashMap<>();
        initParams.put("name", resultSet.getString("name"));
        initParams.put("secondName", resultSet.getString("secondName"));
        initParams.put("age", resultSet.getString("age"));
        initParams.put("id", resultSet.getString("id"));
        return initParams;
    }
    private VisitorInterface getEmployeeByPosition(String positionName, Map<String, String> initParams) {
        return switch (positionName) {
            case "Trainer" -> new Trainer(initParams);
            case "Administrator" -> new Admin(initParams);
            case "Director" -> new Director(initParams);
            default -> {
                System.out.println("Некорректные данные в базе, не указана должность сотрудника" +
                        initParams.get("name")+" "+
                        initParams.get("secondName"));
                yield new Trainer("error", "error", 0);
            }
        };
    }

    @Override
    public VisitorInterface getByID(String ID) {
        foundedEmployee = null;
        resultSet = db.getResultSet("SELECT Employees.id, Employees.name," +
                "\nEmployees.secondname, Employees.age, Employees.status," +
                "\nPositions.positionName"+
                "\nFROM Employees"+
                "\nJOIN Positions ON Employees.status = Positions.Id" +
                "\nWHERE Employees.id ='" + ID + "'");
        try {
            findEmployee();
        } catch (SQLException ignored) {
        }
        db.close();
        return foundedEmployee;
    }
    private void findEmployee() throws SQLException {
        if (resultSet.next()) {
            foundedEmployee = buildEmployeeFromResultSet();;
        } else {
            System.out.println("Employee not found in DB");
        }
    }

    @Override
    public boolean add(Visitor visitor) {
        return false;
    }

    @Override
    public boolean update(Visitor visitor) {
        return false;
    }

    @Override
    public boolean delete(Visitor visitor) {
        return false;
    }
}
