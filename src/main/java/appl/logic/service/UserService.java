package appl.logic.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import appl.data.enums.Userfields;

@Service
public interface UserService {

	int registerNewUserAccount(Map<Userfields, String> data);

}
