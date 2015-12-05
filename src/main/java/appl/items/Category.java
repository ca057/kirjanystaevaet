package appl.items;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "bookcategories", catalog = "public", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CATEGORYID") })
public class Category {
	private int categoryID;
	private String CategoryName;

	private Set<Book> books = new HashSet<Book>(0);

	private Category() {

	}

	public Category(String categoryName, Set<Book> books) {
		super();
		CategoryName = categoryName;
		this.books = books;
	}

	@Id
	// @GeneratedValue
	@Column(name = "CATEGORYID", unique = true, nullable = false)
	public int getCategoryID() {
		return categoryID;
	}

	@Column(name = "CATEGORYNAME", nullable = false)
	public String getCategoryName() {
		return CategoryName;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}

}
