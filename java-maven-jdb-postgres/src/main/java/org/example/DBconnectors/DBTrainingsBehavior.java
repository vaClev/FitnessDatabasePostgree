package org.example.DBconnectors;

import org.example.fitnesse.PublicProgram;
import org.example.fitnesse.Trainer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBTrainingsBehavior implements DBBehavior<PublicProgram> {
    private final DBHelper db = new DBHelper();
    private ResultSet resultSet;
    private PublicProgram foundedProgram;
    private ArrayList<PublicProgram> publicPrograms;

    @Override
    public ArrayList<PublicProgram> getAll() {
        publicPrograms = new ArrayList<>();
        resultSet = db.getResultSet("""
                SELECT PublicProgramms.id, PublicProgramms.ProgrammName, PublicProgramms.trainerid,
                Employees.name, Employees.secondname,
                PublicProgramms.duration, PublicProgramms.maxMembers
                FROM PublicProgramms
                JOIN Employees ON PublicProgramms.trainerid = Employees.Id;"""
        );
        try {
            collectPublicPrograms();
        } catch (SQLException ignored) {
        }
        db.close();
        return publicPrograms;
    }
    private void collectPublicPrograms() throws SQLException {
        boolean isEmpty = true;
        while (resultSet.next()) {
            isEmpty = false;
            foundedProgram = buildProgramFromResultSet();
            publicPrograms.add(foundedProgram);
        }
        if (isEmpty) System.out.println("Таблица PublicProgramms пустая");
    }
    private PublicProgram buildProgramFromResultSet() throws SQLException {
        String trainerId = resultSet.getString("trainerId");
        Trainer trainer = (Trainer) new DBEmployeeBehavior().getById(trainerId);
        return new PublicProgram(
                resultSet.getString("ProgrammName"),
                trainer,
                resultSet.getInt("duration"),
                resultSet.getInt("maxMembers")
        );
    }

    @Override
    public PublicProgram getById(String id) {
        foundedProgram = null;
        String query = """
                SELECT PublicProgramms.id, PublicProgramms.ProgrammName, PublicProgramms.trainerid,
                Employees.name, Employees.secondname,
                PublicProgramms.duration, PublicProgramms.maxMembers
                FROM PublicProgramms
                JOIN Employees ON PublicProgramms.trainerid = Employees.Id
                WHERE PublicProgramms.id =""" + id + ";";
        resultSet = db.getResultSet(query);
        try {
            findProgram();
        } catch (SQLException ignored) {
        }
        db.close();
        return foundedProgram;
    }
    private void findProgram() throws SQLException {
        if (resultSet.next()) {
            foundedProgram = buildProgramFromResultSet();
        } else {
            System.out.println("Training(PublicProgram) not found in DB ");
        }
    }

    @Override
    public boolean add(PublicProgram object) {
        return false;
    }

    @Override
    public boolean update(PublicProgram object) {
        return false;
    }

    @Override
    public boolean delete(PublicProgram object) {
        return false;
    }
}
