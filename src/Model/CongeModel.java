package Model;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;

import DAO.CongeDAOImpl;
import DAO.EmployeDAOImpl;
public class CongeModel {
private CongeDAOImpl dao;

	public CongeModel(CongeDAOImpl dao) {
		this.dao=dao;
	}
	public boolean add(String nom, String DateDebut, String DateFin, TypeCong type) {
		try {
			Date DateD=Date.valueOf(DateDebut);
			Date DateF=Date.valueOf(DateFin);
			
			// Vérification si la date de début est avant la date de fin
	        if (DateD.after(DateF)) {
	            System.out.println("La date de début doit être avant la date de fin.");
	            return false;
	        }
		}catch(IllegalArgumentException e) {
			System.out.println("Les dates doivent être au format 'yyyy-MM-dd'.");
	        return false;
		}
		Conge conge= new Conge(0,nom,DateDebut,DateFin,type);
		dao.add(conge);
		return true;
	}
	
	   public boolean delete(int id) {
				if(id<0) {
					return false;
				}
				dao.delete(id);
				return true;
			}
	   public boolean update(int id,String nom,String dateDebut, String dateFin, TypeCong type) {
			
			try {
		       Date DateD = Date.valueOf(dateDebut);
		       Date DateF = Date.valueOf(dateFin);

		        // Vérification si la date de début est avant la date de fin
		        if (DateD.after(DateF)) {
		            System.out.println("La date de début doit être avant la date de fin.");
		            return false;
		        }
		    } catch (IllegalArgumentException e) {
		        System.out.println("Les dates doivent être au format 'yyyy-MM-dd'.");
		        return false;
		    }
			Conge nv= new Conge(id,nom,dateDebut,dateFin,type);
			dao.update(nv);
			return true;
		}
	   public List<Conge> afficher() {
			 return dao.afficher();
		}
	 public List<Employe> getEmployesName()
	 {
		 return dao.getEmployesName();
	 }
	   public int getSolde(String nom) {
		   return dao.getSolde(nom);
	   }
	   
	    public void updateSolde(String nom, int nouveauSolde) {
	    	dao.updateSolde(nom, nouveauSolde);
	    }
	    
}
