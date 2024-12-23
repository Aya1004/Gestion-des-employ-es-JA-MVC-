 package Model;

import javax.swing.ComboBoxModel;

public class Employe {
	private int id;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private double salaire;
	private Role role;
	private Poste poste;
	private  int usedBalnce;
	

	public Employe(int id,String nom,String prenom,String email,String telephone,double salaire,Role role,Poste poste) {
		this.id=id;
		this.nom=nom;
		this.prenom=prenom;
		this.email=email;
		this.telephone=telephone;
		this.salaire=salaire;
		this.role=role;
		this.poste=poste;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public double getSalaire() {
		return salaire;
	}
	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}
	public String getRole() {
		return role.name();
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getPoste() {
		return poste.name();
	}
	public void setPoste(Poste poste) {
		this.poste = poste;
	}
	public int getUsedBalance() {
		return usedBalnce;
	}
	public void setUsedBalance(int usedBalnce) {
		this.usedBalnce = usedBalnce;
	}

	public static ComboBoxModel values() {
		// TODO Auto-generated method stub
		return null;
	}

}
