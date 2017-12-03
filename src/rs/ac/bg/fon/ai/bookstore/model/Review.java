package rs.ac.bg.fon.ai.bookstore.model;

public class Review {

	private int reviewId;
	private String reviewAuthor;
	private String text;

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public String getReviewAuthor() {
		return reviewAuthor;
	}

	public void setReviewAuthor(String reviewAuthor) {
		this.reviewAuthor = reviewAuthor;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
