package rs.ac.bg.fon.ai.bookstore.gui;


import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import rs.ac.bg.fon.ai.bookstore.model.Genre;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;

public class AddBookDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private String[] authors;
	private AddBookDialog frmAddBook = this;
	private JPanel southPanel;
	private JPanel centerPanel;
	private JButton btnAdd;
	private JButton btnCancel;
	private JLabel lblIsbn;
	private JTextField txtISBN;
	private JLabel lblTitle;
	private JTextField txtTitle;
	private JLabel lblGenre;
	private JComboBox<Genre> cbGenre;
	private JLabel lblPublisher;
	private JTextField txtPublisher;
	private JLabel lblPublishDate;
	private JTextField txtDate;
	private JLabel lblFormatDdmmyyyy;
	private JLabel lblAuthor;
	private JComboBox<String> cbAuthor;

	private GUIController guiController = new GUIController();
	
	/**
	 * Create the dialog.
	 */
	public AddBookDialog(JFrame frame, boolean modal, String[] authors) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		this.authors = authors;
		this.setModal(modal);
		setTitle("Add new book");
		setBounds(150, 150, 450, 320);
		getContentPane().add(getSouthPanel(), BorderLayout.SOUTH);
		getContentPane().add(getCenterPanel(), BorderLayout.CENTER);

	}

	private JPanel getSouthPanel() {
		if (southPanel == null) {
			southPanel = new JPanel();
			southPanel.setLayout(new MigLayout("", "[40.00,grow][][28.00][][40.00]", "[]"));
			southPanel.add(getBtnAdd(), "cell 1 0");
			southPanel.add(getBtnCancel(), "cell 3 0");
		}
		return southPanel;
	}
	private JPanel getCenterPanel() {
		if (centerPanel == null) {
			centerPanel = new JPanel();
			centerPanel.setLayout(new MigLayout("", "[47.00][][][grow][42.00]", "[27.00][][][][][][][]"));
			centerPanel.add(getLblIsbn(), "cell 1 1");
			centerPanel.add(getTxtISBN(), "cell 3 1,growx");
			centerPanel.add(getLblTitle(), "cell 1 2");
			centerPanel.add(getTxtTitle(), "cell 3 2,growx,aligny baseline");
			centerPanel.add(getLblGenre(), "cell 1 3");
			centerPanel.add(getCbGenre(), "cell 3 3,growx");
			centerPanel.add(getLblAuthor(), "cell 1 4");
			centerPanel.add(getCbAuthor(), "cell 3 4,growx");
			centerPanel.add(getLblPublisher(), "cell 1 5");
			centerPanel.add(getTxtPublisher(), "cell 3 5,growx");
			centerPanel.add(getLblPublishDate(), "cell 1 6");
			centerPanel.add(getTxtDate(), "cell 3 6,growx");
			centerPanel.add(getLblFormatDdmmyyyy(), "cell 3 7");
		}
		return centerPanel;
	}
	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("Add");
			btnAdd.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == 10)
						complete();
				}
			});
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					complete();
				}
			});
		}
		return btnAdd;
	}
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return btnCancel;
	}
	private JLabel getLblIsbn() {
		if (lblIsbn == null) {
			lblIsbn = new JLabel("ISBN:");
			lblIsbn.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblIsbn;
	}
	private JTextField getTxtISBN() {
		if (txtISBN == null) {
			txtISBN = new JTextField();
			txtISBN.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == 10)
						complete();
				}
			});
			txtISBN.setColumns(10);
		}
		return txtISBN;
	}
	private JLabel getLblTitle() {
		if (lblTitle == null) {
			lblTitle = new JLabel("Title:");
			lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblTitle;
	}
	private JTextField getTxtTitle() {
		if (txtTitle == null) {
			txtTitle = new JTextField();
			txtTitle.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == 10)
						complete();
				}
			});
			txtTitle.setColumns(10);
		}
		return txtTitle;
	}
	private JLabel getLblGenre() {
		if (lblGenre == null) {
			lblGenre = new JLabel("Genre:");
		}
		return lblGenre;
	}
	private JComboBox<Genre> getCbGenre() {
		if (cbGenre == null) {
			cbGenre = new JComboBox<Genre>();
			cbGenre.setModel(new DefaultComboBoxModel<Genre>(Genre.values()));
		}
		return cbGenre;
	}
	private JLabel getLblAuthor() {
		if (lblAuthor == null) {
			lblAuthor = new JLabel("Author:");
		}
		return lblAuthor;
	}
	private JComboBox<String> getCbAuthor() {
		if (cbAuthor == null) {
			cbAuthor = new JComboBox<String>();
			cbAuthor.setModel(new DefaultComboBoxModel<String>(authors));
		}
		return cbAuthor;
	}
	private JLabel getLblPublisher() {
		if (lblPublisher == null) {
			lblPublisher = new JLabel("Publisher:");
		}
		return lblPublisher;
	}
	private JTextField getTxtPublisher() {
		if (txtPublisher == null) {
			txtPublisher = new JTextField();
			txtPublisher.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == 10)
						complete();
				}
			});
			txtPublisher.setColumns(10);
		}
		return txtPublisher;
	}
	private JLabel getLblPublishDate() {
		if (lblPublishDate == null) {
			lblPublishDate = new JLabel("Publish date:");
		}
		return lblPublishDate;
	}
	private JTextField getTxtDate() {
		if (txtDate == null) {
			txtDate = new JTextField();
			txtDate.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == 10)
						complete();
				}
			});
			txtDate.setColumns(10);
		}
		return txtDate;
	}
	private JLabel getLblFormatDdmmyyyy() {
		if (lblFormatDdmmyyyy == null) {
			lblFormatDdmmyyyy = new JLabel("Format: DD/MM/YYYY");
			lblFormatDdmmyyyy.setForeground(Color.GRAY);
		}
		return lblFormatDdmmyyyy;
	}

	private void complete() {
		if(txtISBN.getText().isEmpty()) {
			JOptionPane.showMessageDialog(frmAddBook, "You need to enter ISBN.");
			return;
		}
		if(txtTitle.getText().isEmpty()) {
			JOptionPane.showMessageDialog(frmAddBook, "You ned to enter book title.");
			return;
		}
		if(txtPublisher.getText().isEmpty()) {
			JOptionPane.showMessageDialog(frmAddBook, "You need to enter name of the book publisher.");
			return;
		}
		if(txtDate.getText().isEmpty()) {
			JOptionPane.showMessageDialog(frmAddBook, "You need to enter book publish date.");
			return;
		}
		if(cbAuthor.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(frmAddBook, "There are no authors in database, please add an author first.");
			return;
		}
		if(txtDate.getText().charAt(2) != '/' || txtDate.getText().charAt(5) != '/' || txtDate.getText().length() != 10) {
			JOptionPane.showMessageDialog(frmAddBook, "You have entered date in wrong format.");
			return;
		}
		String[] dateSplit = txtDate.getText().split("/");
		GregorianCalendar date = new GregorianCalendar(Integer.parseInt(dateSplit[2]), Integer.parseInt(dateSplit[1]) - 1, Integer.parseInt(dateSplit[0]));
		String test = new SimpleDateFormat("dd/MM/yyyy").format(date.getTime());
		if(!txtDate.getText().equals(test)) {
			JOptionPane.showMessageDialog(frmAddBook, "You have entered non-existant date.");
			return;
		}
		GregorianCalendar today = new GregorianCalendar();
		if(date.after(today)) {
			JOptionPane.showMessageDialog(frmAddBook, "The entered date is after today.");
			return;
		}
		try {
			this.dispose();
			guiController.addBook(txtISBN.getText(), txtTitle.getText(), (Genre) cbGenre.getSelectedItem(),	(String) cbAuthor.getSelectedItem(), txtPublisher.getText(), date.getTime());
		} catch (RuntimeException e) {
			JOptionPane.showMessageDialog(frmAddBook, e.getMessage());
			e.printStackTrace();
		}
	}
	
	
}
