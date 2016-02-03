package appl.data.items;

public abstract class ErrorMessageHelper {

	static String nullOrEmptyMessage(String field) {
		return String.format("%s must not be null or empty string.", field);
	}

}
