package rs.ac.bg.fon.ai.bookstore.dao;

import java.util.ArrayList;
import java.util.List;

import rs.ac.bg.fon.ai.bookstore.filters.Criteria;
import rs.ac.bg.fon.ai.bookstore.filters.CriteriaAuthor;
import rs.ac.bg.fon.ai.bookstore.filters.CriteriaGenre;
import rs.ac.bg.fon.ai.bookstore.filters.CriteriaStartsWith;
import rs.ac.bg.fon.ai.bookstore.model.Author;
import rs.ac.bg.fon.ai.bookstore.model.Book;
import rs.ac.bg.fon.ai.bookstore.model.Genre;

public class BookDaoList implements BookDaoInterface {

	private static BookDaoList instance = new BookDaoList();
	private List<Book> books = new ArrayList<Book>();
	
	private BookDaoList() {
	}
	
	@Override
	public void addBook(Book book) throws RuntimeException {
		for (Book temp : books) {
			if(temp.getIsbn().equals(book.getIsbn()))
				throw new RuntimeException("Book with same isbn already exists.");
			if(temp.getTitle().equals(book.getTitle()) && temp.getAuthor().equals(book.getAuthor()))
				throw new RuntimeException("Book of the same author and with the same name already exists.");
		}
		books.add(book);
	}
	
	@Override
	public void removeBook(String isbn) throws RuntimeException {
		for (Book temp : books)
			if(temp.getIsbn().equals(isbn)) {
				books.remove(temp);
				return;
			}
		throw new RuntimeException("Book with this isbn has not been found.");
	}
	
	@Override
	public void removeBooks(String name) {
		for (int i = 0; i < books.size(); i++)
			if(books.get(i).getAuthor().getName().equals(name)) {
				books.remove(i);
				i--;
			}
	}
	
	@Override
	public List<Book> getAllBooks() {
		return books;
	}
	
	@Override
	public List<Book> getBooks(char c) {
		Criteria startsWith = new CriteriaStartsWith(c);
		return startsWith.meetCriteria(books);
	}
	
	@Override
	public List<Book> getBooks(Genre genre) {
		Criteria criteriaGenre = new CriteriaGenre(genre);
		return criteriaGenre.meetCriteria(books);
	}
	
	@Override
	public List<Book> getBooks(Author author) {
		Criteria criteriaAuthor = new CriteriaAuthor(author);
		return criteriaAuthor.meetCriteria(books);
	}

	public static BookDaoList getInstance() {
		return instance;
	}

	public static void setInstance(BookDaoList instance) {
		BookDaoList.instance = instance;
	}
	
}
