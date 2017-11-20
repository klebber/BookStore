package rs.ac.bg.fon.ai.bookstore.service;

import java.util.List;

import rs.ac.bg.fon.ai.bookstore.model.Author;

public interface AuthorService {

	void addAuthor(String name) throws RuntimeException;
	void removeAuthor(String name) throws RuntimeException;
	List<Author> getAllAuthors();
	Author getAuthor(String authorName);
	
}
