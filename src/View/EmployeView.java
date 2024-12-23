package View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Model.Poste;
import Model.Role;

public class EmployeView extends JFrame {
    // Panel principal et panneaux de contenu
    private JPanel mainPanel = new JPanel();
    private JPanel employePanel = new JPanel();
    private CongeView congeView = new CongeView();  // Le panneau des congés
    private JPanel pan2 = new JPanel();
    private JPanel pan3 = new JPanel();
    private JPanel pan4 = new JPanel();

    // TextField/ComboBox
    public JTextField saisie = new JTextField(20);
    public JTextField saisie1 = new JTextField(20);
    public JTextField saisie2 = new JTextField(20);
    public JTextField saisie3 = new JTextField(20);
    public JTextField saisie4 = new JTextField(20);
    public JComboBox<Role> choix = new JComboBox<>(Role.values());
    public JComboBox<Poste> choix2 = new JComboBox<>(Poste.values());

    // Buttons
    public JButton ajou = new JButton("Ajouter");
    public JButton supp = new JButton("Supprimer");
    public JButton aff = new JButton("Afficher");
    public JButton modif = new JButton("Modifier");

    // Table
    public JTable table;
    public DefaultTableModel model;
    public JScrollPane scrollPane;

    // CardLayout pour gérer la navigation
    private CardLayout cardLayout;

    public EmployeView() {
        setTitle("Gestion des employés et des congés");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

     
         add(employePanel);
        // Panel Employé (panneau de contenu)
        employePanel.setLayout(new BorderLayout());
        employePanel.add(pan4, BorderLayout.SOUTH);
        employePanel.add(pan2, BorderLayout.NORTH);
        employePanel.add(pan3, BorderLayout.CENTER);

        // Configurer les éléments du panneau Employé
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

        String[] columnNames = {"Id", "Nom", "Prenom", "Telephone", "Email", "Salaire", "Role", "Poste"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);

        pan3.add(scrollPane);

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
}