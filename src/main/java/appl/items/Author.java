package appl.items;

import java.io.Serializable;
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

@Entity
@Table(name = "bookauthors", catalog = "public", uniqueConstraints = { @UniqueConstraint(columnNames = "AUTHORID") })
public class Author implements Serializable {
	private int authorId;
	private String nameF;
	private String nameL;

	private Set<Book> books = new HashSet<Book>(0);

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AUTHORID", unique = true, nullable = false)
	public int getAuthorId() {
		return authorId;
	}

	public void setId(int id) {
		this.authorId = id;
	}

	@Column(name = "NAMEF", nullable = false)
	public String getNameF() {
		return nameF;
	}

	public void setNameF(String nameF) {
		this.nameF = nameF;
	}

	@Column(name = "NAMEL", nullable = false)
	public String getNameL() {
		return nameL;
	}

	public void setNameL(String nameL) {
		this.nameL = nameL;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "authors")
	public Set<Book> getBooks() {
		return books;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

}
