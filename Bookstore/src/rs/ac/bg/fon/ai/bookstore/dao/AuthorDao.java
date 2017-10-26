package rs.ac.bg.fon.ai.bookstore.dao;

import java.util.ArrayList;
import java.util.List;

import rs.ac.bg.fon.ai.bookstore.model.Author;

public class AuthorDao {

	private List<Author> authors = new ArrayList<Author>();
	
	public List<Author> getAllAuthors() {
		return authors;
	}
	
	public Author getAuthorAt(int index) {
		return authors.get(index);
	}
	
	public int getAuthorCount() {
		return authors.size();
	}
	
	public void addAuthor(Author author) {
		authors.add(author);
	}
	
	public void removeAuthor(Author author) {
		authors.remove(author);
	}
	
}
