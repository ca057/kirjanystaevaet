package exceptions.data;

public class PrimaryKeyViolationException extends Exception {

	public PrimaryKeyViolationException() {
	}

	public PrimaryKeyViolationException(String message) {
		super(message);
	}

	public PrimaryKeyViolationException(Throwable cause) {
		super(cause);
	}

	public PrimaryKeyViolationException(String message, Throwable cause) {
		super(message, cause);
	}

	public PrimaryKeyViolationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
