package rs.ac.bg.fon.ai.bookstore.service;

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
			DatabasePersistence.getInstance().executeUpdate(sql);
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
			DatabasePersistence.getInstance().executeUpdate(sql);
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
			DatabasePersistence.getInstance().executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Author> getAllAuthors() {
		List<Author> authors = new ArrayList<Author>();
		try {
			String sql = "SELECT * FROM authors";
			authors = DatabasePersistence.getInstance().executeQueryForAuthors(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return authors;
	}

	@Override
	public Author getAuthor(int id) {
		Author author = null;
		try {
			String sql = "SELECT * FROM authors " +
						 "WHERE id = " + id;
			List<Author> authors = DatabasePersistence.getInstance().executeQueryForAuthors(sql);
			if(authors.isEmpty())
				return null;
			author = authors.get(0);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return author;
	}
}
