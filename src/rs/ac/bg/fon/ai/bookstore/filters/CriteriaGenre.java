package rs.ac.bg.fon.ai.bookstore.filters;

import java.util.ArrayList;
import java.util.List;

import rs.ac.bg.fon.ai.bookstore.model.Book;
import rs.ac.bg.fon.ai.bookstore.model.Genre;

public class CriteriaGenre implements Criteria {

	private Genre genre;
	
	public CriteriaGenre(Genre genre) {
		super();
		this.genre = genre;
	}
	
	@Override
	public List<Book> meetCriteria(List<Book> books) {
		List<Book> filteredBooks = new ArrayList<Book>();
		for (Book book : books) {
			if(book.getGenre().equals(genre))
				filteredBooks.add(book);
		}
		return filteredBooks;
	}

}
