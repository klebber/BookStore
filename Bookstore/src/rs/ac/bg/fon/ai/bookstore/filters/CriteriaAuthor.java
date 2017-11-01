package rs.ac.bg.fon.ai.bookstore.filters;

import java.util.ArrayList;
import java.util.List;

import rs.ac.bg.fon.ai.bookstore.model.Author;
import rs.ac.bg.fon.ai.bookstore.model.Book;

public class CriteriaAuthor implements Criteria {
	
	private Author author;
	
	public CriteriaAuthor(Author author) {
		super();
		this.author = author;
	}

	@Override
	public List<Book> meetCriteria(List<Book> books) {
		List<Book> filteredBooks = new ArrayList<Book>();
		for (Book book : books) {
			if(book.getAuthor().equals(author))
				filteredBooks.add(book);
		}
		return filteredBooks;
	}

}
