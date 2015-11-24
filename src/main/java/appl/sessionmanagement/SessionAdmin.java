package appl.sessionmanagement;

public interface SessionAdmin {

	public void createSession();

	public boolean isStarted();

	public boolean login();

	public boolean endSession();

	public boolean saveSession();

}
