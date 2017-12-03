package rs.ac.bg.fon.ai.bookstore.service;

import java.util.Date;
import java.util.List;

import rs.ac.bg.fon.ai.bookstore.model.Book;
import rs.ac.bg.fon.ai.bookstore.model.Genre;

public interface BookService {
	
	void addBook(String isbn, String title, Genre genre, int authorId, String publisher, Date date) throws RuntimeException;
	void removeBook(String isbn) throws RuntimeException;
	void removeBooksByAuthor(int authorId);
	void updateBook(String currentIsbn, Book updatedBook) throws RuntimeException;
	List<Book> getAllBooks();
	List<Book> getBooks(char c);
	List<Book> getBooks(Genre genre);
	List<Book> getBooks(int authorId);

}
