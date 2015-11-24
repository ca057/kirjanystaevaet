package appl.ordermanagement;

import appl.ordermanagement.ordermanagementImpl.OrderImpl;

public interface orderAdmin {

	public void createOrder();

	public void deleteOrder();

	public OrderImpl getOrder();

}
