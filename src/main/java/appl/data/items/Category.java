package appl.data.items;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * Category is a POJO marked as persistent entity with table name
 * "bookcategories". It represents a category object with different variables:
 * <ul>
 * <li>categoryId</li>
 * <li>categoryName</li>
 * </ul>
 *{@link Set} of {@link Book} is joined via a many-to-many connection.
 * Category is the owned Entity
 * 
 * @author Madeleine
 */
@Entity
@Table(name = "bookcategories", schema = "public", uniqueConstraints = {
		@UniqueConstraint(columnNames = "categoryId") })
public class Category {
	private int categoryId;
	private String categoryName;

	private Set<Book> books = new HashSet<Book>(0);
	/**
	 * Default Constructor, needed by Hibernate
	 */
	private Category(){}
	/**
	 * Constructor used to build objects that are already persisted in the database
	 * @param categoryId
	 * @param categoryName
	 */
	public Category(int categoryId, String categoryName){
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	/**
	 * Constructuor used to build objects that will be newly stored in the database
	 * @param categoryName
	 */
	public Category(String categoryName) {
		this.categoryName = categoryName;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "categoryId", unique = true, nullable = false)
	public int getCategoryID() {
		return categoryId;
	}

	@Column(name = "categoryName", unique = true, nullable = false)
	public String getCategoryName() {
		return categoryName;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
	public Set<Book> getBooks() {
		return books;
	}

	private void setBooks(Set<Book> books) {
		this.books = books;
	}

	private void setCategoryID(int categoryID) {
		this.categoryId = categoryID;
	}

	private void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "Category [categoryID=" + categoryId + ", CategoryName=" + categoryName + "]";
	}

}

