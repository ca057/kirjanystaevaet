package appl.usermanagement;

import appl.usermanagement.usermanagementImpl.UserImpl;

public interface UserAdmin {

	public void createUser();

	public void deleteUser();

	public void updateUser();

	// TODO Beans?
	public UserImpl getUser();

}
