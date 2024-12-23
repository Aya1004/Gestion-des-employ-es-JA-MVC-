package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Model.Employe;
import Model.Poste;
import Model.Role;
import Model.TypeCong;

public class CongeView extends JFrame{

		//Panel
		 private JPanel congePanel = new JPanel();
		 private JPanel pan2 = new JPanel();
		 private JPanel pan3 = new JPanel();
		 private JPanel pan4 = new JPanel();
		 //TextField/ComboBox
		    public JComboBox<String> dateD = new JComboBox<>();
		    public JComboBox<String> dateF = new JComboBox<>();
		    public JComboBox<String> nom = new JComboBox<>();
		    public JComboBox<TypeCong> choix2 = new JComboBox<>(TypeCong.values());

		 //Button
		 public JButton ajouC =new JButton("Ajouter");
		 public JButton suppC =new JButton("Supprimer");
		 public JButton affC =new JButton("Afficher");
		 public JButton modifC =new JButton("Modifier");
		 public JButton Employes = new JButton("Employes");
		 public JButton Conges = new JButton("Conges");
		 //Table
		    public JTable table;
		    public DefaultTableModel model;
		    public JScrollPane scrollPane;
		 //Switch
		   

		 public CongeView() {
				setSize(700,600);

		        setDefaultCloseOperation(EXIT_ON_CLOSE);
		        setLocationRelativeTo(null);
				add(congePanel);
				congePanel.add(Employes);
				congePanel.add(Conges);
				congePanel.setLayout(new BorderLayout());
				congePanel.add(pan4,BorderLayout.SOUTH); 
				congePanel.add(pan2,BorderLayout.NORTH);
				congePanel.add(pan3,BorderLayout.CENTER);
				

				pan2.setLayout(new GridLayout(4,2));
				
				  pan2.add(new JLabel("Nom de l'employé :"));
			        pan2.add(nom);
			        pan2.add(new JLabel("Type de congé"));
			        pan2.add(choix2);
			        pan2.add(new JLabel("Date de début :"));
			        pan2.add(dateD);
			        pan2.add(new JLabel("Date de fin :"));
			        pan2.add(dateF);
				 
				 pan4.setLayout(new FlowLayout());
				 pan4.add(ajouC);
				 pan4.add(suppC);
				 pan4.add(affC);
				 pan4.add(modifC);
				 
			     String[] columnNames = { "Id", "Date Debut", "Date Fin", "Employé", "Type"};
			        model = new DefaultTableModel(columnNames, 0);
			        table = new JTable(model);
			        scrollPane = new JScrollPane(table);

				 pan3.add(new JScrollPane(table));
				 
				  
				    setVisible(true);
		 }
	
		 public String getNom() {
		        return (String) nom.getSelectedItem();
		    }
		    
		    public String getSelectedType() {
		        return (String) choix2.getSelectedItem();
		    }
		    
		    public String getDateDebut() {
		        return (String) dateD.getSelectedItem();
		    }
		    
		    public String getDateFin() {
		        return (String) dateF.getSelectedItem();
		    }
		    public int getSolde() {
		        if (getDateDebut() != null && getDateFin() != null) {
		            // Conversion des chaînes de caractères en LocalDate
		            LocalDate debut = LocalDate.parse(getDateDebut());
		            LocalDate fin = LocalDate.parse(getDateFin());
		            return (int) java.time.temporal.ChronoUnit.DAYS.between(debut, fin);
		        } else {
		            throw new IllegalArgumentException("Les dates de début et de fin ne doivent pas être nulles.");
		        }
		    }

		    public JComboBox<TypeCong> getTypes() { return choix2; }
		    public JComboBox<String> getNomComboBox() {
		        return nom;
		    }
		    
		    public JComboBox<String> DateDebut() {
		        return DateDebut();
		    }

		    public JComboBox<String> DateFin() {
		        return DateFin();
		    }
		    public void clearFields() {
		        choix2.setSelectedIndex(-1); // Réinitialise la sélection de la ComboBox
		        nom.setSelectedIndex(-1);
		        dateD.setSelectedIndex(-1);
		        dateF.setSelectedIndex(-1);
		    }
		    public static void remplirComboBox(JComboBox<String> comboBox, List<String> items) {
		        if (comboBox == null) {
		            throw new IllegalArgumentException("Le JComboBox fourni est null.");
		        }
		        if (items == null || items.isEmpty()) {
		            System.out.println("Aucune donnée à ajouter dans le JComboBox.");
		            return;
		        }

		        comboBox.removeAllItems();
		        for (String item : items) {
		            comboBox.addItem(item);
		        }
		    }
		    public static void remplirCombo(JComboBox<String> comboBox, List<Employe> items) {
		        comboBox.removeAllItems();
		        for (Employe employee : items) {
		            comboBox.addItem(employee.getNom());
		        }
		    }
		    public void afficherMessageError(String message) {
				JOptionPane.showMessageDialog(this, message,"Error",JOptionPane.ERROR_MESSAGE);
			}
			public void afficherMessageSuccess(String message) {
				JOptionPane.showMessageDialog(this, message,"Succes",JOptionPane.INFORMATION_MESSAGE);
			}
			 public void updateTable(Object[][] data) {
			        model.setRowCount(0); 
			        for (Object[] row : data) {
			        	model.addRow(row);
			        }
			 }
	}