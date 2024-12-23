package DAO;

import java.util.List;

import Model.Employe;

public interface GenericDAOI<T> {
	public void add(T emp);
	public void update(T emp);
	public void delete(int id);
	List<T> afficher();
	T findById(int id);

}
