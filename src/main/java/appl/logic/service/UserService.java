package appl.logic.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import appl.data.enums.Userfields;
import appl.data.items.User;
import exceptions.data.PrimaryKeyViolationException;

@Service
public interface UserService {

	int registerNewUserAccount(Map<Userfields, String> data) throws PrimaryKeyViolationException;

	/**
	 * Returns null (better Exception?) if User not found.
	 * 
	 * @param eMail
	 * @return
	 */
	User findbyMail(String eMail);

	User findByID(int id);

	List<User> getUsers();

}
