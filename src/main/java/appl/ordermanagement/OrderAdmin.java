package appl.ordermanagement;

import appl.ordermanagement.ordermanagementImpl.OrderImpl;

/**
 * 
 * TODO JAVADOC
 */
public interface OrderAdmin {

	public void createOrder();

	public void deleteOrder();

	public OrderImpl getOrder();

}
