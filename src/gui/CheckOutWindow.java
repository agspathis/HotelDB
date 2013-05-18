package gui;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import database.DatabaseGetSelect;
import database.DatabaseUpdate;

/**
 * This class implements the check out action with a gui interface
 * 
 * @author
 */
public class CheckOutWindow extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textArea;

	/**
	 * Creates new form CheckInWindow
	 */
	public CheckOutWindow(JTextArea textArea) {
		this.textArea = textArea;
		initComponents();
	}

	/**
	 * Window initialization with Netbeans
	 */
	private void initComponents() {

		roomLabel = new javax.swing.JLabel();
		roomTF = new javax.swing.JTextField();
		cOButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Check Out");
		setResizable(false);

		roomLabel.setText("Room");

		roomTF.setColumns(5);

		cOButton.setText("Check Out");
		cOButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				checkOutAction();

			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(roomLabel)
								.addGap(39, 39, 39)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(cOButton)
												.addComponent(
														roomTF,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap(21, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(16, 16, 16)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(roomLabel)
												.addComponent(
														roomTF,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(cOButton)
								.addContainerGap(24, Short.MAX_VALUE)));

		pack();
	}

	/**
	 * Check out action
	 */
	private void checkOutAction() {
		// check text field validation
		if (roomTF.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Fill room's text field",
					"Empty text field", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		if (checkIfPayed()) {
			String CHECKOUTQUERY = 
					"UPDATE Rental " + "SET checkOut = True "
					+ "WHERE idRoom = '" + roomTF.getText() + "' AND " +
							"date(departureDate) = current_date()";

			DatabaseUpdate checkOutUpdate = new DatabaseUpdate(textArea,
					CHECKOUTQUERY);
			
			if (!(checkOutUpdate.getConnectionState() == true
					&& checkOutUpdate.execute())) {
				JOptionPane.showMessageDialog(null, "Check out field",
						"Check out error", JOptionPane.ERROR_MESSAGE);
			}
			checkOutUpdate.closeConnection();
		}
		
		textArea.append(Window.SEPERATOR);

	}

	/**
	 * Check room is payed before check out action
	 * 
	 * @return true if payed, else false
	 */
	private boolean checkIfPayed() {
		String CHECKIFPAYEDQUERY = 
				"SELECT payed " + "FROM Rental "
				+ "WHERE idRoom='" + roomTF.getText() + "' "
				+ "AND date(departureDate) = current_date()";

		DatabaseGetSelect checkIfPayed = new DatabaseGetSelect(textArea,
				CHECKIFPAYEDQUERY);

		ResultSet checkResult = checkIfPayed.getResult();
		if (checkResult != null) {
			try {
				if (!checkResult.next()) {
					JOptionPane.showMessageDialog(null, "No such rental",
							"Check out error", JOptionPane.INFORMATION_MESSAGE);
					return false;
				}
				if (checkResult.getBoolean("payed") == false) {
					JOptionPane.showMessageDialog(null,
							"Customer hasn't payed", "Not payed",
							JOptionPane.INFORMATION_MESSAGE);
					return false;
				} else {
					return true;
				}
			} catch (HeadlessException | SQLException e1) {
				JOptionPane.showMessageDialog(null, "Check out error", "Error",
						JOptionPane.ERROR_MESSAGE);
				return false;
			} finally {
				checkIfPayed.closeAll();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Check if payed failed",
					"Check if payed error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

	}

	// Variables declaration - do not modify
	private javax.swing.JButton cOButton;
	private javax.swing.JLabel roomLabel;
	private javax.swing.JTextField roomTF;
	// End of variables declaration
}
