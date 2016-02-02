package exceptions.data;

public class PrimaryKeyViolation extends Exception {

	public PrimaryKeyViolation() {
	}

	public PrimaryKeyViolation(String message) {
		super(message);
	}

	public PrimaryKeyViolation(Throwable cause) {
		super(cause);
	}

	public PrimaryKeyViolation(String message, Throwable cause) {
		super(message, cause);
	}

	public PrimaryKeyViolation(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
