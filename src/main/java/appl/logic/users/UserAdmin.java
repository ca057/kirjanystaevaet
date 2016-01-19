package appl.logic.users;

import appl.data.dao.impl.UserDAOImpl;

// TODO remove this class --> admin gets its own package
public interface UserAdmin {

	public void createUser();

	public void deleteUser();

	public void updateUser();

	// TODO Beans?
	public UserDAOImpl getUser();

}
