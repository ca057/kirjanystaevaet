package exceptions.data;

/**
 * This abstract class helps to standardize error messages.
 * 
 * @author Johannes
 * @author Madeleine
 *
 */
public abstract class ErrorMessageHelper {

	/**
	 * Use if a field must not be null or an empty string.
	 * 
	 * @param field
	 *            the concerned field
	 * @return the formatted {@code String}
	 */
	public static String nullOrEmptyMessage(String field) {
		return String.format("%s must not be null or empty string.", field);
	}

	/**
	 * Use if a primary key is already in use.
	 * 
	 * @return the formatted {@code String}
	 */
	public static String primaryKeyViolation() {
		return String.format("PrimaryKeyViolation, Keys must be unique");
	}

	/**
	 * Use if a general error occurred while working with the database.
	 * 
	 * @param field
	 *            the error message
	 * @return the formatted {@code String}
	 */
	public static String generalDatabaseError(String message) {
		return String.format("General Database Error: %s", message);
	}

	/**
	 * Use if a field may only contain digits.
	 * 
	 * @param field
	 *            the concerned column
	 * @return the formatted {@code String}
	 */
	public static String mayContainOnlyNumbers(String field) {
		return String.format("String %s may only contain digits", field);
	}

	/**
	 * @param entity
	 *            the name of the entity class
	 * @return the formatted {@code String}
	 */
	public static String entityDoesNotExist(String entity) {
		return String.format("%s does not exist", entity);
	}
	
	/**
	 * Use if you may try to delete something that is still in relationship with some other entities
	 * @param entity1 you try to delete
	 * @param entity2 contained in entity
	 * @param errormessage original errormessage of the original exception
	 * @return
	 */
	public static String DataIntegrityViolation(String entity1, String entity2, String errormessage){
		return String.format("%s kann only be deleted when it does not contain any %s \n Original ErrorMessage: %s", entity1, entity2, errormessage);
	}

	/**
	 * Use if an object could not be saved to database. Expects to be followed
	 * by {@code e.getMessage()}.
	 * 
	 * @param entity
	 *            The name of the entity which holds the object
	 * @return the formatted {@code String}
	 */
	public static String couldNotBeSaved(String entity) {
		return String.format("%s could not be saved to database: ", entity);
	}

	/**
	 * Use if an error occurred while trying to update an entity
	 * 
	 * @param entity
	 *            the name of the entity which holds the object
	 * @param id
	 *            the id of the object which was supposed to be updated
	 * @param message
	 *            additional information or exception messages
	 * @return the formatted {@code String}
	 */
	public static String updateError(String entity, String id, String message) {
		return String.format("Unable to update %s with id = %s: %s", entity, id, message);
	}

	/**
	 * Use if an error occurred while trying to remove an entity
	 * 
	 * @param entity
	 *            the name of the entity which holds the object
	 * @param id
	 *            the id of the object which was supposed to be removed
	 * @param message
	 *            additional information or exception messages
	 * @return the formatted {@code String}
	 */
	public static String removeError(String entity, String id, String message) {
		return String.format("Unable to delete %s with id %s: %s", entity, id, message);
	}
	/**
	 * Used if one for example tries to insert an entity that already exists
	 * @param entity
	 * @return
	 */
	public static String entityDoesAlreadyExist(String entity){
		return String.format("%s does already exist", entity);
	}
	
	public static String insertFailed(String entity){
		return String.format("Insert of %s failed", entity);
		
	}
	public static String stockIsNull(){
		return String.format("Sorry, someone else was fast, the book is not available anymore. Stock = 0");
	}


}
