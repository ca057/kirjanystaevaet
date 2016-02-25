package appl.logic.service;

import java.util.Set;

public interface OrderService {
	public int createOrder(Set<String> isbns, int userId, int year, int month, int day);
}
