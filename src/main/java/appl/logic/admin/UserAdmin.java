package appl.logic.admin;

import appl.data.dao.impl.UserDAOImpl;

public interface UserAdmin {

	public void createUser();

	public void deleteUser();

	public void updateUser();

	// TODO Beans?
	public UserDAOImpl getUser();

}
