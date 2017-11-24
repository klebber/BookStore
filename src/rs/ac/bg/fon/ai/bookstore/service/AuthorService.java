package rs.ac.bg.fon.ai.bookstore.service;

import java.util.List;

import rs.ac.bg.fon.ai.bookstore.model.Author;

public interface AuthorService {

	void addAuthor(int id, String name) throws RuntimeException;
	void removeAuthor(int id) throws RuntimeException;
	List<Author> getAllAuthors();
	Author getAuthor(int id);
	
}
