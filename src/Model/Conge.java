package Model;

import java.sql.Date;
import java.time.LocalDate;

public class Conge {
	public int idCong;
	private String nom;
	private String dateDebut;
	private String dateFin;
	public  TypeCong type;
	
	public Conge(String nom,String dateDebut,String dateFin,TypeCong type) {
	this.nom=nom;
	this.dateDebut=dateDebut;
	this.dateFin=dateFin;
	this.type=type;
	}
	public int getIdC() {
		return idCong;
	}
	
	public void setIdC(int idCong) {
		this.idCong = idCong;
	}
   
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getDateD() {
		return dateDebut;
	}
	public void setDateD(String dateDebut) {
		this.dateDebut = dateDebut;
	}
	public String getDateF() {
		return dateFin;
	}
	public void setDateF(String dateFin) {
		this.dateFin = dateFin;
	}
	public 	TypeCong getType() {
		return type;
	}
	public void setType(TypeCong type) {
		this.type = type;
	}
	
}
