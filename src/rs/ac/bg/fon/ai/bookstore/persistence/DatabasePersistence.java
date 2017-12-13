package rs.ac.bg.fon.ai.bookstore.persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import rs.ac.bg.fon.ai.bookstore.model.Author;
import rs.ac.bg.fon.ai.bookstore.model.Book;
import rs.ac.bg.fon.ai.bookstore.model.Genre;
import rs.ac.bg.fon.ai.bookstore.model.Review;

public class DatabasePersistence {

	private static DatabasePersistence INSTANCE;
	
	static final String USER = "root";
	static final String PASS = "test";
	static final String DB_URL = "jdbc:mysql://localhost:3306/bookstore?autoReconnect=true&useSSL=false";
	
	private Connection conn = null;
	private Statement stmt = null;
	
	private DatabasePersistence() {
	}
	
	public void openConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
		stmt = conn.createStatement();
		
	}
	
	public void closeConnection() throws SQLException {
		stmt.close();
		conn.close();
	}
	
	public void executeUpdate(String sql) throws ClassNotFoundException, SQLException {
		openConnection();
		stmt.executeUpdate(sql);
		closeConnection();
	}
	
	public List<Author> executeQueryForAuthors(String sql) throws ClassNotFoundException, SQLException {
		openConnection();
		ResultSet rs = stmt.executeQuery(sql);
		List<Author> authors = new ArrayList<Author>();
		while(rs.next()) {
			authors.add(new Author(rs.getInt("id"), rs.getString("name")));
		}
		closeConnection();
		return authors;
	}
	
	public List<Book> executeQueryForBooks(String sql) throws ClassNotFoundException, SQLException {
		openConnection();
		ResultSet rs = stmt.executeQuery(sql);
		List<Book> books = new ArrayList<Book>();
		while(rs.next()) {
			books.add(new Book(rs.getString("isbn"), rs.getString("title"), Genre.valueOf(rs.getString("Genre")), 
					new Author(rs.getInt("authorId"), rs.getString("name")), rs.getString("publisher"), rs.getDate("publishDate")));
		}
		closeConnection();
		return books;
	}
	
	public List<Review> executeQueryForReviews(String sql) throws ClassNotFoundException, SQLException {
		openConnection();
		ResultSet rs = stmt.executeQuery(sql);
		List<Review> reviews = new ArrayList<Review>();
		while(rs.next()) {
			reviews.add(new Review(rs.getInt("id"), rs.getString("isbn"), rs.getString("reviewAuthor"), rs.getString("text")));
		}
		closeConnection();
		return reviews;
	}
	
	public static DatabasePersistence getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new DatabasePersistence();
		}
		return INSTANCE;
	}

}
