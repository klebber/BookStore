package rs.ac.bg.fon.ai.bookstore.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import rs.ac.bg.fon.ai.bookstore.model.Author;
import rs.ac.bg.fon.ai.bookstore.model.Book;
import rs.ac.bg.fon.ai.bookstore.model.Genre;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.awt.event.ItemEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.ListSelectionModel;
import java.awt.Toolkit;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private MainWindow frmMain = this;
	private String[] authors;
	
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnEdit;
	private JMenu mnHelp;
	private JPanel booksPanel;
	private JLabel lblFilter;
	private JComboBox<String> cbBookFilter1;
	private JComboBox<String> cbBookFilter2;
	private JButton btnAdd;
	private JTable table;
	private JScrollPane scrollPane;
	private JPanel eastPanel;
	private JPanel southPanel;
	private JButton btnRemove;
	private JButton btnAuthors;

	/**
	 * Create the frame.
	 */
	public MainWindow(String[] authors) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/rs/ac/bg/fon/ai/bookstore/resources/open-book.png")));
		this.authors = authors;
		setTitle("Bookstore");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 450);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getBooksPanel(), BorderLayout.CENTER);
		contentPane.add(getEastPanel(), BorderLayout.EAST);
		contentPane.add(getSouthPanel(), BorderLayout.SOUTH);
	}

	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnFile());
			menuBar.add(getMnEdit());
			menuBar.add(getMnHelp());
		}
		return menuBar;
	}
	private JMenu getMnFile() {
		if (mnFile == null) {
			mnFile = new JMenu("File");
		}
		return mnFile;
	}
	private JMenu getMnEdit() {
		if (mnEdit == null) {
			mnEdit = new JMenu("Edit");
		}
		return mnEdit;
	}
	private JMenu getMnHelp() {
		if (mnHelp == null) {
			mnHelp = new JMenu("Help");
		}
		return mnHelp;
	}
	private JPanel getBooksPanel() {
		if (booksPanel == null) {
			booksPanel = new JPanel();
			booksPanel.setLayout(new MigLayout("", "[18.00][][150.00][150.00][grow][]", "[20.00][179.00][]"));
			booksPanel.add(getScrollPane(), "cell 0 0 6 2,grow");
		}
		return booksPanel;
	}
	private JLabel getLblFilter() {
		if (lblFilter == null) {
			lblFilter = new JLabel("Filter:");
		}
		return lblFilter;
	}
	private JComboBox<String> getCbBookFilter1() {
		if (cbBookFilter1 == null) {
			cbBookFilter1 = new JComboBox<String>();
			cbBookFilter1.setPreferredSize(new Dimension(150, 20));
			cbBookFilter1.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if(cbBookFilter1.getSelectedIndex() == 0) {
						cbBookFilter2.setEnabled(false);
						cbBookFilter2.setModel(new DefaultComboBoxModel<String>());
						GUIController.reloadTable();
					} else if(cbBookFilter1.getSelectedIndex() == 1) {
						cbBookFilter2.setEnabled(true);
						cbBookFilter2.setModel(new DefaultComboBoxModel<String>(new String[] {"--Select--", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}));
						GUIController.reloadTable();
					} else if(cbBookFilter1.getSelectedIndex() == 2) {
						cbBookFilter2.setEnabled(true);
						String[] select = {"--Select--"};
						String[] both = Stream.concat(Arrays.stream(select), Arrays.stream(Genre.values()).map(Genre::name)).toArray(String[]::new);
						cbBookFilter2.setModel(new DefaultComboBoxModel<String>(both));
						GUIController.reloadTable();
					} else if(cbBookFilter1.getSelectedIndex() == 3) {
						cbBookFilter2.setEnabled(true);
						String[] select = {"--Select--"};
						String[] both = Stream.concat(Arrays.stream(select), Arrays.stream(authors)).toArray(String[]::new);
						GUIController.reloadTable();
						cbBookFilter2.setModel(new DefaultComboBoxModel<String>(both));
					}
				}
			});
			cbBookFilter1.setModel(new DefaultComboBoxModel<String>(new String[] {"Show all books", "Filter by Name", "Filter by Genre", "Filter by Author"}));
		}
		return cbBookFilter1;
	}
	private JComboBox<String> getCbBookFilter2() {
		if (cbBookFilter2 == null) {
			cbBookFilter2 = new JComboBox<String>();
			cbBookFilter2.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					applySelectedFilter();
				}
			});
			cbBookFilter2.setPreferredSize(new Dimension(150, 20));
			cbBookFilter2.setEnabled(false);
		}
		return cbBookFilter2;
	}
	public void applySelectedFilter() {
		if(cbBookFilter1.getSelectedIndex() == 0)
			GUIController.reloadTable();
		else if(cbBookFilter2.getSelectedIndex() == 0)
			GUIController.reloadTable();
		else if(cbBookFilter2.getSelectedItem().toString().length() == 1)
			GUIController.filterTable(cbBookFilter2.getSelectedItem().toString().charAt(0));
		else if(cbBookFilter1.getSelectedIndex() == 2)
			GUIController.filterTable(Genre.valueOf(cbBookFilter2.getSelectedItem().toString()));
		else if(cbBookFilter1.getSelectedIndex() == 3)
			GUIController.filterTable(new Author(cbBookFilter2.getSelectedItem().toString()));
	}
	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setModel(new TableModel(null));
			table.getColumnModel().getColumn(0).setPreferredWidth(107);
			table.getColumnModel().getColumn(1).setPreferredWidth(159);
			table.getColumnModel().getColumn(2).setPreferredWidth(80);
			table.getColumnModel().getColumn(3).setPreferredWidth(130);
			table.getColumnModel().getColumn(4).setPreferredWidth(109);
			table.getColumnModel().getColumn(5).setPreferredWidth(84);
		}
		return table;
	}
	public void updateTable(List<Book> books) {
		TableModel tableModel = (TableModel) table.getModel();
		tableModel.updateTable(books);
	}
	public void updateAuthorsArray(String[] authors) {
		this.authors = authors;
		if(cbBookFilter1.getSelectedIndex() == 3)
			cbBookFilter1.setSelectedIndex(0);
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	private JPanel getEastPanel() {
		if (eastPanel == null) {
			eastPanel = new JPanel();
			eastPanel.setLayout(new MigLayout("", "[84.00]", "[24][24][24][24][24][24][24][24][24][24][24][24][24]"));
			eastPanel.add(getBtnAdd(), "cell 0 1");
			eastPanel.add(getBtnRemove(), "cell 0 2");
			eastPanel.add(getBtnAuthors(), "cell 0 5");
		}
		return eastPanel;
	}
	private JPanel getSouthPanel() {
		if (southPanel == null) {
			southPanel = new JPanel();
			southPanel.setLayout(new MigLayout("", "[24.00][][9.00][150.00][20.00][][25.00][]", "[]"));
			southPanel.add(getLblFilter(), "cell 1 0");
			southPanel.add(getCbBookFilter1(), "cell 3 0");
			southPanel.add(getCbBookFilter2(), "cell 5 0");
		}
		return southPanel;
	}
	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("Add");
			btnAdd.setMinimumSize(new Dimension(80, 23));
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GUIController.openAddBookDialog(frmMain, true);
				}
			});
		}
		return btnAdd;
	}
	private JButton getBtnRemove() {
		if (btnRemove == null) {
			btnRemove = new JButton("Remove");
			btnRemove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(table.getSelectedRow() != -1)
						try {
							GUIController.removeBook(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
						} catch (RuntimeException e2) {
							JOptionPane.showMessageDialog(frmMain, e2.getMessage());
						}
				}
			});
			btnRemove.setMinimumSize(new Dimension(80, 23));
		}
		return btnRemove;
	}
	private JButton getBtnAuthors() {
		if (btnAuthors == null) {
			btnAuthors = new JButton("Authors");
			btnAuthors.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GUIController.openAuthorWindow(frmMain, true);
				}
			});
			btnAuthors.setMinimumSize(new Dimension(80, 23));
		}
		return btnAuthors;
	}
}
