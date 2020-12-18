import java.util.ArrayList;

import dao.UserDAO;
import model.User;

public class UserApp {

	public static void main(String[] args) {
		UserDAO userDAO = new UserDAO();
		ArrayList<User> list = new ArrayList<>();
		list = userDAO.찾기();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	}
}
