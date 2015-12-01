package appl.items;

public class Author {
	private int id;
	private String nameF;
	private String nameL;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public void setAll(int id, String nameF, String nameL) {
		this.id = id;
		this.nameF = nameF;
		this.nameL = nameL;
	}

}
