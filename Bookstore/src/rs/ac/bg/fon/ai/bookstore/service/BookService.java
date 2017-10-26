package rs.ac.bg.fon.ai.bookstore.service;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import rs.ac.bg.fon.ai.bookstore.dao.BookDao;
import rs.ac.bg.fon.ai.bookstore.model.Author;
import rs.ac.bg.fon.ai.bookstore.model.Book;
import rs.ac.bg.fon.ai.bookstore.model.Genre;

public class BookService {
	
	private static BookDao bookDao = new BookDao();
	
	public static boolean addBook(Book newBook) {
		List<Book> books = bookDao.getAllBooks();
		for (int i = 0; i < books.size(); i++) {
			Book temp = books.get(i);
			if(temp.getIsbn() == newBook.getIsbn() || temp.getTitle().equals(newBook.getTitle()))
				return false;
		}
		bookDao.addBook(newBook);
		return true;
	}
	
	public static boolean addBook(String isbn, String title, Genre genre, String authorName, String publisher, GregorianCalendar publishDate) {
		List<Book> books = bookDao.getAllBooks();
		for (int i = 0; i < books.size(); i++) {
			Book temp = books.get(i);
			if(temp.getIsbn() == isbn || temp.getTitle().equals(title))
				return false;
		}
		bookDao.addBook(new Book(isbn, title, genre, new Author(authorName), publisher, publishDate));
		return true;
	}
	
	public static boolean removeBook(String isbn) {
		List<Book> books = bookDao.getAllBooks();
		for (int i = 0; i < books.size(); i++) {
			Book temp = books.get(i);
			if(temp.getIsbn() == isbn) {
				bookDao.removeBook(temp);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Removes all the books written by the given author.
	 * @param name author whose books need to be removed
	 */
	public static void removeBooksByAuthor(String name) {
		List<Book> books = bookDao.getAllBooks();
		for (int i = 0; i < books.size(); i++) {
			Book temp = books.get(i);
			if(temp.getAuthor().getName().equals(name)) {
				bookDao.removeBook(temp);
			}
		}
	}
	
	/**
	 * Returns a list, containing all the books.
	 * @return List of all the books
	 */
	public static List<Book> getBooks() {
		return bookDao.getAllBooks();
	}

	/**
	 * Returns the list with all books which title starts with given character, if that character is a letter,
	 * list will contain books starting with both lower and upper case letter.
	 * @param c character that represents desired first character in a book title
	 * @return  List of books that fulfill given criteria
	 */
	public static List<Book> getBooks(char c) {
		List<Book> books = bookDao.getAllBooks();
		List<Book> filteredBooks = new ArrayList<Book>();
		for (int i = 0; i < books.size(); i++) {
			Book temp = books.get(i);
			char tempChar = temp.getTitle().charAt(0);
			if(tempChar == c || (Character.isLetter(tempChar) && Character.toUpperCase(tempChar) == c)) {
				filteredBooks.add(temp);
			}
		}
		return filteredBooks;
	}
	
	/**
	 * Returns the list with all the books in given genre.
	 * @param  genre desired book genre
	 * @return List of books that fulfill given criteria
	 */
	public static List<Book> getBooks(Genre genre) {
		List<Book> books = bookDao.getAllBooks();
		List<Book> filteredBooks = new ArrayList<Book>();
		for (int i = 0; i < books.size(); i++) {
			Book temp = books.get(i);
			if(temp.getGenre().equals(genre)) {
				filteredBooks.add(temp);
			}
		}
		return filteredBooks;
	}
	
	/**
	 * Returns the list with all the books written by the given author.
	 * @param  author desired author
	 * @return List of books that fulfill given criteria
	 */
	public static List<Book> getBooks(Author author) {
		List<Book> books = bookDao.getAllBooks();
		List<Book> filteredBooks = new ArrayList<Book>();
		for (int i = 0; i < books.size(); i++) {
			Book temp = books.get(i);
			if(temp.getAuthor().equals(author)) {
				filteredBooks.add(temp);
			}
		}
		return filteredBooks;
	}
}
