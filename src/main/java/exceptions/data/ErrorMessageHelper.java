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
	
	/**
	 * Use if an error occurs while trying to insert something
	 * @param entity
	 * @return
	 */
	public static String insertFailed(String entity){
		return String.format("Insert of %s failed", entity);
		
	}
	/**
	 * Use if one tries to decrement stock that is already 0
	 * @param bookTitle
	 * @return
	 */
	public static String stockIsNull(String bookTitle){
		return String.format("Book with title %s is not available. Stock cannot be decremeted anymore", bookTitle);
	}

	/**
	 * Use if one tries to update a field which can not be updated, especially isbn
	 * @param field
	 * @return
	 */
	public static String mayNotBeUpdated(String field){
		return String.format("The field %s may not be updated", field);
	}
	
	/**
	 * Use if one tries to order a book which is marked as deleted (Stock = -1)
	 * @param isbn
	 * @return
	 */
	public static String bookNotSold(String isbn){
		return String.format("Book with isbn %s ist not sold anymore", isbn);
		
	}
	/**
	 * use of one tries to access a book that is not available
	 * @param isbn
	 * @return
	 */
	public static String bookNotAvailable(String isbn){
		return String.format("Book with isbn %s is not available right now", isbn);
	}
	
	/**
	 * Used if generally a database request is unsuccessful
	 * @param entity
	 * @return
	 */
	public static String couldNotGetData(String entity){
		return String.format("Could not get %s from database", entity);
	}
	/**
	 * Used if deletion was generally unsuccessful
	 * @param entity of the object to be deleted
	 * @param id of the object to be deleted
	 * @param information further info like exception message
	 * @return
	 */
	public static String deletionFailed(String entity, String id, String information){
		return String.format("Deletion of %s with id %s failed\n information", entity, id, information);
	}

	public static String increaseOfVisitCountFailed(String entity, String id, String information){
		return String.format("Increase of visitCount of %s with identifier %s failed\n %s", entity, id, information);
	}
}
