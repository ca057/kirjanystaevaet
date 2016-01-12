package appl.data.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import appl.data.items.Author;

@Transactional
public interface AuthorDAO {

	public List<Author> getAuthors();

	public List<Author> getAuthorsByNameF(String nameF);

	public List<Author> getAuthorsByNameL(String nameL);

	public Author getAuthorByID(int authorID);

	public void insertAuthor(Author author);

	public void deleteAuthor(Author author);

	public void updateAuthor(Author author);

}
