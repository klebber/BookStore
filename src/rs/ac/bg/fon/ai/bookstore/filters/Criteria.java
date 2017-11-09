package rs.ac.bg.fon.ai.bookstore.filters;

import java.util.List;

import rs.ac.bg.fon.ai.bookstore.model.Book;

public interface Criteria {
	public List<Book> meetCriteria(List<Book> books);
}
