package rs.ac.bg.fon.ai.bookstore.service;

import java.util.List;

import rs.ac.bg.fon.ai.bookstore.dao.AuthorDaoList;
import rs.ac.bg.fon.ai.bookstore.dao.AuthorDaoInterface;
import rs.ac.bg.fon.ai.bookstore.model.Author;

public class AuthorService {
	
	private static AuthorDaoInterface authorDao = AuthorDaoList.getInstance();
	
	public static void addAuthor(String name) throws RuntimeException {
		Author author = new Author(name);
		authorDao.addAuthor(author);
	}
	
	public static void removeAuthor(String name) throws RuntimeException {
		Author author = new Author(name);
		authorDao.removeAuthor(author);
	}

	public static List<Author> getAuthors() {
		return authorDao.getAllAuthors();
	}
	
	public static String[] getAuthorsArray() {
		return authorDao.getAuthorsArray();
	}
	
}
