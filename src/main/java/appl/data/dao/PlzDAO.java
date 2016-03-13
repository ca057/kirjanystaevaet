package appl.data.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import appl.data.items.PLZ;
import exceptions.data.DatabaseException;

/**
 * Interface for a Data Access Object. Manages all access to the database
 * connected with the {@link PLZ} entity.
 * 
 * @author Hannes
 *
 */
@Transactional
public interface PlzDAO {

	/**
	 * Creates a new {@link PLZ}.
	 * 
	 * @param postalCode
	 *            the postal code
	 * @param place
	 *            the name of the region
	 * @return true if successful
	 * @throws DatabaseException
	 *             if an error occurs while accessing the database
	 */
	public boolean insertPLZ(String postalCode, String place) throws DatabaseException;

	/**
	 * Updates an existing {@link PLZ} object.
	 * 
	 * @param plzId
	 *            the id of the {@code PLZ}
	 * @param postalCode
	 *            the postal code
	 * @param place
	 *            the name of the region
	 * @return true if successful
	 * @throws DatabaseException
	 *             if an error occurs while accessing the database
	 */
	public boolean updatePLZ(int plzId, String postalCode, String place) throws DatabaseException;

	public Optional<PLZ> getPLZ(int plzId);

	public Optional<PLZ> getPLZ(String postalCode, String place);

	public Optional<Integer> getPLZId(String postalCode, String place);

	public List<PLZ> getPLZByPostalCode(String postalCode);

	public List<PLZ> getPLZByPlace(String place);

}
