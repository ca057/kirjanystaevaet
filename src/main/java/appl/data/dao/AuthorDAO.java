package appl.data.dao;

import java.util.List;

import appl.data.items.Author;

public interface AuthorDAO {

	public List<Author> getAuthors();

	public List<Author> getAuthorsByNameF(String nameF);

	public List<Author> getAuthorsByNameL(String nameL);

	public Author getAuthorByID(int authorID);

}
