package appl.queryManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class queryCreatorImpl implements queryCreator{
/*
	@Override
	public Map getBookByID(String id) {
		// TODO Auto-generated method stub
		return null;
	}
*/
	@Override
	public List<String> getCategories() {
		// TODO Auto-generated method stub
		List<String> categoryList = new ArrayList<String>();

		categoryList.add("Bla");
		categoryList.add("blub");
		categoryList.add("bli");
		categoryList.add("Fisch");
		
		
		return categoryList;
	}
	
	

}
