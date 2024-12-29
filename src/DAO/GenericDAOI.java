package DAO;

import java.io.File;
import java.io.IOException;
import java.util.List;

import Model.Employe;

public interface GenericDAOI<T> {
	public boolean add(T emp);
	public void update(T emp);
	public void delete(int id);
	List<T> afficher();
	T findById(int id);
	int importData(File filepath) throws IOException;
	void exportData(File filepath) throws IOException;
	List<Employe> getAll();
}
