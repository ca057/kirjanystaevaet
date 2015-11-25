package appl.databasemanagement;

import java.util.List;
import java.util.Map;

public interface BookDataQuery {

	public Map searchBooks(Map<Enum<Searchfields>, String> searchterms, Enum<SearchOptions> searchOptions);

	public List<String> getCategories();

	public Map searchAll(Map<Enum<Searchfields>, String> searchterms, Enum<SearchOptions> searchOptions);
}
