package Model;

import java.util.ArrayList;
import java.util.List;

import DAO.EmployeDAOImpl;

public class EmployeModel {
	private EmployeDAOImpl dao;
	
	public EmployeModel(EmployeDAOImpl dao) {
		this.dao=dao;
	}
	public boolean add(String nom, String prenom, String email, String telephone, double salaire, Role role, Poste poste) {
		if(salaire<4000) {
			System.err.println("Salaire incorect.");
			return false;
		}
		 if (telephone.length() != 10) {
	            System.err.println("Le numéro de telephone doit contenir exactement 10 chiffres.");
	            return false; 
	        }
		 Employe emp = new Employe(0,nom, prenom, email, telephone, salaire, role, poste);
	        dao.add(emp);
	        return true;
	}
	public boolean update(int id,String nom, String prenom, String email, String telephone, double salaire, Role role, Poste poste) {
        if (salaire < 4000) {
            System.err.println("Salaire incorect.");
            return false;
        }
        if (telephone.length() != 10) {
            System.err.println("Le numéro de telephone doit contenir exactement 10 chiffres.");
            return false;
        }
        Employe emp = new Employe(id,nom, prenom, email, telephone, salaire, role, poste);
        dao.update(emp);
        return true;
    }
	public boolean delete(int id) {
		dao.delete(id);
		return true;
	}
	public ArrayList<Object[]> afficher(){
		 ArrayList<Employe>emp=(ArrayList<Employe>) dao.afficher();
		  ArrayList<Object[]> tabEmp = new ArrayList<>();
		  for (Employe empl : emp) {
			  Object[] tab = new Object[8];
            tab[0] = empl.getId();
            tab[1] = empl.getNom();
            tab[2] = empl.getPrenom();
            tab[3] = empl.getEmail();
            tab[4] = empl.getTelephone();
            tab[5] = empl.getSalaire();
            tab[6] = empl.getRole();
            tab[7] = empl.getPoste();
            tabEmp.add(tab);
        }
		return tabEmp;
	}

}
