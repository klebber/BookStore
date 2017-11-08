package rs.ac.bg.fon.ai.bookstore.dao;

import java.util.ArrayList;
import java.util.List;

import rs.ac.bg.fon.ai.bookstore.model.Author;

public class AuthorDaoList implements AuthorDaoInterface {
	
	private static AuthorDaoList instance = new AuthorDaoList();
	private List<Author> authors = new ArrayList<Author>();
	
	private AuthorDaoList() {
	}
	
	@Override
	public void addAuthor(Author author) throws RuntimeException {
		for (Author temp : authors)
			if(temp.equals(author))
				throw new RuntimeException("Author with the same name already exists.");
		authors.add(author);
	}
	
	@Override
	public void removeAuthor(Author author) throws RuntimeException {
		for(Author temp : authors)
			if(temp.equals(author)) {
				authors.remove(temp);
				return;
			}
		throw new RuntimeException("Author with this name has not been found.");
	}
	
	@Override
	public List<Author> getAllAuthors() {
		return authors;
	}
	
	@Override
	public String[] getAuthorsArray() {
		String[] authorsArray = new String[authors.size()];
		for (int i = 0; i < authors.size(); i++)
			authorsArray[i] = authors.get(i).getName();
		return authorsArray;
	}
	
	public static AuthorDaoList getInstance() {
		return instance;
	}

	public static void setInstance(AuthorDaoList instance) {
		AuthorDaoList.instance = instance;
	}
	
}
