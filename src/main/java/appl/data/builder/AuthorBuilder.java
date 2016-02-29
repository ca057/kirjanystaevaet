package appl.data.builder;

import appl.data.items.Author;

/**
 * Builder to create a new object of the {@link Author} class.
 * 
 * @author Madeleine
 *
 */
public interface AuthorBuilder {

	public AuthorBuilder setNameF(String nameF);

	public AuthorBuilder setNameL(String nameL);

	public Author createAuthor();

}
