package appl.data.builder;

import org.springframework.stereotype.Component;

import appl.data.builder.impl.AuthorBuilderImpl;
import appl.data.builder.impl.BookBuilderImpl;
import appl.data.builder.impl.CategoryBuilderImpl;
import appl.data.builder.impl.UserBuilderImpl;

/**
 * Class to get new instances of different builder.
 * 
 * @author Johannes
 *
 */
@Component
public class BuilderFactory {

	public AuthorBuilder getAuthorBuilder() {
		return new AuthorBuilderImpl();
	}

	public BookBuilder getBookBuilder() {
		return new BookBuilderImpl();
	}

	public CategoryBuilder getCategoryBuilder() {
		return new CategoryBuilderImpl();
	}

	public UserBuilder getUserBuilder() {
		return new UserBuilderImpl();
	}

}
