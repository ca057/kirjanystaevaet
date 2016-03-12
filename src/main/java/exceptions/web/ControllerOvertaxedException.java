package exceptions.web;

public class ControllerOvertaxedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6037231727038290180L;

	public ControllerOvertaxedException() {
	}

	public ControllerOvertaxedException(String arg0) {
		super(arg0);
	}

	public ControllerOvertaxedException(Throwable arg0) {
		super(arg0);
	}

	public ControllerOvertaxedException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ControllerOvertaxedException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
