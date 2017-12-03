package rs.ac.bg.fon.ai.bookstore.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Book {

	private String isbn;
	private String title;
	private Genre genre;
	private Author author;
	private String publisher;
	private Date publishDate;
	private List<Review> reviews;

	public Book() {
	}
	
	public Book(String isbn, String title, Genre genre, Author author, String publisher, Date publishDate) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.genre = genre;
		this.author = author;
		this.publisher = publisher;
		this.publishDate = publishDate;
		this.reviews = new ArrayList<Review>();
	}
	
	public Book(String isbn, String title, Genre genre, Author author, String publisher, Date publishDate, List<Review> reviews) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.genre = genre;
		this.author = author;
		this.publisher = publisher;
		this.publishDate = publishDate;
		this.reviews = reviews;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return title;
	}

}
