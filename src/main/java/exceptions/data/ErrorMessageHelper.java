package exceptions.data;

public abstract class ErrorMessageHelper {

	public static String nullOrEmptyMessage(String field) {
		return String.format("%s must not be null or empty string.", field);
	}

}
