package database;

import java.util.Map;

public interface BookDataQuery {

	public Map searchBooks(Map<Enum<Searchfields>, String> searchterms, Enum<SearchOptions> searchOptions);

	public Map searchAuthors(Map<Enum<Searchfields>, String> searchterms, Enum<SearchOptions> searchOptions);

	public Map searchCategories(Map<Enum<Searchfields>, String> searchterms, Enum<SearchOptions> searchOptions);

	public Map searchAll(Map<Enum<Searchfields>, String> searchterms, Enum<SearchOptions> searchOptions);

}
