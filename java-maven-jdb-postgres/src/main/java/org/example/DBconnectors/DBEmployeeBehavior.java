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
    //TODO подумать как использовать один метод
    public ArrayList<VisitorInterface> getAll(String position) {
        employees = new ArrayList<>();
        String query="""
                SELECT Employees.id, Employees.name, Employees.secondname, Employees.age, Employees.status,
                Positions.positionName
                FROM Employees
                JOIN Positions ON Employees.status = Positions.Id
                WHERE Positions.positionName='"""+position+"'";
        resultSet = db.getResultSet(query);
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
                        initParams.get("name") + " " +
                        initParams.get("secondName"));
                yield new Trainer("error", "error", 0);
            }
        };
    }

    @Override
    public VisitorInterface getById(String id) {
        foundedEmployee = null;
        resultSet = db.getResultSet("SELECT Employees.id, Employees.name," +
                "\nEmployees.secondname, Employees.age, Employees.status," +
                "\nPositions.positionName" +
                "\nFROM Employees" +
                "\nJOIN Positions ON Employees.status = Positions.Id" +
                "\nWHERE Employees.id ='" + id + "'");
        try {
            findEmployee();
        } catch (SQLException ignored) {
        }
        db.close();
        return foundedEmployee;
    }
    private void findEmployee() throws SQLException {
        if (resultSet.next()) {
            foundedEmployee = buildEmployeeFromResultSet();
            ;
        } else {
            System.out.println("Employee not found in DB");
        }
    }
    @Override
    public boolean add(VisitorInterface visitor) {
        //TODO придумать
        if (visitor instanceof Director)
        {
            add((Director)visitor,2);
        } else if (visitor instanceof Admin) {
            add((Admin)visitor,1);
        }
        else if (visitor instanceof Trainer) {
            add((Trainer)visitor,0);
        }
        return false;
    }
    public boolean add(Visitor visitor, int status) {
        //TODO проверка наличия в базе
        try {
            insertDB(visitor, status);
        } catch (SQLException e) {
            System.out.println("такой есть в базе!!!!!");
            throw new RuntimeException(e);
            //return false;
        }
        return true;
    }
    public void insertDB(Visitor v1, int status) throws SQLException {
        String table = "Employees";
        String query = String.format(
                "insert into %s values('%s', '%s', '%s', '%d', '%d')",
                table,
                v1.id,
                v1.name,
                v1.secondName,
                v1.age,
                status);
        db.executeUpdate(query);
    }

   /* public void newEmployee(int status) throws SQLException {
        Visitor temp = createEmployee(status);
        insertDB(temp, status);
    }
    private Visitor createEmployee(int status) {
        if (status == 0) {
            return new Trainer();
        } else if (status == 1) {
            return new Admin();
        } else if (status == 2) {
            return new Director();
        } else {
            return null;
        }
    }*/

    @Override
    public boolean update(VisitorInterface visitor) {
        return false;
    }

    @Override
    public boolean delete(VisitorInterface visitor) {
        return false;
    }
    public void delete(String id)
    {
        try {
            deleteFromDB(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void deleteFromDB(String id) throws SQLException {
        String query = getDeleteSQLQuery(id,"Employees");
        db.executeUpdate(query);
    }
}
