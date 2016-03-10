
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
 * Author is a POJO marked as persistent entity with table name
 * "bookauthors". It represents an author object with different variables:
 * <ul>
 * <li>authorId</li>
 * <li>nameF</li>
 * <li>nameL</li>
 * </ul>
 *{@link Set} of {@link Book} is joined via a many-to-many connection.
 * Author is the owned Entity
 * @author Madeleine
 *
 */
@Entity
@Table(name = "bookauthors", schema = "public", uniqueConstraints = { @UniqueConstraint(columnNames = "authorId") })
public class Author {
	
	private int authorId;
	private String nameF;
	private String nameL;

	private Set<Book> books = new HashSet<Book>(0);
	/**
	 * Default constructor
	 */
	private Author (){}
	/**
	 * @param nameF
	 * @param nameL
	 */
	public Author (String nameF, String nameL){
		this.nameF = nameF;
		this.nameL = nameL;
	}

	/**
	 * Annotation: GenerationType Identity, does not generate Ids by incrementing (which causes problems with already stored in the database
	 * @return
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "authorId", unique = true, nullable = false)
	public int getAuthorId() {
		return authorId;
	}

	public void setId(int id) {
		this.authorId = id;
	}

	@Column(name = "nameF", nullable = false)
	public String getNameF() {
		return nameF;
	}

	public void setNameF(String nameF) {
		this.nameF = nameF;
	}

	@Column(name = "nameL", nullable = false)
	public String getNameL() {
		return nameL;
	}

	public void setNameL(String nameL) {
		this.nameL = nameL;
	}

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "authors")
	public Set<Book> getBooks() {
		return books;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Author [authorId=" + authorId + ", nameF=" + nameF + ", nameL=" + nameL + "]";
	}

}
