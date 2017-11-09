package rs.ac.bg.fon.ai.bookstore.gui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import rs.ac.bg.fon.ai.bookstore.model.Book;

public class TableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	
	private String[] columns = {"ISBN", "Title", "Genre", "Author", "Publisher", "Publish date"};
	private List<Book> books;

	public TableModel(List<Book> books) {
		if(books == null)
			this.books = new ArrayList<Book>();
		else
			this.books = books;
	}
	
	@Override
	public int getRowCount() {
		return books.size();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Book book = books.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return book.getIsbn();
		case 1:
			return book.getTitle();
		case 2:
			return book.getGenre();
		case 3:
			return book.getAuthor();
		case 4:
			return book.getPublisher();
		case 5:
			GregorianCalendar date = book.getPublishDate();
			return new SimpleDateFormat("dd/MM/yyyy").format(date.getTime());

		default:
			return "/";
		}
	}

	@Override
	public String getColumnName(int column) {
		return columns[column];
	}
	
	public void updateTable(List<Book> books) {
		this.books = books;
		fireTableDataChanged();
	}
}
