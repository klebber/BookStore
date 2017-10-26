package rs.ac.bg.fon.ai.bookstore.service;

import java.util.List;

import rs.ac.bg.fon.ai.bookstore.dao.AuthorDao;
import rs.ac.bg.fon.ai.bookstore.model.Author;

public class AuthorService {
	
	private static AuthorDao authorDao = new AuthorDao();
	
	public static boolean addAuthor(String name) {
		List<Author> authors = authorDao.getAllAuthors();
		Author author = new Author(name);
		for (int i = 0; i < authors.size(); i++) {
			if(authors.get(i).equals(author))
				return false;
		}
		authorDao.addAuthor(author);
		return true;
	}
	
	public static boolean removeAuthor(String name) {
		List<Author> authors = authorDao.getAllAuthors();
		for (int i = 0; i < authors.size(); i++) {
			if(authors.get(i).getName().equals(name)) {
				authorDao.removeAuthor(authors.get(i));
				return true;
			}
		}
		return false;
	}

	public static List<Author> getAuthors() {
		return authorDao.getAllAuthors();
	}
	
	public static String[] getAuthorsArray() {
		List<Author> authors = authorDao.getAllAuthors();
		String[] a = new String[authors.size()];
		for (int i = 0; i < authors.size(); i++) {
			a[i] = authors.get(i).getName();
		}
		return a;
	}
	
}
