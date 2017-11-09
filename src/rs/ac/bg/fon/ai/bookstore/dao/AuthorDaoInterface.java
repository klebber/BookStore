package rs.ac.bg.fon.ai.bookstore.dao;

import java.util.List;

import rs.ac.bg.fon.ai.bookstore.model.Author;

public interface AuthorDaoInterface {

	public void addAuthor(Author author) throws RuntimeException;
	public void removeAuthor(Author author) throws RuntimeException;
	public List<Author> getAllAuthors();
	public String[] getAuthorsArray();
	
}
