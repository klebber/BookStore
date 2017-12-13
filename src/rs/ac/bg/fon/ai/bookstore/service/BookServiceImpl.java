package rs.ac.bg.fon.ai.bookstore.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import rs.ac.bg.fon.ai.bookstore.filters.Criteria;
import rs.ac.bg.fon.ai.bookstore.filters.CriteriaAuthor;
import rs.ac.bg.fon.ai.bookstore.filters.CriteriaGenre;
import rs.ac.bg.fon.ai.bookstore.filters.CriteriaStartsWith;
import rs.ac.bg.fon.ai.bookstore.model.Author;
import rs.ac.bg.fon.ai.bookstore.model.Book;
import rs.ac.bg.fon.ai.bookstore.model.Genre;
import rs.ac.bg.fon.ai.bookstore.persistence.ListPersistence;

public class BookServiceImpl implements BookService {
	
	private AuthorService authorService = new AuthorServiceImpl();

	@Override
	public void addBook(String isbn, String title, Genre genre, int authorId, String publisher, Date date) throws RuntimeException {
		for (Book book : ListPersistence.getInstance().books) {
			if(book.getIsbn().equals(isbn))
				throw new RuntimeException("Book with same isbn already exists.");
		}
		
		Author author = authorService.getAuthor(authorId);
		
		if (author == null) {
			throw new RuntimeException("Could not load author with id: " + authorId);
		}
		
		Book newBook = new Book(isbn, title, genre, author, publisher, date);
		ListPersistence.getInstance().books.add(newBook);
	}
	
	@Override
	public void removeBook(String isbn) throws RuntimeException {
		Iterator<Book> iterator = ListPersistence.getInstance().books.iterator();
		
		while (iterator.hasNext()) {
			Book book = (Book) iterator.next();
			if(book.getIsbn().equals(isbn)) {
				iterator.remove();
				return;
			}
		}
		throw new RuntimeException("Book with this isbn has not been found.");
	}
	
	@Override
	public void removeBooksByAuthor(int authorId) {
		Iterator<Book> iterator = ListPersistence.getInstance().books.iterator();
		
		while (iterator.hasNext()) {
			Book book = (Book) iterator.next();
			if(book.getAuthor().getId() == authorId) {
				iterator.remove();
			}
		}
	}
	
	@Override
	public void updateBook(String currentIsbn, Book updatedBook) throws RuntimeException {
		Iterator<Book> iterator = ListPersistence.getInstance().books.iterator();

		while (iterator.hasNext()) {
			Book book = (Book) iterator.next();
			if (book.getIsbn() == currentIsbn) {
				book = updatedBook;
				return;
			}
		}
		throw new RuntimeException("Book with this isbn has not been found.");
	}
	
	@Override
	public Book getBook(String isbn) {
		for (Book book : ListPersistence.getInstance().books) {
			if(book.getIsbn().equals(isbn)) {
				return book;
			}
		}
		throw new RuntimeException("Book with same isbn already exists.");
	}
	
	@Override
	public List<Book> getAllBooks() {
		return ListPersistence.getInstance().books;
	}
	
	@Override
	public List<Book> getBooks(char c) {
		Criteria startsWith = new CriteriaStartsWith(c);
		return startsWith.meetCriteria(ListPersistence.getInstance().books);
	}
	
	@Override
	public List<Book> getBooks(Genre genre) {
		Criteria criteriaGenre = new CriteriaGenre(genre);
		return criteriaGenre.meetCriteria(ListPersistence.getInstance().books);
	}
	
	@Override
	public List<Book> getBooks(int authorId) {
		Author author = authorService.getAuthor(authorId);
		Criteria criteriaAuthor = new CriteriaAuthor(author);
		return criteriaAuthor.meetCriteria(ListPersistence.getInstance().books);
	}

}
