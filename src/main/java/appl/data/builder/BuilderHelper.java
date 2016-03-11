package appl.data.builder;

import appl.data.items.Author;
import appl.data.items.Book;
import appl.data.items.Category;

public abstract class BuilderHelper {

	public static BookBuilder saveOldValues(Book book, BookBuilder bookBuilder){
		return bookBuilder
				.setUserBookStatistics(book.getUserBookStatistics())
				.setAuthors(book.getAuthors())
				.setCategories(book.getCategories())
				.setDescription(book.getDescription())
				.setEdition(book.getEdition())
				.setIsbn(book.getIsbn())
				.setPages(book.getPages())
				.setPrice(book.getPrice())
				.setPubdate(book.getPubdate())
				.setPublisher(book.getPublisher())
				.setStock(book.getStock())
				.setTitle(book.getTitle())
				.setVisitCount(book.getVisitCount());
	}
	
	public static AuthorBuilder saveOldValues(Author author, AuthorBuilder authorBuilder){
		return authorBuilder.setNameF(author.getNameF()).setNameL(author.getNameL()).setAuthorId(author.getAuthorId());
	}
	
	public static CategoryBuilder saveOldValues(Category category, CategoryBuilder categoryBuilder){
		return categoryBuilder.setCategoryName(category.getCategoryName()).setCategoryId(category.getCategoryID());
	}

}
