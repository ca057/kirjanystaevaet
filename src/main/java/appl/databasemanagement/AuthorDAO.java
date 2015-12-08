package appl.databasemanagement;

import java.util.List;

import appl.items.Author;

public interface AuthorDAO {

	public List<Author> getAuthors();

	public List<Author> getAuthorsByNameF(String nameF);

	public List<Author> getAuthorsByNameL(String nameL);

	public Author getAuthorByID(int authorID);

}
