package rs.ac.bg.fon.ai.bookstore.service;

import java.util.GregorianCalendar;
import java.util.List;

import rs.ac.bg.fon.ai.bookstore.dao.BookDaoList;
import rs.ac.bg.fon.ai.bookstore.dao.BookDaoInterface;
import rs.ac.bg.fon.ai.bookstore.model.Author;
import rs.ac.bg.fon.ai.bookstore.model.Book;
import rs.ac.bg.fon.ai.bookstore.model.Genre;

public class BookService {
	
	private static BookDaoInterface bookDao = BookDaoList.getInstance();
	
	public static void addBook(Book book) throws RuntimeException {
		bookDao.addBook(book);
	}
	
	public static void addBook(String isbn, String title, Genre genre, String authorName, String publisher, 
			GregorianCalendar publishDate) throws RuntimeException {
		bookDao.addBook(new Book(isbn, title, genre, new Author(authorName), publisher, publishDate));
	}
	
	public static void removeBook(String isbn) throws RuntimeException {
		bookDao.removeBook(isbn);
	}
	
	/**
	 * Removes all the books written by the given author.
	 * @param name author whose books need to be removed
	 */
	public static void removeBooksByAuthor(String name) {
		bookDao.removeBooks(name);
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
		return bookDao.getBooks(c);
	}
	
	/**
	 * Returns the list with all the books in given genre.
	 * @param  genre desired book genre
	 * @return List of books that fulfill given criteria
	 */
	public static List<Book> getBooks(Genre genre) {
		return bookDao.getBooks(genre);
	}
	
	/**
	 * Returns the list with all the books written by the given author.
	 * @param  author desired author
	 * @return List of books that fulfill given criteria
	 */
	public static List<Book> getBooks(Author author) {
		return bookDao.getBooks(author);
	}
}
