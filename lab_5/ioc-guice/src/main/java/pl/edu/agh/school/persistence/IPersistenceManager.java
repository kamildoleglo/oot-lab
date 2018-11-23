package pl.edu.agh.school.persistence;

import pl.edu.agh.school.SchoolClass;
import pl.edu.agh.school.Teacher;

import java.util.ArrayList;

public interface IPersistenceManager {
    void saveTeachers(ArrayList<Teacher> teachers);

    @SuppressWarnings("unchecked")
    ArrayList<Teacher> loadTeachers();

    void saveClasses(ArrayList<SchoolClass> classes);

    @SuppressWarnings("unchecked")
    ArrayList<SchoolClass> loadClasses();
}
