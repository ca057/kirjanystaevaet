package appl.logic.orders;

import appl.logic.orders.impl.OrderImpl;

/**
 * 
 * TODO JAVADOC
 */
public interface OrderAdmin {

	public void createOrder();

	public void deleteOrder();

	public OrderImpl getOrder();

}
