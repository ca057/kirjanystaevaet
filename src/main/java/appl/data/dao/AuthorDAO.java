package appl.data.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import appl.data.items.Author;
import exceptions.data.AuthorNotFoundException;
import exceptions.data.EntityDoesNotExistException;

@Transactional
public interface AuthorDAO {

	public List<Author> getAuthors();

	public List<Author> getAuthorsByNameF(String nameF);

	public List<Author> getAuthorsByNameL(String nameL);
	
	public List<Author> getAuthorByExactNames (String nameF, String nameL);

	public Author getAuthorByID(int authorID) throws EntityDoesNotExistException;

	public int insertAuthor(Author author);

	public void deleteAuthor(Author author);

	public void updateAuthor(Author author);

}
