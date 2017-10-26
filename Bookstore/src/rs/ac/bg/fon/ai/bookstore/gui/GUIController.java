package rs.ac.bg.fon.ai.bookstore.gui;

import java.awt.EventQueue;
import java.util.GregorianCalendar;

import javax.swing.JFrame;

import rs.ac.bg.fon.ai.bookstore.model.Author;
import rs.ac.bg.fon.ai.bookstore.model.Genre;
import rs.ac.bg.fon.ai.bookstore.service.AuthorService;
import rs.ac.bg.fon.ai.bookstore.service.BookService;


public class GUIController {

	public static MainWindow mainWindow;
	public static AddBookDialog addBookDialog;
	public static AuthorWindow authorWindow;
	public static AddAuthorDialog addAuthorDialog;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWindow = new MainWindow(AuthorService.getAuthorsArray());
					mainWindow.setVisible(true);
					mainWindow.updateTable(BookService.getBooks());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void openAddBookDialog(JFrame frame, boolean modal) {
		addBookDialog = new AddBookDialog(frame, modal, AuthorService.getAuthorsArray());
		addBookDialog.setVisible(true);
		addBookDialog.setLocationRelativeTo(frame);
	}
	
	public static void openAuthorWindow(JFrame frame, boolean modal) {
		authorWindow = new AuthorWindow(frame, modal, AuthorService.getAuthorsArray());
		authorWindow.setVisible(true);
		authorWindow.setLocationRelativeTo(frame);
	}
	
	public static void addBook(String ISBN, String title, Genre genre, String author, String publisher, GregorianCalendar date) {
		BookService.addBook(ISBN, title, genre, author, publisher, date);
		mainWindow.applySelectedFilter();
		addBookDialog.dispose();
	}
	
	public static void removeBook(String isbn) {
		BookService.removeBook(isbn);
		mainWindow.applySelectedFilter();
	}
	
	public static void reloadTable() {
		mainWindow.updateTable(BookService.getBooks());
	}
	
	public static void filterTable(char c) {
		mainWindow.updateTable(BookService.getBooks(c));
	}
	
	public static void filterTable(Genre genre) {
		mainWindow.updateTable(BookService.getBooks(genre));
	}
	
	public static void filterTable(Author author) {
		mainWindow.updateTable(BookService.getBooks(author));
	}
	
	public static void openAddAuthorDialog(boolean modal) {
		addAuthorDialog = new AddAuthorDialog(modal);
		addAuthorDialog.setVisible(true);
		addAuthorDialog.setLocationRelativeTo(addAuthorDialog);
	}
	
	public static void addAuthor(String name) {
		AuthorService.addAuthor(name);
		addAuthorDialog.dispose();
		reloadAuthorList();
		mainWindow.applySelectedFilter();
		mainWindow.updateAuthorsArray(AuthorService.getAuthorsArray());
	}
	
	public static void removeAuthor(String name) {
		AuthorService.removeAuthor(name);
		BookService.removeBooksByAuthor(name);
		reloadAuthorList();
		mainWindow.applySelectedFilter();
		mainWindow.updateAuthorsArray(AuthorService.getAuthorsArray());
	}
	
	public static void reloadAuthorList() {
		authorWindow.updateAuthorList(AuthorService.getAuthorsArray());
	}
}
