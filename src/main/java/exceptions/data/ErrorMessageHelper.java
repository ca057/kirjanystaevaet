package exceptions.data;

public abstract class ErrorMessageHelper {

	public static String nullOrEmptyMessage(String field) {
		return String.format("%s must not be null or empty string.", field);
	}

	public static String primaryKeyViolation() {
		return String.format("PrimaryKeyViolation, Keys must be unique");
	}

	public static String generalDatabaseError(String field) {
		return String.format("General Database Error: %s", field);
	}

	public static String mayContainOnlyNumbers(String field) {
		return String.format("String %s may only contain digits", field);
	}

	public static String entityDoesNotExist(String entity) {
		return String.format("%s does not exist", entity);
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

	public static String updateError(String entity, String id, String message) {
		return String.format("Unable to update %s with id = %s: %s", entity, id, message);
	}

	public static String removeError(String entity, String id, String message) {
		return String.format("Unable to delete %s with id %s: %s", entity, id, message);
	}

}
