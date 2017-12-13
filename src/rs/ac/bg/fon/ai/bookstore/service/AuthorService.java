package rs.ac.bg.fon.ai.bookstore.service;

import java.util.List;

import rs.ac.bg.fon.ai.bookstore.model.Author;

public interface AuthorService {

	void addAuthor(int id, String name);
	void removeAuthor(int id);
	void updateAuthor(int currentId, Author updatedAuthor);
	List<Author> getAllAuthors();
	Author getAuthor(int id);
	
}
