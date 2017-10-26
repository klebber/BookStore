package rs.ac.bg.fon.ai.bookstore.dao;

import java.util.ArrayList;
import java.util.List;

import rs.ac.bg.fon.ai.bookstore.model.Book;

public class BookDao {

	private List<Book> books = new ArrayList<Book>();
	
	public List<Book> getAllBooks() {
		return books;
	}
	
	public Book getBookAt(int index) {
		return books.get(index);
	}
	
	public int getBookCount() {
		return books.size();
	}
	
	public void addBook(Book book) {
		books.add(book);
	}
	
	public void removeBook(Book book) {
		books.remove(book);
	}
	
}
