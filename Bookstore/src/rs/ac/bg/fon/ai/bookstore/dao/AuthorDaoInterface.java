package rs.ac.bg.fon.ai.bookstore.dao;

import java.util.List;

import rs.ac.bg.fon.ai.bookstore.model.Author;

public interface AuthorDaoInterface {

	public boolean addAuthor(Author author);
	public boolean removeAuthor(Author author);
	public List<Author> getAllAuthors();
	public String[] getAuthorsArray();
	
}
