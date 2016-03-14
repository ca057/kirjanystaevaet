package appl.logic.service;

import java.util.Map;

import exceptions.data.DatabaseException;

/**
 * The kraken is hungry. Let him eat all the data from your services and be
 * aware of his attack. In the moment it only collects all the data, but it
 * should be able to do more in the future.
 * 
 * @author Davy Jones
 *
 */
public interface DataKraken {

	/**
	 * The kraken is awake? Be aware of his attack and use the data wisely.
	 * 
	 * @return all the data the kraken could found all around the seas
	 * @throws DatabaseException
	 *             if it gets sick while searching
	 */
	public Map<String, ?> attack() throws DatabaseException;

}
