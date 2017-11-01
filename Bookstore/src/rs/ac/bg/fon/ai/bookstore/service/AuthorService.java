package rs.ac.bg.fon.ai.bookstore.service;

import java.util.List;

import rs.ac.bg.fon.ai.bookstore.dao.AuthorDaoList;
import rs.ac.bg.fon.ai.bookstore.dao.AuthorDaoInterface;
import rs.ac.bg.fon.ai.bookstore.model.Author;

public class AuthorService {
	
	private static AuthorDaoInterface authorDao = AuthorDaoList.getInstance();
	
	public static boolean addAuthor(String name) {
		Author author = new Author(name);
		return authorDao.addAuthor(author);
	}
	
	public static boolean removeAuthor(String name) {
		Author author = new Author(name);
		return authorDao.removeAuthor(author);
	}

	public static List<Author> getAuthors() {
		return authorDao.getAllAuthors();
	}
	
	public static String[] getAuthorsArray() {
		return authorDao.getAuthorsArray();
	}
	
}
