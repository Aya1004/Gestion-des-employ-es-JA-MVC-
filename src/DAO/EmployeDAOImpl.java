package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Model.Employe;
import Model.Poste;
import Model.Role;

public class EmployeDAOImpl implements EmployeDAOI {
	private static DBConnection conn;
	
	public EmployeDAOImpl() {
		conn=new DBConnection();
	}
	
	@Override
	public void addEmploye(Employe emp) {
		String sql="INSERT INTO Employee (id,nom,prenom,email,phone,salaire,role,poste) VALUES (?,?,?,?,?,?,?,?)";
		try(PreparedStatement stmt=conn.getConnexion().prepareStatement(sql)){
			stmt.setInt(1,emp.getId());
			stmt.setString(2,emp.getNom());
			stmt.setString(3,emp.getPrenom());
			stmt.setString(4,emp.getEmail());
			stmt.setString(5,emp.getTelephone());
			stmt.setDouble(6,emp.getSalaire());
			stmt.setString(7,emp.getRole());
			stmt.setString(8,emp.getPoste());
			stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Employe ajouté avec succées!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
		}catch(SQLException e) {
			System.out.println(e.getMessage());            JOptionPane.showMessageDialog(null, "Employee added successfully!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, "Echec d'ajout!"+e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	@Override
    public void update(Employe emp) {
        String sql = "UPDATE Employee SET nom = ? , prenom = ? , email = ?,phone = ?, salaire = ?, role = ?, poste = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.getConnexion().prepareStatement(sql)) {
            stmt.setString(1, emp.getNom());
            stmt.setString(2, emp.getPrenom());
            stmt.setString(3, emp.getEmail());
            stmt.setString(4, emp.getTelephone());
            stmt.setDouble(5, emp.getSalaire());
            stmt.setString(6, emp.getRole());
            stmt.setString(7, emp.getPoste());
            stmt.setInt(8, emp.getId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Employe modifié avec succées!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Aucun employé trouvé à mise à jour.", "Attention", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erreur lors de la mise à jour de l'employe : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
	@Override
    public void delete(int id) {
		String sql = "DELETE FROM Employee WHERE id = ?";
		try (PreparedStatement stmt = conn.getConnexion().prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Employé supprimé avec succès!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Aucun employé trouvé avec le nom et prénom donnés.", "Information", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erreur lors de la suppression de l'employé : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
	}
	public List<Employe>employes() {
		List<Employe>emp=new ArrayList<>();
		String sql = "SELECT * FROM Employee";
		try (PreparedStatement stmt = conn.getConnexion().prepareStatement(sql);
				ResultSet rslt=stmt.executeQuery()){
			while(rslt.next()) {
				emp.add(new Employe(
						rslt.getInt("id"),
						rslt.getString("nom"),
						rslt.getString("prenom"),
						rslt.getString("email"),
						rslt.getString("phone"),
						rslt.getDouble("salaire"),
						Role.valueOf(rslt.getString("role")),
						Poste.valueOf(rslt.getString("poste"))
						));
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erreur lors de la récupération des employés : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		return emp;
	}
}
