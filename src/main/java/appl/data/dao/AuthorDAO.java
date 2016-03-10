package appl.data.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import appl.data.items.Author;
import exceptions.data.EntityDoesNotExistException;

@Transactional
public interface AuthorDAO {

	/**
	 * 
	 * @return
	 */
	public List<Author> getAuthors();
	/**
	 * 
	 * @param isbn
	 * @return
	 */
	
	public List<Author> getAuthorsByIsbn(String isbn);
	/**
	 * 
	 * @param nameF
	 * @return
	 */

	public List<Author> getAuthorsByNameF(String nameF);

	/**
	 * 
	 * @param nameL
	 * @return
	 */
	public List<Author> getAuthorsByNameL(String nameL);
	/**
	 * Case-Sensitive
	 * @param nameF
	 * @param nameL
	 * @return
	 */
	public List<Author> getAuthorByExactNames (String nameF, String nameL);
	/**
	 * 
	 * @param authorID
	 * @return
	 * @throws EntityDoesNotExistException
	 */

	public Author getAuthorByID(int authorID) throws EntityDoesNotExistException;
	
	/**
	 * @param author must hold all data of author
	 * @return
	 */
	public int insertAuthor(Author author);
	/**
	 * deletes author physically
	 * @param author
	 */

	public void deleteAuthor(Author author);

	/**
	 * 
	 * @param author ust hold all data of author
	 */
	public void updateAuthor(Author author);

}
