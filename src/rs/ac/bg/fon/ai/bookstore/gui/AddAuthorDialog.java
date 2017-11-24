package rs.ac.bg.fon.ai.bookstore.gui;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddAuthorDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private AddAuthorDialog frmAddAuthor = this;
	private JPanel southPanel;
	private JPanel centerPanel;
	private JButton btnAdd;
	private JButton btnCancel;
	private JLabel lblName;
	private JTextField txtName;
	
	private GUIController guiController = new GUIController();
	private JLabel lblId;
	private JTextField txtId;

	/**
	 * Create the dialog.
	 * @param strings 
	 * @param modal 
	 * @param frame 
	 */
	public AddAuthorDialog(boolean modal) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Add author");
		setBounds(100, 100, 350, 250);
		getContentPane().add(getSouthPanel(), BorderLayout.SOUTH);
		getContentPane().add(getCenterPanel(), BorderLayout.CENTER);
		this.setModal(modal);

	}

	private JPanel getSouthPanel() {
		if (southPanel == null) {
			southPanel = new JPanel();
			southPanel.setLayout(new MigLayout("", "[grow][][16.00][][16.00]", "[]"));
			southPanel.add(getBtnAdd(), "cell 1 0");
			southPanel.add(getBtnCancel(), "cell 3 0");
		}
		return southPanel;
	}
	private JPanel getCenterPanel() {
		if (centerPanel == null) {
			centerPanel = new JPanel();
			centerPanel.setLayout(new MigLayout("", "[25.00][][19.00][grow][30.00]", "[30.00][][]"));
			centerPanel.add(getLblId(), "cell 1 1");
			centerPanel.add(getTxtId(), "cell 3 1,growx");
			centerPanel.add(getLblName(), "cell 1 2");
			centerPanel.add(getTxtName(), "cell 3 2,growx");
		}
		return centerPanel;
	}
	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("Add");
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					complete();
				}
			});
			btnAdd.setMinimumSize(new Dimension(80, 23));
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
			btnCancel.setMinimumSize(new Dimension(80, 23));
		}
		return btnCancel;
	}
	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("Name:");
		}
		return lblName;
	}
	private JTextField getTxtName() {
		if (txtName == null) {
			txtName = new JTextField();
			txtName.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == 10) {
						complete();
					}
				}
			});
			txtName.setColumns(10);
		}
		return txtName;
	}

	private void complete() {
		if(txtId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(frmAddAuthor, "You need to enter id of the author.");
			return;
		}
		double id = -1;
		try {
			id = Double.parseDouble(txtId.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(frmAddAuthor, "Id must be a number.");
			return;
		}
		if(id < 0 || (int) id != id) {
			JOptionPane.showMessageDialog(frmAddAuthor, "Entered id is in the wrong format.");
			return;
		}
		if(txtName.getText().isEmpty()) {
			JOptionPane.showMessageDialog(frmAddAuthor, "You need to enter name of the author.");
			return;
		}
		if(txtName.getText().split(" ").length < 2) {
			JOptionPane.showMessageDialog(frmAddAuthor, "Please enter both first and last name of the author.");
			return;
		}
		try {
			guiController.addAuthor((int) id, txtName.getText());
			this.dispose();
		} catch (RuntimeException e) {
			JOptionPane.showMessageDialog(frmAddAuthor, e.getMessage());
		}
	}
	private JLabel getLblId() {
		if (lblId == null) {
			lblId = new JLabel("Id:");
		}
		return lblId;
	}
	private JTextField getTxtId() {
		if (txtId == null) {
			txtId = new JTextField();
			txtId.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == 10) {
						complete();
					}
				}
			});
			txtId.setColumns(10);
		}
		return txtId;
	}
}
