package web.jsonwrappers;

import appl.data.items.Author;

/**
 * Wrapper for sending and receiving data of an {@link Author} as JSON to and
 * from the client.
 * 
 * @author Christian
 *
 */
public class AuthorJSONWrapper {
	private String nameF;
	private String nameL;
	private boolean newAuthor;

	public AuthorJSONWrapper() {

	}

	public String getNameF() {
		return nameF;
	}

	public void setNameF(String nameF) {
		this.nameF = nameF;
	}

	public String getNameL() {
		return nameL;
	}

	public void setNameL(String nameL) {
		this.nameL = nameL;
	}

	public boolean isNewAuthor() {
		return newAuthor;
	}

	public void setNewAuthor(boolean newAuthor) {
		this.newAuthor = newAuthor;
	}

	@Override
	public String toString() {
		return "AuthorJSONWrapper [nameF=" + nameF + ", nameL=" + nameL + ", newAuthor=" + newAuthor + "]";
	}

}
