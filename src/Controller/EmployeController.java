package Controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Model.CongeModel;
import Model.EmployeModel;
import Model.Poste;
import Model.Role;
import View.EmployeView;

public class EmployeController {
	private EmployeView view;
	private EmployeModel model;
	private CongeModel model2;

	public EmployeController(EmployeView view,EmployeModel model) {
		this.view=view;
		this.model=model;
		this.view.ajou.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("ajoute");

				 if (view.getLastName().getText().isEmpty() || view.getFirstName().getText().isEmpty() || view.getEmail().getText().isEmpty() || view.getPhone().getText().isEmpty()) {
	                    JOptionPane.showMessageDialog(null, "All fields must be filled out", "Input Error", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }
				String nom=view.getLastName().getText();
				String prenom=view.getFirstName().getText();
				String email=view.getEmail().getText();
				String telephone=view.getPhone().getText();
				Role role=(Role) view.getRoles().getSelectedItem();
				Poste poste=(Poste) view.getPostes().getSelectedItem();
				try {
					double salaire = Double.parseDouble(view.getSalary().getText());
					if(model.add(nom, prenom, email, telephone, salaire, role, poste)) {
						JOptionPane.showMessageDialog(null, "Employé ajouté avec succées","Comfirmation",JOptionPane.INFORMATION_MESSAGE);
					}
					else JOptionPane.showMessageDialog(null, "Echec d'ajout","Error",JOptionPane.ERROR_MESSAGE);
 
			    } catch(NumberFormatException er) {
			    	er.printStackTrace();
			    }
						}
			 
		});
		this.view.modif.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = view.table.getSelectedRow();
                if (selectedRow != -1) { 
                	int id = (int) view.table.getValueAt(selectedRow, 0);
                    String nom = view.getLastName().getText();
                    String prenom = view.getFirstName().getText();
                    String email = view.getEmail().getText();
                    String telephone = view.getPhone().getText();
                    double salaire = 0;
                    try {
                        salaire = Double.parseDouble(view.getSalary().getText());
                    }catch(NumberFormatException ex) {
                        JOptionPane.showMessageDialog(view, "Entrée de salaire invalide. Valeur par défaut définie.");
                    }
                    Role role = (Role) view.getRoles().getSelectedItem();
                    Poste poste = (Poste) view.getPostes().getSelectedItem();
                    model.update(id,nom, prenom, email, telephone, salaire, role, poste);
                    view.model.setValueAt(id,selectedRow,0);
                    view.model.setValueAt(nom, selectedRow, 1);
                    view.model.setValueAt(prenom, selectedRow, 2);
                    view.model.setValueAt(email, selectedRow, 3);
                    view.model.setValueAt(telephone, selectedRow, 4);
                    view.model.setValueAt(salaire, selectedRow, 5);
                    view.model.setValueAt(role, selectedRow, 6);
                    view.model.setValueAt(poste, selectedRow, 7);
			}else {
                JOptionPane.showMessageDialog(view, "Veuillez sélectionner une ligne à modifier.");
			}
		}
	});
		this.view.supp.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent e) {
				int selectedRow = view.table.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) view.model.getValueAt(selectedRow, 0);
                    model.delete(id);
                    view.model.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(view, "Veuillez sélectionner une ligne à supprimer.");
                }
		}
		});
		this.view.aff.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			 ArrayList<Object[]> employes = model.afficher();
			view.model.setRowCount(0);
			for(Object[] emp :employes) {
				view.model.addRow(emp);
			}
			}
		});
 }

}
