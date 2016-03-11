package appl.data.builder.impl;

import appl.data.builder.AuthorBuilder;
import appl.data.items.Author;

public class AuthorBuilderImpl implements AuthorBuilder {

	private int authorId = -1;
	private String nameF;
	private String nameL;
	
	public AuthorBuilderImpl() {
	}
	
	@Override
	public AuthorBuilder setAuthorId(int authorId){
		this.authorId = authorId;
		return this;
	}

	@Override
	public AuthorBuilder setNameF(String nameF) {
		this.nameF = nameF;
		return this;
	}

	@Override
	public AuthorBuilder setNameL(String nameL) {
		this.nameL = nameL;
		return this;
	}

	@Override
	public Author createAuthor() {
		if (authorId < 0){
			return new Author(this.nameF, this.nameL);

		} else {
			return new Author(this.authorId, this.nameF, this.nameL);

		}
	}

}
