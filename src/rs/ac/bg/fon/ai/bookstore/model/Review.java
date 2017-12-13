package rs.ac.bg.fon.ai.bookstore.model;

public class Review {

	private int id;
	private String reviewedBookIsbn;
	private String reviewAuthor;
	private String text;
	
	public Review() {
	}

	public Review(int id, String reviewedBookIsbn, String reviewAuthor, String text) {
		super();
		this.id = id;
		this.reviewedBookIsbn = reviewedBookIsbn;
		this.reviewAuthor = reviewAuthor;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReviewedBookIsbn() {
		return reviewedBookIsbn;
	}

	public void setReviewedBookIsbn(String reviewedBookIsbn) {
		this.reviewedBookIsbn = reviewedBookIsbn;
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
