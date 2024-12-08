package default_package;

import Controller.EmployeController;
import DAO.EmployeDAOI;
import DAO.EmployeDAOImpl;
import Model.EmployeModel;
import View.EmployeView;

public class Main {
		public static void main(String[] args) {
			EmployeDAOImpl dao = new EmployeDAOImpl();
			EmployeModel model = new EmployeModel(dao);
			EmployeView view = new EmployeView();
			EmployeController controller= new EmployeController( view, model);

		}

}
