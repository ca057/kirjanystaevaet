package appl.queryManagement;

import java.util.ArrayList;
import java.util.List;

public class QueryCreatorImpl implements QueryCreator {
	/*
	 * @Override public Map getBookByID(String id) { // TODO Auto-generated
	 * method stub return null; }
	 */
	public QueryCreatorImpl(){
		
	}
	
	@Override
	public List<String> getCategories() {
		// TODO vernünftige Abfrage an die Datenbank
		List<String> categoryList = new ArrayList<String>();

		categoryList.add("Java");
		categoryList.add("PHP");
		categoryList.add("MySQL");
		categoryList.add("mysql");

		return categoryList;
	}

}
