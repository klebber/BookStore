package rs.ac.bg.fon.ai.bookstore.service;

import java.util.List;

import rs.ac.bg.fon.ai.bookstore.model.Review;

public interface ReviewService {

	void addReview(int id, String reviewedBookIsbn, String reviewAuthor, String text);
	void removeReview(int id);
	Review getReview(int id);
	List<Review> getReviewsForBook(String isbn);
	List<Review> getAllReviews();
	
}
