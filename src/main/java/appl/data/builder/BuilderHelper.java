package appl.data.builder;

import appl.data.items.Author;
import appl.data.items.Book;
import appl.data.items.Category;

/**
 * Class contains some methods that help to fill the Builder with data, the
 * further goal is to keep the Entity-Objects immutable.
 * 
 * @author Madeleine
 */
public abstract class BuilderHelper {

	/**
	 * 
	 * @param book
	 *            contains Data to be saved in the BookBuilder
	 * @param bookBuilder
	 * @return the bookBuilder filled with all the Data from Book
	 */
	public static BookBuilder saveOldValues(Book book, BookBuilder bookBuilder) {
		return bookBuilder.setUserBookStatistics(book.getUserBookStatistics()).setAuthors(book.getAuthors())
				.setCategories(book.getCategories()).setDescription(book.getDescription()).setEdition(book.getEdition())
				.setIsbn(book.getIsbn()).setPages(book.getPages()).setPrice(book.getPrice())
				.setPubdate(book.getPubdate()).setPublisher(book.getPublisher()).setStock(book.getStock())
				.setTitle(book.getTitle()).setVisitCount(book.getVisitCount());
	}

	/**
	 * 
	 * @param author
	 *            contains Data to be saved in the AuthorBuilder
	 * @param authorBuilder
	 * @return AuthorBuilder filled with all the Data from Author
	 */
	public static AuthorBuilder saveOldValues(Author author, AuthorBuilder authorBuilder) {
		return authorBuilder.setNameF(author.getNameF()).setNameL(author.getNameL()).setAuthorId(author.getAuthorId());
	}

	/**
	 * 
	 * @param category
	 *            contains Data to be saved in the CategoryBuilder
	 * @param categoryBuilder
	 * @return CategoryBuilder filled with all the Data from Category
	 */
	public static CategoryBuilder saveOldValues(Category category, CategoryBuilder categoryBuilder) {
		return categoryBuilder.setCategoryName(category.getCategoryName()).setCategoryId(category.getCategoryID());
	}

}
