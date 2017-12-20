package rs.ac.bg.fon.ai.bookstore.service;

import java.util.Iterator;
import java.util.List;

import rs.ac.bg.fon.ai.bookstore.model.Author;
import rs.ac.bg.fon.ai.bookstore.persistence.ListPersistence;

@Deprecated
public class AuthorServiceImpl implements AuthorService {
	
	@Override
	public void addAuthor(int id, String name) throws RuntimeException {
		for (Author a : ListPersistence.getInstance().authors)
			if(a.getId() == id)
				throw new RuntimeException("Author with the same id already exists.");
		
		ListPersistence.getInstance().authors.add(new Author(id, name));
	}
	
	@Override
	public void removeAuthor(int id) throws RuntimeException {
		Iterator<Author> iterator = ListPersistence.getInstance().authors.iterator();
		
		while (iterator.hasNext()) {
			Author author = (Author) iterator.next();
			if(author.getId() == id) {
				iterator.remove();
				return;
			}
		}
		throw new RuntimeException("Author you are attempting to remove has not been found.");
	}
	
	@Override
	public void updateAuthor(int currentId, Author updatedAuthor) throws RuntimeException {
		Iterator<Author> iterator = ListPersistence.getInstance().authors.iterator();

		while (iterator.hasNext()) {
			Author author = (Author) iterator.next();
			if (author.getId() == currentId) {
				author = updatedAuthor;
				return;
			}
		}
		throw new RuntimeException("Author you are attempting to update has not been found.");
	}
	
	@Override
	public List<Author> getAllAuthors() {
		return ListPersistence.getInstance().authors;
	}

	@Override
	public Author getAuthor(int id) {
		for (Author author : ListPersistence.getInstance().authors) {
			if (author.getId() == id) {
				return author;
			}
		}
		return null;
	}
	
}
