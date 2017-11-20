package rs.ac.bg.fon.ai.bookstore.persistance;

import java.util.ArrayList;
import java.util.List;

import rs.ac.bg.fon.ai.bookstore.model.Author;
import rs.ac.bg.fon.ai.bookstore.model.Book;

public class ListPersistance {

	private static ListPersistance INSTANCE;
	
	public List<Book> books = new ArrayList<Book>();
	public List<Author> authors = new ArrayList<Author>();
	
	private ListPersistance() {
	}
	
	public static ListPersistance getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ListPersistance();
		}
		return INSTANCE;
	}
}
