package rs.ac.bg.fon.ai.bookstore.persistence;

import java.util.ArrayList;
import java.util.List;

import rs.ac.bg.fon.ai.bookstore.model.Author;
import rs.ac.bg.fon.ai.bookstore.model.Book;
import rs.ac.bg.fon.ai.bookstore.model.Review;

public class ListPersistence {

	private static ListPersistence INSTANCE;
	
	public List<Book> books = new ArrayList<Book>();
	public List<Author> authors = new ArrayList<Author>();
	public List<Review> reviews = new ArrayList<Review>();
	
	private ListPersistence() {
	}
	
	public static ListPersistence getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ListPersistence();
		}
		return INSTANCE;
	}
}
