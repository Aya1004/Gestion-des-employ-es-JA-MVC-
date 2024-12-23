package Main;

import Controller.CongeController;
import Controller.EmployeController;
import DAO.CongeDAOImpl;
import DAO.EmployeDAOImpl;
import Model.CongeModel;
import Model.EmployeModel;
import View.CongeView;
import View.EmployeView;

public class main {

	public static void main(String[] args) {
     	EmployeDAOImpl dao = new EmployeDAOImpl();
		EmployeModel model = new EmployeModel(dao);
		EmployeView view = new EmployeView();
		EmployeController controller= new EmployeController( view, model);
	}

}
