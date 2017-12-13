package rs.ac.bg.fon.ai.bookstore.gui;

import java.awt.EventQueue;
import java.util.Date;
import java.util.List;

import rs.ac.bg.fon.ai.bookstore.model.Author;
import rs.ac.bg.fon.ai.bookstore.model.Book;
import rs.ac.bg.fon.ai.bookstore.model.Genre;
import rs.ac.bg.fon.ai.bookstore.service.*;


public class GUIController {

	private BookService bookService = new BookServiceDbImpl();
	private AuthorService authorService = new AuthorServiceDbImpl();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow mainWindow = new MainWindow();
					mainWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void addBook(String ISBN, String title, Genre genre, int authorId, String publisher, 
			Date date) throws RuntimeException {
		bookService.addBook(ISBN, title, genre, authorId, publisher, date);
	}
	
	public void removeBook(String isbn) throws RuntimeException {
		bookService.removeBook(isbn);
	}
	
	public List<Book> getFilteredList(char c) {
		return bookService.getBooks(c);
	}
	
	public List<Book> getFilteredList(Genre genre) {
		return bookService.getBooks(genre);
	}
	
	public List<Book> getFilteredList(int authorId) {
		return bookService.getBooks(authorId);
	}
	
	public void addAuthor(int id, String name) throws RuntimeException {
		authorService.addAuthor(id, name);
	}
	
	public void removeAuthor(int authorId) throws RuntimeException {
		bookService.removeBooksByAuthor(authorId);
		authorService.removeAuthor(authorId);
	}
	
	public Author[] getAuthorsArray() {
		List<Author> authors = authorService.getAllAuthors();
		
		Author[] authorsArray = new Author[authors.size()];
		for (int i = 0; i < authors.size(); i++)
			authorsArray[i] = authors.get(i);
		return authorsArray;
	}
	
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}
}
