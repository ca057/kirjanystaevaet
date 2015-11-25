package appl.ordermanagement;

import appl.ordermanagement.ordermanagementImpl.OrderImpl;

public interface OrderAdmin {

	public void createOrder();

	public void deleteOrder();

	public OrderImpl getOrder();

}
