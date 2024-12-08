package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import Model.Poste;
import Model.Role;


public class EmployeView extends JFrame {
	//Panel
	 private JPanel pan = new JPanel();
	 private JPanel pan2 = new JPanel();
	 private JPanel pan3 = new JPanel();
	 private JPanel pan4 = new JPanel();
	 //TextField/ComboBox
	 public JTextField saisie =new JTextField(20);
	 public JTextField saisie1 =new JTextField(20);
	 public JTextField saisie2 =new JTextField(20);
	 public JTextField saisie3 =new JTextField(20);
	 public JTextField saisie4 =new JTextField(20);
	 public JComboBox choix =new JComboBox(Role.values());
	 public JComboBox choix2 =new JComboBox(Poste.values());
	 public JSpinner idSpinner;
	 //Button
	 public JButton ajou =new JButton("Ajouter");
	 public JButton supp =new JButton("Supprimer");
	 public JButton aff =new JButton("Afficher");
	 public JButton modif =new JButton("Modifier");
	 //Table
	    public JTable table;
	    public DefaultTableModel model;
	    public JScrollPane scrollPane;


	 public EmployeView() {
		 setTitle("ma fenêtre");
			setSize(600,400);
			add(pan);
			pan.setLayout(new BorderLayout());
			pan.add(pan4,BorderLayout.SOUTH); 
			pan.add(pan2,BorderLayout.NORTH);
			pan.add(pan3,BorderLayout.CENTER);
			
			pan2.setLayout(new GridLayout(8,2));
			
			 pan2.add(new JLabel("Id"));
	         idSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 1000000, 1)); 
	         pan2.add(idSpinner);
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
			 
		     String[] columnNames = {"Id","Nom", "Prenom", "Telephone", "Email", "Salaire", "Role", "Poste"};
		        model = new DefaultTableModel(columnNames, 0);
		        table = new JTable(model);
		        scrollPane = new JScrollPane(table);

			 pan3.add(new JScrollPane(table));
			 
			 setDefaultCloseOperation(EXIT_ON_CLOSE);
			 setLocationRelativeTo(null);
			    setVisible(true);
	 }
}
