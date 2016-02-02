package exceptions.data;

public class DatabaseInitializationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DatabaseInitializationException() {
	}

	public DatabaseInitializationException(String arg0) {
		super(arg0);
	}

	public DatabaseInitializationException(Throwable arg0) {
		super(arg0);
	}

	public DatabaseInitializationException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DatabaseInitializationException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
