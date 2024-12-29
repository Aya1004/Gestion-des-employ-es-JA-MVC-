package DAO;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Model.Employe;
import Model.Poste;
import Model.Role;

public class EmployeDAOImpl implements GenericDAOI<Employe>{
	private static DBConnection conn;
	
	public EmployeDAOImpl() {
		conn=new DBConnection();
	}
	
	@Override
	public boolean add(Employe emp) {
		String sql="INSERT INTO Employee (nom,prenom,email,phone,salaire,role,poste) VALUES (?,?,?,?,?,?,?)";
		try(PreparedStatement stmt=conn.getConnexion().prepareStatement(sql)){
			stmt.setString(1,emp.getNom());
			stmt.setString(2,emp.getPrenom());
			stmt.setString(3,emp.getEmail());
			stmt.setString(4,emp.getTelephone());
			stmt.setDouble(5,emp.getSalaire());
			stmt.setString(6,emp.getRole());
			stmt.setString(7,emp.getPoste());
			stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Employe ajouté avec succées!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
		}catch(SQLException e) {
			System.out.println(e.getMessage());            
            JOptionPane.showMessageDialog(null, "Echec d'ajout!"+e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		return false;
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
	public ArrayList<Employe>afficher() {
		ArrayList<Employe>emp=new ArrayList<>();
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

	@Override
    public Employe findById(int id) {
        String sql = "SELECT * FROM Employee WHERE id = ?";
        try (PreparedStatement stmt = conn.getConnexion().prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Employe(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getDouble("salaire"),
                    Role.valueOf(rs.getString("role")),
					Poste.valueOf(rs.getString("poste"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retourne null si l'employé n'est pas trouvé
    }
	 public List<String> getEmployeeNames() {
	        List<String> employeeNames = new ArrayList<>();
	        try {
	            String query = "SELECT nom, prenom FROM Employee";
	            PreparedStatement statement = conn.getConnexion().prepareStatement(query);
	            ResultSet resultSet = statement.executeQuery();
	            while (resultSet.next()) {
	                String fullName = resultSet.getString("nom") + " " + resultSet.getString("prenom");
	                employeeNames.add(fullName);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return employeeNames;
	    }

	 @Override
	 public int importData(File file) throws IOException {
		    int counter = 0;
		    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
		        String line;
		        while ((line = reader.readLine()) != null) {
		                String[] values = line.split(",");
		                if (add(new Employe(
		                    Integer.parseInt(values[0]),
		                    values[1],
		                    values[2],
		                    values[3],
		                    values[4],
		                    Double.parseDouble(values[5]),
		                    Role.valueOf(values[6]),
		                    Poste.valueOf(values[7])
		                ))) {
		                    counter++;
		        }
		    }
		    return counter;
		}
	 }

	  @Override
	    public List<Employe> getAll() {
	        List<Employe> employers = new ArrayList<>();
	        try (ResultSet getResult = conn.getConnexion().prepareStatement("SELECT * FROM Employee").executeQuery()) {

	            while (getResult.next()) {
	                employers.add(new Employe(
	                    getResult.getInt("id"), 
	                    getResult.getString("nom"), 
	                    getResult.getString("prenom"), 
	                    getResult.getString("email"), 
	                    getResult.getString("phone"), 
	                    getResult.getDouble("salaire"), 
	                    Role.valueOf(getResult.getString("role")), 
	                    Poste.valueOf(getResult.getString("poste"))
	                ));
	            }

	        } catch (SQLException getException) {
	            getException.printStackTrace();
	        }
	        return employers;
	    }
	@Override
	public void exportData(File fileName) throws IOException {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName)))
		{	
			for(Employe employe : getAll()) {
				writer.write(
						employe.getId() + "," +
						employe.getNom()+ "," +
						employe.getPrenom()+ "," +
						employe.getEmail()+ "," +
						employe.getTelephone()+ "," +
						employe.getSalaire()+ "," +	
						employe.getRole()+ "," +
						employe.getPoste()
				);
				writer.newLine();
			}
		}
				
	}
}
