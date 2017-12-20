package rs.ac.bg.fon.ai.bookstore.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.bg.fon.ai.bookstore.model.Review;
import rs.ac.bg.fon.ai.bookstore.persistence.DatabasePersistence;

public class ReviewServiceDbImpl implements ReviewService {

	@Override
	public void addReview(int id, String reviewedBookIsbn, String reviewAuthor, String text) {
		try {
			if(getReview(id) != null) {
				throw new RuntimeException("There is already a review with the same id.");
			}
			String sql = "INSERT INTO reviews " +
					 	 "VALUES (" + id + ", '" + reviewedBookIsbn + "', '" + reviewAuthor + "', " + text + "')";
			DatabasePersistence.getInstance().executeInsertOrUpdate(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeReview(int id) {
		try {
			String sql = "DELETE FROM reviews " +
						 "WHERE id = " + id;
			DatabasePersistence.getInstance().executeInsertOrUpdate(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Review getReview(int id) {
		Review review = null;
		try {
			String sql = "SELECT * FROM reviews " +
						 "WHERE id = " + id;
			List<Review> reviews = DatabasePersistence.getInstance().executeQueryForReviews(sql);
			if(reviews.isEmpty())
				return null;
			review = reviews.get(0);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return review;
	}

	@Override
	public List<Review> getReviewsForBook(String isbn) {
		List<Review> reviews = new ArrayList<Review>();
		try {
			String sql = "SELECT * FROM reviews "
					+ "Where isbn = '" + isbn + "'";
			reviews = DatabasePersistence.getInstance().executeQueryForReviews(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reviews;
	}

	@Override
	public List<Review> getAllReviews() {
		List<Review> reviews = new ArrayList<Review>();
		try {
			String sql = "SELECT * FROM reviews";
			reviews = DatabasePersistence.getInstance().executeQueryForReviews(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reviews;
	}

}
