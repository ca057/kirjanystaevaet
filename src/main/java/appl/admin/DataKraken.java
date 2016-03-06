package appl.admin;

import java.util.Map;

/**
 * The kraken is hungry. Let him eat all the data from your services and be
 * aware of his attack.
 * 
 * @author Davy Jones
 *
 */
public interface DataKraken {

	/**
	 * The kraken is awake? Be aware of his attack and use the data wisely.
	 * 
	 * @return all the data the kraken could found all around the seas
	 */
	public Map<String, ?> attack();

}
