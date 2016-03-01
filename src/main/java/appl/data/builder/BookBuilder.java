package appl.data.builder;

import java.util.Set;

import appl.data.items.Author;
import appl.data.items.Book;
import appl.data.items.Category;

/**
 * Builder to create a new object of the {@link Book} class.
 * 
 * @author Madeleine
 *
 */
public interface BookBuilder {

	public BookBuilder setIsbn(String isbn);

	public BookBuilder setTitle(String title);

	public BookBuilder setDescription(String description);

	public BookBuilder setPrice(double price);

	public BookBuilder setPublisher(String publisher);

	public BookBuilder setPubdate(String pubdate);

	public BookBuilder setEdition(String edition);

	public BookBuilder setPages(String pages);

	public BookBuilder setAuthors(Set<Author> authors);

	public BookBuilder setCategories(Set<Category> categories);
	
	public BookBuilder setStock(int stock);

	public Book createBook();
}
