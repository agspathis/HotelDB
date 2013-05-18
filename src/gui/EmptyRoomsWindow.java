package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import database.DatabaseSelect;

/**
 * This class implements the empty room search action with a gui interface
 * 
 * @author
 */
public class EmptyRoomsWindow extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextArea textArea;

	/**
	 * Creates new form FreeRoomsWindow
	 */
	public EmptyRoomsWindow(JTextArea textArea) {
		this.textArea = textArea;
		initComponents();
	}


	/**
	 * Window initialization with Netbeans
	 */
	private void initComponents() {

		fromLabel = new javax.swing.JLabel();
		toLabel = new javax.swing.JLabel();
		fromTF = new javax.swing.JTextField();
		toTF = new javax.swing.JTextField();
		showButton = new javax.swing.JToggleButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Empty Rooms");
		setResizable(false);

		fromLabel.setText("From");

		toLabel.setText("To");

		fromTF.setColumns(11);

		toTF.setColumns(11);

		showButton.setText("Show");
		showButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				searchForEmptyRoomsAction();
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
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														javax.swing.GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						fromTF,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						fromLabel))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		42,
																		Short.MAX_VALUE)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						toTF,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						toLabel)))
												.addGroup(
														javax.swing.GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup()
																.addGap(0,
																		0,
																		Short.MAX_VALUE)
																.addComponent(
																		showButton)))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(29, 29, 29)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(fromLabel)
												.addComponent(toLabel))
								.addGap(9, 9, 9)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														fromTF,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														toTF,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(showButton)
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));

		pack();
	}

	/**
	 * Empty rooms search action
	 */
	private void searchForEmptyRoomsAction() {
		//text field validation
		if (fromTF.getText().length() != 10 || toTF.getText().length() != 10 ||
				!checkDate()) {
			JOptionPane.showMessageDialog(null, "Invalid Date (YYYY-MM-DD)",
					"Date error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		String FREEROOMQUERY = 
				"SELECT * " + "FROM Room "
				+ "WHERE idRoom not in " + "(SELECT idRoom "
				+ "FROM Rental " + "WHERE (date(arrivalDate)>='"
				+ fromTF.getText() + "' AND date(arrivalDate)<'"
				+ toTF.getText() + "') OR " + "(date(arrivalDate)<='"
				+ fromTF.getText() + "' AND date(departureDate)>'"
				+ fromTF.getText() + "'))";

		DatabaseSelect freeRoomSelect = new DatabaseSelect(textArea,
				FREEROOMQUERY);
		
		if (!(freeRoomSelect.getConnectionState() == true
				&& freeRoomSelect.execute())) {
			JOptionPane.showMessageDialog(null, "Free room field",
					"Free room error", JOptionPane.ERROR_MESSAGE);
		}
		freeRoomSelect.closeConnection();
		
		textArea.append(Window.SEPERATOR);
	}

	/**
	 * Date validation
	 * 
	 * @return true of date is valid, else false
	 */
	private boolean checkDate() {
		if ( fromTF.getText().compareTo(toTF.getText()) >= 0) {
			return false;
		} else {
			return true;
		}
	}
	
	// Variables declaration - do not modify
	private javax.swing.JTextField fromTF;
	private javax.swing.JLabel fromLabel;
	private javax.swing.JLabel toLabel;
	private javax.swing.JToggleButton showButton;
	private javax.swing.JTextField toTF;
	// End of variables declaration
}
