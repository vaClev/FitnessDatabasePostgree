package org.example.DBconnectors;
import org.example.fitnesse.Visitor;
import org.example.fitnesse.VisitorInterface;

import java.util.ArrayList;

public interface DBVisitorBehavior {
    ArrayList<VisitorInterface> getAll();
    VisitorInterface getByID (String ID);
    boolean add (Visitor visitor);
    boolean update (Visitor visitor);
    boolean delete (Visitor visitor);
}
