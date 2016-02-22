package appl.data.builder;

import appl.data.items.Author;

public interface AuthorBuilder {
	public AuthorBuilder setNameF(String nameF);
	public AuthorBuilder setNameL(String nameL);
	public Author createAuthor();
	

}
