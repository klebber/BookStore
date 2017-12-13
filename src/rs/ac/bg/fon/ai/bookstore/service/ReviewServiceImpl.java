package rs.ac.bg.fon.ai.bookstore.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import rs.ac.bg.fon.ai.bookstore.model.Review;
import rs.ac.bg.fon.ai.bookstore.persistence.ListPersistence;

public class ReviewServiceImpl implements ReviewService {

	@Override
	public void addReview(int id, String reviewedBookIsbn, String reviewAuthor, String text) throws RuntimeException {
		for(Review r : ListPersistence.getInstance().reviews) {
			if(r.getId() == id) {
				throw new RuntimeException("Review with same id already exists.");
			}
		}
		ListPersistence.getInstance().reviews.add(new Review(id,reviewedBookIsbn, reviewAuthor, text));
		
	}

	@Override
	public void removeReview(int id) throws RuntimeException {
		Iterator<Review> iterator = ListPersistence.getInstance().reviews.iterator();
		
		while(iterator.hasNext()) {
			Review review = (Review) iterator.next();
			if(review.getId() == id) {
				iterator.remove();
				return;
			}
		}
		throw new RuntimeException("Review with this id has not been found.");
	}

	@Override
	public Review getReview(int id) throws RuntimeException {
		for(Review r : ListPersistence.getInstance().reviews) {
			if(r.getId() == id) {
				return r;
			}
		}
		throw new RuntimeException("Review with this id has not been found.");
	}

	@Override
	public List<Review> getReviewsForBook(String isbn) {
		List<Review> selectedReviews = new ArrayList<Review>();
		for(Review r : ListPersistence.getInstance().reviews) {
			if(r.getReviewedBookIsbn() == isbn) {
				selectedReviews.add(r);
			}
		}
		return selectedReviews;
	}

	@Override
	public List<Review> getAllReviews() {
		return ListPersistence.getInstance().reviews;
	}

}
