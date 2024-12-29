package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import Model.Poste;
import Model.Role;

public class EmployeView extends JFrame {
    private JPanel pan2 = new JPanel();
    private JPanel pan3 = new JPanel();
    private JPanel pan4 = new JPanel();
    public JTextField saisie = new JTextField(20);
    public JTextField saisie1 = new JTextField(20);
    public JTextField saisie2 = new JTextField(20);
    public JTextField saisie3 = new JTextField(20);
    public JTextField saisie4 = new JTextField(20);
    public JComboBox<Role> choix = new JComboBox<>(Role.values());
    public JComboBox<Poste> choix2 = new JComboBox<>(Poste.values());
    public JButton ajou = new JButton("Ajouter");
    public JButton supp = new JButton("Supprimer");
    public JButton aff = new JButton("Afficher");
    public JButton modif = new JButton("Modifier");
    public JButton impor = new JButton("Import");
    public JButton expor = new JButton("Export");

    public JButton Conges = new JButton("Aller à Congés");
    public JTable table;
    public DefaultTableModel model;

    public EmployeView() {
    	setSize(700,600);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        add(pan4, BorderLayout.SOUTH);
        add(pan2, BorderLayout.NORTH);
        add(pan3, BorderLayout.CENTER);

        pan2.setLayout(new GridLayout(8, 2));
        pan2.add(new JLabel("Nom"));
        pan2.add(saisie);
        pan2.add(new JLabel("Prenom"));
        pan2.add(saisie1);
        pan2.add(new JLabel("Email"));
        pan2.add(saisie2);
        pan2.add(new JLabel("Téléphone"));
        pan2.add(saisie3);
        pan2.add(new JLabel("Salaire"));
        pan2.add(saisie4);
        pan2.add(new JLabel("Role"));
        pan2.add(choix);
        pan2.add(new JLabel("Poste"));
        pan2.add(choix2);

        pan4.setLayout(new FlowLayout());
        pan4.add(ajou);
        pan4.add(supp);
        pan4.add(aff);
        pan4.add(modif);
        pan4.add(impor);
        pan4.add(expor);
        
        String[] columnNames = {"Id", "Nom", "Prenom", "Téléphone", "Email", "Salaire", "Role", "Poste"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        pan3.add(new JScrollPane(table));
        setVisible(true);
    }
    public JTextField getFirstName() { return saisie1; }
    public JTextField getLastName() { return saisie; }
    public JTextField getPhone() { return saisie3; }
    public JTextField getEmail() { return saisie2; }
    public JTextField getSalary() { return saisie4; }
    public JComboBox<Role> getRoles() { return choix; }
    public JComboBox<Poste> getPostes() { return choix2; }
    public JTable getTable() { return table; }
    public DefaultTableModel getModel() { return model; }
    public JButton getAddButton() { return ajou; }
    public JButton getUpdateButton() { return modif; }
    public JButton getDeleteButton() { return supp; }
    public JButton getDisplayButton() { return aff; }
    public void afficherMessageError(String message) {
		JOptionPane.showMessageDialog(this, message,"Error",JOptionPane.ERROR_MESSAGE);
	}
	public void afficherMessageSuccess(String message) {
		JOptionPane.showMessageDialog(this, message,"Succes",JOptionPane.INFORMATION_MESSAGE);
	}
}
