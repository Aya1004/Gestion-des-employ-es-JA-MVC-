package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Model.EmployeModel;
import Model.Poste;
import Model.Role;
import View.EmployeView;

public class EmployeController {
	private EmployeView view;
	private EmployeModel model;
	public EmployeController(EmployeView view,EmployeModel model) {
		this.view=view;
		this.model=model;
		this.view.ajou.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
            	int id = (int) view.idSpinner.getValue();
				String nom=view.saisie.getText();
				String prenom=view.saisie1.getText();
				String email=view.saisie2.getText();
				String telephone=view.saisie3.getText();
				double salaire=Double.parseDouble(view.saisie4.getText());
				Role role=(Role) view.choix.getSelectedItem();
				Poste poste=(Poste) view.choix2.getSelectedItem();
				model.addEmploye(id, nom, prenom, email, telephone, salaire, role, poste);
				Object[] row = {id,nom, prenom,email, telephone, salaire, role, poste};
                System.out.println(id+nom+prenom+email+telephone+salaire+role+poste);
                view.model.addRow(row);
			}
		});
		this.view.modif.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = view.table.getSelectedRow();
                if (selectedRow != -1) { 
                	int id = (int) view.idSpinner.getValue();
                    String nom = view.saisie.getText();
                    String prenom = view.saisie1.getText();
                    String email = view.saisie2.getText();
                    String telephone = view.saisie3.getText();
                    double salaire = 0;
                    try {
                        salaire = Double.parseDouble(view.saisie4.getText());
                    }catch(NumberFormatException ex) {
                        JOptionPane.showMessageDialog(view, "Entrée de salaire invalide. Valeur par défaut définie.");
                    }
                    Role role = (Role) view.choix.getSelectedItem();
                    Poste poste = (Poste) view.choix2.getSelectedItem();
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
			Object[][] Temployes=model.employes();
			view.model.setRowCount(0);
			for(Object[] emp :Temployes) {
				view.model.addRow(emp);
			}
			}
		});
	}
}
