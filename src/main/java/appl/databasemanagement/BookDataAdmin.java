package appl.databasemanagement;

import java.util.List;

public interface BookDataAdmin {
	// True wenn möglich, false, wenn das nicht geklappt hat
	public boolean addBookDesc(String isbn, String title, String desc, String price, String pub, String pubDate,
			String edition, String page, List<Integer> authorID, List<Integer> catId);

	public boolean addBookCategoriesBooks(int CatId, String isnb);

	public boolean addBookAuthorsBooks(int AuthorId, String isbn);

	public boolean addBookCategories(int catId, String catName);

	public boolean addBookAuthors(int authorId, String firstName, String lastName);

	public boolean removeBookDesc(String isbn);

	public boolean removeBookCategoriesBooks(int CatId, String isbn);

	public boolean removeBookAuthorsBooks(String isbn, int authorId);

	public boolean removeBookCategories(int catId);

	public boolean removeBookAuthors(int authorId);

	// Return neuen Bestand,
	/**
	 * 
	 * @param dif
	 *            Negative Integer if stock decreases, positive if increases
	 * @return new size of stock
	 */
	public int updateStock(int dif);

}
