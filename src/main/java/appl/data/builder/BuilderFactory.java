package appl.data.builder;

import org.springframework.stereotype.Component;

import appl.data.builder.impl.AuthorBuilderImpl;
import appl.data.builder.impl.BookBuilderImpl;
import appl.data.builder.impl.CategoryBuilderImpl;
import appl.data.builder.impl.UserBuilderImpl;

/**
 * @author Johannes
 *
 */
@Component
public class BuilderFactory {

	/**
	 * @return
	 */
	public AuthorBuilder getAuthorBuilder() {
		return new AuthorBuilderImpl();
	}

	/**
	 * @return
	 */
	public BookBuilder getBookBuilder() {
		return new BookBuilderImpl();
	}

	/**
	 * @return
	 */
	public CategoryBuilder getCategoryBuilder() {
		return new CategoryBuilderImpl();
	}

	/**
	 * @return
	 */
	public UserBuilder getUserBuilder() {
		return new UserBuilderImpl();
	}

}
