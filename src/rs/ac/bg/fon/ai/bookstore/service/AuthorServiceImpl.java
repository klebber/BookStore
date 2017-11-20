package rs.ac.bg.fon.ai.bookstore.service;

import java.util.Iterator;
import java.util.List;

import rs.ac.bg.fon.ai.bookstore.model.Author;
import rs.ac.bg.fon.ai.bookstore.persistance.ListPersistance;

public class AuthorServiceImpl implements AuthorService {
	
	@Override
	public void addAuthor(String name) throws RuntimeException {
		for (Author a : ListPersistance.getInstance().authors)
			if(a.getName().equals(name))
				throw new RuntimeException("Author with the same name already exists.");
		
		ListPersistance.getInstance().authors.add(new Author(name));
	}
	
	@Override
	public void removeAuthor(String name) throws RuntimeException {
		Iterator<Author> iterator = ListPersistance.getInstance().authors.iterator();
		
		while (iterator.hasNext()) {
			Author author = (Author) iterator.next();
			if(author.getName().equals(name)) {
				iterator.remove();
				return;
			}
		}
		throw new RuntimeException("Author with this name has not been found.");
	}
	
	@Override
	public List<Author> getAllAuthors() {
		return ListPersistance.getInstance().authors;
	}

	@Override
	public Author getAuthor(String authorName) {
		for (Author author : ListPersistance.getInstance().authors) {
			if (author.getName().equals(authorName)) {
				return author;
			}
		}
		return null;
	}
	
}
