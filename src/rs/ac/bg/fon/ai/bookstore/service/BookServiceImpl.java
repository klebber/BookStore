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
import rs.ac.bg.fon.ai.bookstore.persistance.ListPersistance;

public class BookServiceImpl implements BookService {
	
	private AuthorService authorService = new AuthorServiceImpl();

	@Override
	public void addBook(String isbn, String title, Genre genre, String authorName, String publisher, Date date) throws RuntimeException {
		for (Book temp : ListPersistance.getInstance().books) {
			if(temp.getIsbn().equals(isbn))
				throw new RuntimeException("Book with same isbn already exists.");
		}
		
		Author author = authorService.getAuthor(authorName);
		
		if (author == null) {
			throw new RuntimeException("Could not load author with name " + authorName);
		}
		
		Book newBook = new Book(isbn, title, genre, author, publisher, date);
		ListPersistance.getInstance().books.add(newBook);
	}
	
	@Override
	public void removeBook(String isbn) throws RuntimeException {
		Iterator<Book> iterator = ListPersistance.getInstance().books.iterator();
		
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
	public void removeBooksByAuthor(String name) {
		Iterator<Book> iterator = ListPersistance.getInstance().books.iterator();
		
		while (iterator.hasNext()) {
			Book book = (Book) iterator.next();
			if(book.getAuthor().getName().equals(name)) {
				iterator.remove();
			}
		}
	}
	
	@Override
	public List<Book> getAllBooks() {
		return ListPersistance.getInstance().books;
	}
	
	@Override
	public List<Book> getBooks(char c) {
		Criteria startsWith = new CriteriaStartsWith(c);
		return startsWith.meetCriteria(ListPersistance.getInstance().books);
	}
	
	@Override
	public List<Book> getBooks(Genre genre) {
		Criteria criteriaGenre = new CriteriaGenre(genre);
		return criteriaGenre.meetCriteria(ListPersistance.getInstance().books);
	}
	
	@Override
	public List<Book> getBooks(Author author) {
		Criteria criteriaAuthor = new CriteriaAuthor(author);
		return criteriaAuthor.meetCriteria(ListPersistance.getInstance().books);
	}
	
}
