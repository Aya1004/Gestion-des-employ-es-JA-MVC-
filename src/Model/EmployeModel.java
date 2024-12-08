package Model;

import java.util.List;

import DAO.EmployeDAOImpl;

public class EmployeModel {
	private EmployeDAOImpl dao;
	
	public EmployeModel(EmployeDAOImpl dao) {
		this.dao=dao;
	}
	public boolean addEmploye(int id,String nom, String prenom, String email, String telephone, double salaire, Role role, Poste poste) {
		if(salaire<4000) {
			System.err.println("Salaire incorect.");
			return false;
		}
		 if (telephone.length() != 10) {
	            System.err.println("Le numéro de telephone doit contenir exactement 10 chiffres.");
	            return false;
	        }
		 Employe emp = new Employe(id,nom, prenom, email, telephone, salaire, role, poste);
	        dao.addEmploye(emp);
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
	public Object[][] employes(){
		List<Employe>emp=dao.employes();
		Object[][] tab =new Object[emp.size()][8];
		for (int i = 0; i < emp.size(); i++) {
            Employe empl = emp.get(i);
            tab[i][0] = empl.getId();
            tab[i][1] = empl.getNom();
            tab[i][2] = empl.getPrenom();
            tab[i][3] = empl.getEmail();
            tab[i][4] = empl.getTelephone();
            tab[i][5] = empl.getSalaire();
            tab[i][6] = empl.getRole();
            tab[i][7] = empl.getPoste();
        }
		return tab;
	}

}
