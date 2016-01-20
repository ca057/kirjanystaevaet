package appl.logic.service;

import java.util.Map;

import appl.data.enums.User;

public interface UserService {

	int registerNewUserAccount(Map<User, String> data);

}
