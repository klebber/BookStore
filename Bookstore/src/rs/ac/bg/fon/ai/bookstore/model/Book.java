package rs.ac.bg.fon.ai.bookstore.model;

import java.util.GregorianCalendar;

public class Book {

	private String isbn;
	private String title;
	private Genre genre;
	private Author author;
	private String publisher;
	private GregorianCalendar publishDate;

	public Book() {
	}
	
	public Book(String isbn, String title, Genre genre, Author author, String publisher, GregorianCalendar publishDate) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.genre = genre;
		this.author = author;
		this.publisher = publisher;
		this.publishDate = publishDate;
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

	public void setAuthors(Author author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public GregorianCalendar getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(GregorianCalendar publishDate) {
		this.publishDate = publishDate;
	}

	@Override
	public String toString() {
		return title;
	}

}
