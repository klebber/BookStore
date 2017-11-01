package rs.ac.bg.fon.ai.bookstore.filters;

import java.util.ArrayList;
import java.util.List;

import rs.ac.bg.fon.ai.bookstore.model.Book;

public class CriteriaStartsWith implements Criteria {

	private char startingChar;
	
	public CriteriaStartsWith(char c) {
		super();
		this.startingChar = c;
	}
	
	@Override
	public List<Book> meetCriteria(List<Book> books) {
		List<Book> filteredBooks = new ArrayList<Book>();
		for (Book book : books) {
			if(startingChar == book.getTitle().charAt(0) ||
					(Character.isLetter(book.getTitle().charAt(0)) && Character.toUpperCase(book.getTitle().charAt(0)) == startingChar))
				filteredBooks.add(book);
		}
		return filteredBooks;
	}

}
