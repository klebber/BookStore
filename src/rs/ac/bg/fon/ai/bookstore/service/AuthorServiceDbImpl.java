package rs.ac.bg.fon.ai.bookstore.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.bg.fon.ai.bookstore.model.Author;
import rs.ac.bg.fon.ai.bookstore.persistence.DatabasePersistence;

public class AuthorServiceDbImpl implements AuthorService {

	@Override
	public void addAuthor(int id, String name) throws RuntimeException {
		try {
			if(getAuthor(id) != null) {
				throw new RuntimeException("That id already exists.");
			}
			String sql = "INSERT INTO authors " +
					"VALUES (" + id + ", '" + name + "')";
			DatabasePersistence.getInstance().executeInsertOrUpdate(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeAuthor(int id) {
		try {
			String sql = "DELETE FROM authors " +
					"WHERE id = " + id;
			DatabasePersistence.getInstance().executeInsertOrUpdate(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateAuthor(int currentId, Author updatedAuthor) {
		try {
			String sql = "UPDATE authors " +
						 "SET id = " + updatedAuthor.getId() + ", name = '" + updatedAuthor.getName() + "' " +
						 "WHERE id = " + currentId;
			DatabasePersistence.getInstance().executeInsertOrUpdate(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Author> getAllAuthors() {
		List<Author> authors = new ArrayList<Author>();
		
		try (Connection conn = DatabasePersistence.getInstance().openConnection1()) {
			conn.setAutoCommit(false);
	
			String sql = "SELECT * FROM authors";
			
			try {
				ResultSet rs = conn.createStatement().executeQuery(sql);
				
				while(rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					
					authors.add(new Author(id, name));
				}
			} catch (SQLException ex) {
				conn.rollback();
				throw ex;
			}
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return authors;
	}

	@Override
	public Author getAuthor(int id) {
		Author author = null;
		try {
			DatabasePersistence.getInstance().openConnection();
	
			String sql = 
					"SELECT * FROM authors " +
					"WHERE id = " + id;
			
			ResultSet rs = DatabasePersistence.getInstance().executeSelectQuery(sql);
			
			if(rs.next()) {
				String name = rs.getString("name");
				author = new Author(id, name);
			}
			DatabasePersistence.getInstance().closeConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return author;
	}
}
