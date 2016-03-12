package exceptions.data;

public class AuthorNotFoundException extends DatabaseException {

	public AuthorNotFoundException() {
	}

	public AuthorNotFoundException(String arg0) {
		super(arg0);
	}

	public AuthorNotFoundException(Throwable arg0) {
		super(arg0);
	}

	public AuthorNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public AuthorNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
