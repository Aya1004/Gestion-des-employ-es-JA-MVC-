package DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Model.Conge;
import Model.Employe;
import Model.Poste;
import Model.TypeCong;
public class CongeDAOImpl implements GenericDAOI<Conge> {
private static DBConnection conn;
	
	public CongeDAOImpl() {
		conn=new DBConnection();	
		}
	@Override
	public void add(Conge cong) {
		String sql="INSERT INTO Conge (nom,dateDebut,dateFin,typee) VALUES (?,?,?,?)";
		try(PreparedStatement stmt=conn.getConnexion().prepareStatement(sql)){
			stmt.setString(1,cong.getNom());
			stmt.setDate(2,Date.valueOf(cong.getDateD()));
			stmt.setDate(3, Date.valueOf(cong.getDateF()));
			stmt.setString(4, cong.getType().name());
			stmt.executeUpdate();
		}catch(SQLException e) {
		e.printStackTrace();	
		}
	}
	@Override
	public void update(Conge cong) {
		String query = "UPDATE Conge SET dateDebut=?, dateFin=?, typee=?, nom=? WHERE idCong=?";
        try (PreparedStatement stmt = conn.getConnexion().prepareStatement(query)) {
            stmt.setDate(1, Date.valueOf(cong.getDateD()));
            stmt.setDate(2, Date.valueOf(cong.getDateF()));
            stmt.setString(3, cong.getType().name());
            stmt.setString(4, cong.getNom());
            stmt.setInt(5,cong.getIdC()); 
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
	@Override
	public void delete(int id) {
		 String query = "DELETE FROM Conge WHERE idCong=?";
	        try (PreparedStatement stmt = conn.getConnexion().prepareStatement(query)) {
	            stmt.setInt(1, id);
	            stmt.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
	}
	@Override
	public ArrayList<Conge> afficher(){
		ArrayList<Conge> cong = new ArrayList<>();
        String query = "SELECT* FROM Conge";

        try (PreparedStatement stmt = conn.getConnexion().prepareStatement(query);
        		ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                cong.add( new Conge(
                rs.getString("dateDebut"),
                rs.getString("dateFin"),
                rs.getString("nom"),
                	TypeCong.valueOf(rs.getString("typee"))
                	));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cong;
	}
	@Override
	public Conge findById(int id) {
		return null;
	}
	 public List<Employe> getEmployesName() {
	        List<Employe> employes = new ArrayList<>();
	        String query = "SELECT nom FROM Employee";

	        try (Statement stmt = conn.getConnexion().createStatement();
	             ResultSet rs = stmt.executeQuery(query)) {

	            while (rs.next()) {
	                Employe employe = new Employe(0, query, query, query, query, 0, null, null);
	                employe.setNom(rs.getString("nom"));
	                employes.add(employe);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return employes;
	    }
	 public int getSolde(String nom) {
	        String query = "SELECT used_balance FROM Employee WHERE nom = ?";
	        try (PreparedStatement stmt = conn.getConnexion().prepareStatement(query)) {
	            stmt.setString(1, nom);
	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    return rs.getInt("used_balance");
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return -1; // Retourne -1 si une erreur survient ou si l'employé n'existe pas
	    }
	 public void updateSolde(String nom, int nouveauSolde) {
	        String query = "UPDATE Employee SET used_balance = ? WHERE nom = ?";
	        try (PreparedStatement stmt = conn.getConnexion().prepareStatement(query)) {
	            stmt.setInt(1, nouveauSolde);
	            stmt.setString(2, nom);
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	
}
