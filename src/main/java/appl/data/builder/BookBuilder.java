package appl.data.builder;

import java.util.Set;

import appl.data.items.Author;
import appl.data.items.Category;

public interface BookBuilder {
	public BookBuilder setIsbn (String isbn);
	
	public BookBuilder setTitle (String title);
	
	public BookBuilder setDescription (String descriptiont);
	
	public BookBuilder setPrice (double price );
	
	public BookBuilder setPublisher (String publisher);
	
	public BookBuilder setPubdate (String pubdate);
	
	public BookBuilder setEdition (String edition);
	
	public BookBuilder setPages (int pages);
	
	public BookBuilder setAuthors (Set<Author> authors);
	
	public BookBuilder setCategories (Set<Category> categories);
	
}
