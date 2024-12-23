package default_package;

import java.sql.Connection;

import Controller.CongeController;
import Controller.EmployeController;
import DAO.CongeDAOImpl;
import DAO.EmployeDAOImpl;
import Model.CongeModel;
import Model.EmployeModel;
import View.CongeView;
import View.EmployeView;

public class Main {
		public static void main(String[] args) {
			CongeDAOImpl dao = new CongeDAOImpl();
			CongeModel model = new CongeModel(dao);
			CongeView view =new CongeView();
			CongeController controller= new CongeController( view, model);
		}

}
