package databaseImpl;

import java.util.List;

import database.BookDataAdmin;

public class BookDataAdminImpl implements BookDataAdmin {

	@Override
	public boolean addBookDesc(String isbn, String title, String desc, String price, String pub, String pubDate,
			String edition, String page, List<Integer> authorID, List<Integer> catId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addBookCategoriesBooks(int CatId, String isnb) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addBookAuthorsBooks(int AuthorId, String isbn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addBookCategories(int catId, String catName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addBookAuthors(int authorId, String firstName, String lastName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeBookDesc(String isbn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeBookCategoriesBooks(int CatId, String isbn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeBookAuthorsBooks(String isbn, int authorId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeBookCategories(int catId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeBookAuthors(int authorId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int updateStock(int dif) {
		// TODO Auto-generated method stub
		return 0;
	}

}
