package appl.databasemanagement.databaseImpl;

import java.util.List;
import java.util.Map;

import appl.databasemanagement.BookDataQuery;
import appl.databasemanagement.SearchOptions;
import appl.databasemanagement.Searchfields;

public class BookDataQueryImpl implements BookDataQuery {

	@Override
	public Map searchBooks(Map<Enum<Searchfields>, String> searchterms, Enum<SearchOptions> searchOptions) {
		// Muss man das hier nicht aufsplitten, wie dynamisch kann man das machen, wenn es um die Auswahl geht, aus welcher Table man überhaupt selected?
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public List<String> getCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map searchAll(Map<Enum<Searchfields>, String> searchterms, Enum<SearchOptions> searchOptions) {
		// TODO Auto-generated method stub
		return null;
	}

}
