package appl.logic.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import appl.data.enums.Userfields;
import appl.data.items.User;

@Service
public interface UserService {

	int registerNewUserAccount(Map<Userfields, String> data);

	User findbyMail(String eMail);

}