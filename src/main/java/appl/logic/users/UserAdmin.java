package appl.logic.users;

import appl.logic.users.impl.UserImpl;

public interface UserAdmin {

	public void createUser();

	public void deleteUser();

	public void updateUser();

	// TODO Beans?
	public UserImpl getUser();

}
