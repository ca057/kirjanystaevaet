package appl.data.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import appl.data.items.Author;

public interface AuthorDAO {

	@Transactional
	public List<Author> getAuthors();

	@Transactional
	public List<Author> getAuthorsByNameF(String nameF);

	@Transactional
	public List<Author> getAuthorsByNameL(String nameL);

	@Transactional
	public Author getAuthorByID(int authorID);

	@Transactional
	public boolean insertAuthor(Author author);

	@Transactional
	public boolean deleteAuthor(Author author);

	@Transactional
	public boolean updateAuthor(Author author);

}
