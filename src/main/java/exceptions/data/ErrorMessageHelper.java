package exceptions.data;

public abstract class ErrorMessageHelper {

	public static String nullOrEmptyMessage(String field) {
		return String.format("%s must not be null or empty string.", field);
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

}
