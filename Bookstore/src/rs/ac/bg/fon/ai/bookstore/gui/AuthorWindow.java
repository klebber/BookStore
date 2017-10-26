package rs.ac.bg.fon.ai.bookstore.gui;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class AuthorWindow extends JDialog {
	private static final long serialVersionUID = 1L;
	private String[] authors;
	private AuthorWindow frmAuthor = this;
	private JPanel eastPanel;
	private JPanel southPanel;
	private JPanel centralPanel;
	private JList<String> list;
	private JButton btnAdd;
	private JButton btnRemove;
	private JButton btnClose;
	private JScrollPane scrollPane;

	/**
	 * Create the dialog.
	 * @param authors 
	 * @param modal 
	 * @param frame 
	 */
	public AuthorWindow(JFrame frame, boolean modal, String[] authors) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Authors");
		setResizable(false);
		setBounds(150, 150, 360, 400);
		getContentPane().add(getEastPanel(), BorderLayout.EAST);
		getContentPane().add(getSouthPanel(), BorderLayout.SOUTH);
		getContentPane().add(getCentralPanel(), BorderLayout.CENTER);
		
		this.authors = authors;
		if(authors != null)
			list.setListData(this.authors);
		this.setModal(modal);
	}

	private JPanel getEastPanel() {
		if (eastPanel == null) {
			eastPanel = new JPanel();
			eastPanel.setLayout(new MigLayout("", "[80.00]", "[40.00][][16.00][]"));
			eastPanel.add(getBtnAdd(), "cell 0 1");
			eastPanel.add(getBtnRemove(), "cell 0 3");
		}
		return eastPanel;
	}
	private JPanel getSouthPanel() {
		if (southPanel == null) {
			southPanel = new JPanel();
			southPanel.setLayout(new MigLayout("", "[grow][][50.00]", "[]"));
			southPanel.add(getBtnClose(), "cell 2 0");
		}
		return southPanel;
	}
	private JPanel getCentralPanel() {
		if (centralPanel == null) {
			centralPanel = new JPanel();
			centralPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
			centralPanel.add(getScrollPane(), "cell 0 0,grow");
		}
		return centralPanel;
	}
	private JList<String> getList() {
		if (list == null) {
			list = new JList<String>();
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return list;
	}
	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("Add");
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GUIController.openAddAuthorDialog(true);
				}
			});
			btnAdd.setMinimumSize(new Dimension(80, 23));
		}
		return btnAdd;
	}
	private JButton getBtnRemove() {
		if (btnRemove == null) {
			btnRemove = new JButton("Remove");
			btnRemove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (list.getSelectedIndex() == -1)
						return;
					int n = JOptionPane.showConfirmDialog(
							frmAuthor, "Removing an author will also remove all of that author's books. "
									+ "\nAre you sure you wish to remove " + list.getSelectedValue() + "?",
									"Removing author",
									JOptionPane.YES_NO_OPTION);
					if(n == JOptionPane.NO_OPTION)
						return;
					GUIController.removeAuthor(list.getSelectedValue());
				}
			});
			btnRemove.setMinimumSize(new Dimension(80, 23));
		}
		return btnRemove;
	}
	public void updateAuthorList(String[] authors) {
		this.authors = authors;
		list.setListData(this.authors);
	}
	private JButton getBtnClose() {
		if (btnClose == null) {
			btnClose = new JButton("Close");
			btnClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnClose.setMinimumSize(new Dimension(80, 23));
		}
		return btnClose;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getList());
		}
		return scrollPane;
	}
}
