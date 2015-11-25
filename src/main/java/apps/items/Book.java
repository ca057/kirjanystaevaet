package apps.items;

public class Book {
	private String isbn;
	private String title;
	private String description;
	private double price;
	private String publisher;
	private String pubdate;
	private String edition;
	private String pages;

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getPubdate() {
		return pubdate;
	}

	public String getEdition() {
		return edition;
	}

	public String getPages() {
		return pages;
	}

}
