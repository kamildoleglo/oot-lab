package pl.edu.agh.school.persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import pl.edu.agh.school.SchoolClass;
import pl.edu.agh.school.Teacher;

public final class SerializablePersistenceManager implements IPersistenceManager {

	private static Logger log = Logger.getLogger(SerializablePersistenceManager.class.getName());

	private String teachersStorageFileName;

	private String classStorageFileName;

	private pl.edu.agh.logger.Logger logger;

	public SerializablePersistenceManager() {
		this.teachersStorageFileName = "teachers.dat";
		this.classStorageFileName = "classes.dat";
	}

	@Override
	public void saveTeachers(ArrayList<Teacher> teachers) {
		if (teachers == null) {
			throw new IllegalArgumentException();
		}
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(teachersStorageFileName))) {
			oos.writeObject(teachers);
			logger.log("Saved teachers");
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException(e);
		} catch (IOException e) {
			log.log(Level.SEVERE, "There was an error while saving the teachers data", e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<Teacher> loadTeachers() {
		ArrayList<Teacher> res = null;
		try (ObjectInputStream ios = new ObjectInputStream(new FileInputStream(teachersStorageFileName))) {

			res = (ArrayList<Teacher>) ios.readObject();
			logger.log("Read teachers");
		} catch (FileNotFoundException e) {
			res = new ArrayList<Teacher>();
		} catch (IOException e) {
			log.log(Level.SEVERE, "There was an error while loading the teachers data", e);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
		return res;
	}

	@Override
	public void saveClasses(ArrayList<SchoolClass> classes) {
		if (classes == null) {
			throw new IllegalArgumentException();
		}
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(classStorageFileName))) {
			oos.writeObject(classes);
			logger.log("Saved classes");
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException(e);
		} catch (IOException e) {
			log.log(Level.SEVERE, "There was an error while saving the classes data", e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<SchoolClass> loadClasses() {
		ArrayList<SchoolClass> res = null;
		try (ObjectInputStream ios = new ObjectInputStream(new FileInputStream(classStorageFileName))) {
			res = (ArrayList<SchoolClass>) ios.readObject();
			logger.log("Read classes");
		} catch (FileNotFoundException e) {
			res = new ArrayList<SchoolClass>();
		} catch (IOException e) {
			log.log(Level.SEVERE, "There was an error while loading the classes data", e);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
		return res;
	}
	@Inject
	public void setTeachersStorageFileName(@Named("teachersStorageFileName") String teachersStorageFileName) {
		this.teachersStorageFileName = teachersStorageFileName;
	}
	@Inject
	public void setClassStorageFileName(@Named("classStorageFileName") String classStorageFileName) {
		this.classStorageFileName = classStorageFileName;
	}

	@Inject
	public void setLogger(pl.edu.agh.logger.Logger logger) {
		this.logger = logger;
	}

}
