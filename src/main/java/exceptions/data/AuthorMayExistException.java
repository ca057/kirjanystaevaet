package exceptions.data;

public class AuthorMayExistException extends DatabaseException {

	public AuthorMayExistException() {
	}

	public AuthorMayExistException(String message) {
		super(message);
	}

	public AuthorMayExistException(Throwable cause) {
		super(cause);
	}

	public AuthorMayExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthorMayExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
