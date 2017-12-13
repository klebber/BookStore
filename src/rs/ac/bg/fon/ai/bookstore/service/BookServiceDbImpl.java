package rs.ac.bg.fon.ai.bookstore.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rs.ac.bg.fon.ai.bookstore.model.Book;
import rs.ac.bg.fon.ai.bookstore.model.Genre;
import rs.ac.bg.fon.ai.bookstore.persistence.DatabasePersistence;

public class BookServiceDbImpl implements BookService {

	@Override
	public void addBook(String isbn, String title, Genre genre, int authorId, String publisher, Date date) throws RuntimeException {
		try {
			if(getBook(isbn) != null) {
				throw new RuntimeException("That isbn already exists.");
			}
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
			String sql = "INSERT INTO books " +
					 	 "VALUES ('" + isbn + "', '" + title + "', '" + genre + "', " + authorId + 
					 	 ", '" + publisher + "', '" + dateFormatter.format(date.getTime()) + "')";
			DatabasePersistence.getInstance().executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeBook(String isbn) {
		try {
			String sql = "DELETE FROM books " +
						 "WHERE isbn = '" + isbn + "'";
			DatabasePersistence.getInstance().executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeBooksByAuthor(int authorId) {
		try {
			String sql = "DELETE FROM books " +
						 "WHERE authorId = '" + authorId + "'";
			DatabasePersistence.getInstance().executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateBook(String currentIsbn, Book updatedBook) {
		try {
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
			String sql = "UPDATE books " +
						 "SET isbn = '" + updatedBook.getIsbn() + "', title = '" + updatedBook.getTitle() + 
						 "', genre = '" + updatedBook.getGenre().toString() + "', authorId = " + updatedBook.getAuthor().getId() + 
						 ", publisher = '" + updatedBook.getPublisher() + "', date = '" + dateFormatter.format(updatedBook.getPublishDate().getTime()) + "' " +
						 "WHERE isbn = '" + currentIsbn + "'";
			DatabasePersistence.getInstance().executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Book getBook(String isbn) {
		Book book = null;
		try {
			String sql = "SELECT * FROM books, authors " +
						 "WHERE isbn = '" + isbn + "' AND authorId = id";
			List<Book> books = DatabasePersistence.getInstance().executeQueryForBooks(sql);
			if(books.isEmpty())
				return null;
			book = books.get(0);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}
	
	@Override
	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<Book>();
		try {
			String sql = "SELECT * FROM books, authors " +
						 "WHERE authorId = id";
			books = DatabasePersistence.getInstance().executeQueryForBooks(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

	@Override
	public List<Book> getBooks(char c) {
		List<Book> books = new ArrayList<Book>();
		try {
			String sql = "SELECT * FROM books, authors " +
						 "WHERE title LIKE '" + c + "%' AND authorId = id";
			books = DatabasePersistence.getInstance().executeQueryForBooks(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

	@Override
	public List<Book> getBooks(Genre genre) {
		List<Book> books = new ArrayList<Book>();
		try {
			String sql = "SELECT * FROM books, authors " +
						 "WHERE genre = '" + genre.toString() + "' AND authorId = id";
			books = DatabasePersistence.getInstance().executeQueryForBooks(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

	@Override
	public List<Book> getBooks(int authorId) {
		List<Book> books = new ArrayList<Book>();
		try {
			String sql = "SELECT * FROM books, authors " +
						 "WHERE authorId = " + authorId + " AND authorId = id";
			books = DatabasePersistence.getInstance().executeQueryForBooks(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

}
