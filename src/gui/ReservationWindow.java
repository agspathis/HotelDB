package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import database.DatabaseGetSelect;
import database.DatabaseInsert;

/**
 * This class implements the reservation action with a gui interface
 * 
 * @author
 */
public class ReservationWindow extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textArea;
	private int idRental;
	/**
	 * Creates new form ReservationWindow
	 */
	public ReservationWindow(JTextArea textArea) {
		this.textArea = textArea;
		initComponents();	
	}

	/**
	 * Window initialization with Netbeans
	 */ 
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initComponents() {

		firstNameLabel = new javax.swing.JLabel();
		fNTF = new javax.swing.JTextField();
		lastNameLabel = new javax.swing.JLabel();
		lNTF = new javax.swing.JTextField();
		customerIDLabel = new javax.swing.JLabel();
		idTF = new javax.swing.JTextField();
		cityLabel = new javax.swing.JLabel();
		cityTF = new javax.swing.JTextField();
		countryTF = new javax.swing.JTextField();
		countryLabel = new javax.swing.JLabel();
		emailTF = new javax.swing.JTextField();
		emailLabel = new javax.swing.JLabel();
		roomLabel = new javax.swing.JLabel();
		roomTF = new javax.swing.JTextField();
		arrivalDateLabel = new javax.swing.JLabel();
		aDTF = new javax.swing.JTextField();
		departureDateLabel = new javax.swing.JLabel();
		dDTF = new javax.swing.JTextField();
		priceLabel = new javax.swing.JLabel();
		priceTF = new javax.swing.JTextField();
		bookButton = new javax.swing.JButton();
		jScrollPane2 = new javax.swing.JScrollPane();
		offerList = new javax.swing.JList();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Reservation");
		setResizable(false);

		firstNameLabel.setText("First Name*");
		firstNameLabel.setToolTipText("");

		fNTF.setColumns(10);

		lastNameLabel.setText("Last Name*");

		lNTF.setColumns(10);

		customerIDLabel.setText("ID*");

		idTF.setColumns(10);
		idTF.setToolTipText("First Name");

		cityLabel.setText("City");

		cityTF.setColumns(10);

		countryTF.setColumns(10);

		countryLabel.setText("Country");

		emailTF.setColumns(10);

		emailLabel.setText("Email");

		roomLabel.setText("Room*");

		roomTF.setColumns(10);

		arrivalDateLabel.setText("Arrival Date*");

		aDTF.setColumns(10);

		departureDateLabel.setText("Departure Date*");

		dDTF.setColumns(10);

		priceLabel.setText("Price*");

		priceTF.setColumns(10);

		bookButton.setText("Book");
		bookButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				reservationAction();
			}
		});

		offerList.setModel(new javax.swing.AbstractListModel() {

			private static final long serialVersionUID = 1L;
			
			//get discount names
			String[] strings = getDiscounts();

			public int getSize() {
				return strings.length;
			}

			public Object getElementAt(int i) {
				return strings[i];
			}
		});
		jScrollPane2.setViewportView(offerList);
		
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
												.addComponent(firstNameLabel)
												.addComponent(lastNameLabel)
												.addComponent(customerIDLabel)
												.addComponent(cityLabel)
												.addComponent(countryLabel)
												.addComponent(emailLabel))
								.addGap(30, 30, 30)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														idTF,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														fNTF,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														lNTF,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														cityTF,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														emailTF,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														countryTF,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(64, 64, 64)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
																.addComponent(
																		bookButton)
																.addGroup(
																		layout.createSequentialGroup()
																				.addComponent(
																						priceLabel)
																				.addGap(114,
																						114,
																						114)
																				.addComponent(
																						priceTF,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)))
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						arrivalDateLabel)
																				.addComponent(
																						departureDateLabel)
																				.addComponent(
																						roomLabel))
																.addGap(39, 39,
																		39)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						roomTF,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						dDTF,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						aDTF,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE))))
								.addGap(18, 18, 18)
								.addComponent(jScrollPane2,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										120, Short.MAX_VALUE).addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(5, 5, 5)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														jScrollPane2,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														192,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						customerIDLabel)
																				.addComponent(
																						idTF,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						roomLabel)
																				.addComponent(
																						roomTF,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						firstNameLabel)
																				.addComponent(
																						fNTF,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						arrivalDateLabel)
																				.addComponent(
																						aDTF,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						lastNameLabel)
																				.addComponent(
																						lNTF,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						departureDateLabel)
																				.addComponent(
																						dDTF,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						cityLabel)
																				.addComponent(
																						cityTF,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						priceLabel)
																				.addComponent(
																						priceTF,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						countryTF,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						countryLabel))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						emailTF,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						emailLabel)
																				.addComponent(
																						bookButton))))
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));

		idTF.getAccessibleContext().setAccessibleName("idTF");

		pack();
	}

	/**
	 * Reservation action
	 */
	private void reservationAction() {
		// check text field validation
		if (idTF.getText().length() == 0 || fNTF.getText().length() == 0
				|| lNTF.getText().length() == 0
				|| roomTF.getText().length() == 0
				|| aDTF.getText().length() == 0 || dDTF.getText().length() == 0
				|| priceTF.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Fill text field",
					"Empty text field", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		if (!checkDate()) {
			JOptionPane.showMessageDialog(null, "Invalid date", "Date",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		if (doCustomerExist()) {// if customer exist
			if (checkForEmptyRoom()) {// if room empty
				if (makeRental()) {// make rental
					if(makeOffer()){// make offer
						if(!addDefaultService()){// add default service
							JOptionPane.showMessageDialog(null, "Can't add service",
									"Service error", JOptionPane.ERROR_MESSAGE);
						}
					}else{
						JOptionPane.showMessageDialog(null, "Can't make offer",
								"Offer error", JOptionPane.ERROR_MESSAGE);
					}
					
				} else {// rental error
					JOptionPane.showMessageDialog(null, "Can't make rental",
							"Rental error", JOptionPane.ERROR_MESSAGE);
				}
			} else {// room not empty
				JOptionPane.showMessageDialog(null, "Room not empty", "Room",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} else {
			if (insertCustomerIfNotExist()) {// create customer
				if (checkForEmptyRoom()) {// if room free
					if (makeRental()) {// make rental
						if(makeOffer()){// make offer
							if(!addDefaultService()){// add default service
								JOptionPane.showMessageDialog(null, "Can't add service",
										"Service error", JOptionPane.ERROR_MESSAGE);
							}
						}else{
							JOptionPane.showMessageDialog(null, "Can't make offer",
									"Offer error", JOptionPane.ERROR_MESSAGE);
						}
					} else {// rental error
						JOptionPane.showMessageDialog(null,
								"Can't make rental", "Rental error",
								JOptionPane.ERROR_MESSAGE);
					}

				} else {// room not empty
					JOptionPane.showMessageDialog(null, "Room not empty",
							"Room", JOptionPane.INFORMATION_MESSAGE);
				}
			} else {// customer error
				JOptionPane.showMessageDialog(null, "Can't create customer",
						"Customer error", JOptionPane.ERROR_MESSAGE);
			}
		}

		textArea.append(Window.SEPERATOR);
	}

	/**
	 * Check if customer exist
	 * 
	 * @return true if exist, else false
	 */
	private boolean doCustomerExist() {
		String CUSTOMEREXISTQUERY = 
				"SELECT idCustomer " + "FROM Customer "
				+ "WHERE idCustomer='" + idTF.getText() + "'";

		DatabaseGetSelect customerExist = new DatabaseGetSelect(textArea,
				CUSTOMEREXISTQUERY);

		ResultSet rs = customerExist.getResult();
		if (rs != null) {
			try {
				if (!rs.next()) {
					return false;
				} else {
					return true;
				}
			} catch (SQLException e) {
				return false;
			} finally {
				customerExist.closeAll();
			}
		} else {
			return false;
		}

	}

	/**
	 * Check if room is empty
	 * 
	 * @return true if empty, else false
	 */
	private boolean checkForEmptyRoom() {
		String EMPTYROOMQUERY = 
				"SELECT idRoom " + "FROM Room "
				+ "WHERE idRoom='" + roomTF.getText() + "' AND "
				+ "idRoom not in " + "(SELECT idRoom " + "FROM Rental "
				+ "WHERE (date(arrivalDate)>='" + aDTF.getText()
				+ "' AND date(arrivalDate)<'" + dDTF.getText() + "') OR "
				+ "(date(arrivalDate)<='" + aDTF.getText()
				+ "' AND date(departureDate)>'" + aDTF.getText() + "'))";

		DatabaseGetSelect emptyRoom = new DatabaseGetSelect(textArea,
				EMPTYROOMQUERY);

		ResultSet rs = emptyRoom.getResult();
		if (rs != null) {
			try {
				if (!rs.next()) {
					return false;
				} else {
					return true;
				}
			} catch (SQLException e) {
				return false;
			} finally {
				emptyRoom.closeAll();
			}
		} else {
			return false;
		}

	}

	/**
	 * Inserts a customer
	 * 
	 * @return true if inserted, else false
	 */
	private boolean insertCustomerIfNotExist() {
		String INSERTCUSTOMER = 
				"INSERT INTO Customer(`idCustomer`, `lastName`," +
				" `firstName`, `city`, `country`, `email`)"
				+ "VALUES ('"
				+ idTF.getText()
				+ "', '"
				+ lNTF.getText()
				+ "', '"
				+ fNTF.getText()
				+ "', '"
				+ cityTF.getText()
				+ "', '"
				+ countryTF.getText() + "', '" + emailTF.getText() + "')";

		DatabaseInsert customerInsert = new DatabaseInsert(textArea,
				INSERTCUSTOMER);

		
		if (customerInsert.getConnectionState() && customerInsert.execute()) {
			customerInsert.closeConnection();
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Make rental
	 * 
	 * @return true of ok, else false
	 */
	private boolean makeRental() {

		String MAKERENTAL = 
				"INSERT INTO Rental(idRental, `idRoom`, " +
				"`idCustomer`, `arrivalDate`, `departureDate`, " +
				"`dayPrice`, `checkIn`, `checkOut`, `payed`) "
				+ "VALUES (default, '"
				+ roomTF.getText()
				+ "', '"
				+ idTF.getText()
				+ "', '"
				+ aDTF.getText()
				+ " 12:00:00', '"
				+ dDTF.getText()
				+ " 12:00:00', "
				+ priceTF.getText()
				+ ", "
				+ isCurrentDate()
				+ ", 0, 0)";

		DatabaseInsert makeRental = new DatabaseInsert(textArea, MAKERENTAL);
		
		if (makeRental.getConnectionState() == true && makeRental.execute()) {
			makeRental.closeConnection();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Check if text field date = current date
	 * 
	 * @return true of equal, else false
	 */
	private String isCurrentDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
		Date now = new Date();

		if (formatter.format(now).equalsIgnoreCase(aDTF.getText())) {
			return "1";
		} else {
			return "0";
		}

	}

	/**
	 * Validate date
	 * 
	 * @return true if date is valid, else false
	 */
	private boolean checkDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
		Date now = new Date();

		if (formatter.format(now).compareTo(aDTF.getText()) > 0
				|| formatter.format(now).compareTo(dDTF.getText()) > 0
				|| aDTF.getText().compareTo(dDTF.getText()) >= 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Add offer to the rental, if no offer is selected, default offer is added
	 * 
	 * @return true of added, else false
	 */
	private boolean makeOffer() {

		int[] indices = offerList.getSelectedIndices();
		if (indices.length == 0) {
			//add default offer
			addOffer(1);
			return true;
		} else {
			for (int i : indices) {
				addOffer(i+1);
			}
			return true;
		}
	}
	
	/**
	 * Inserts an offer into database
	 * 
	 * @param discountID the discount id
	 * @return true if inserted, else false
	 */
	private boolean addOffer(int discountID){
		
		String GETRENTALIDQUERY = 
				"SELECT idRental " +
				"FROM Rental " +
				"WHERE arrivalDate = '"+aDTF.getText()+ " 12:00:00' AND " +
						"idRoom = '"+roomTF.getText()+"' AND "+
						"idCustomer = '"+idTF.getText()+"'";
		
		DatabaseGetSelect getRentalID = new DatabaseGetSelect(textArea,
				GETRENTALIDQUERY);
		
		ResultSet rs = getRentalID.getResult();
		
		if(rs != null){
			try {
				rs.next();
				idRental = rs.getInt("idRental");
				getRentalID.closeAll();

				String  INSERTOFFERQUERY = 
						"INSERT INTO Offer " +
						"VALUES(default, "+idRental+", "+discountID+")";
				DatabaseInsert insertOffer = new DatabaseInsert(textArea, 
						INSERTOFFERQUERY);
				if (insertOffer.getConnectionState() == true && 
						insertOffer.execute()) {
					insertOffer.closeConnection();
					return true;
				} else {
					return false;
				}
				
						
			} catch (SQLException e) {
				return false;

			}
		}else{
			return false;
		}
		
	}
	
	/**
	 * Get discounts names from database. Used to populate the offer list
	 * 
	 * @return the names of the discounts
	 */
	private String[] getDiscounts(){
		ArrayList<String> temp = new ArrayList<String>();
		String GETDISCOUNTSQUERY = 
				"SELECT name " +
				"FROM Discount";
		
		DatabaseGetSelect getDiscounts = new DatabaseGetSelect(textArea,
				GETDISCOUNTSQUERY);
		
		ResultSet rs = getDiscounts.getResult();
		
		if (rs != null) {
			try {
				while(rs.next()){
					temp.add(rs.getString("name"));
				}
			} catch (SQLException e) {
				return null;
			}finally{
				getDiscounts.closeAll();
			}
		}
		String[] type = new String[temp.size()];
		for(int i = 0;i<temp.size();i++){
			type[i] = temp.get(i);
		}
		return type;
	}
	
	/**
	 * Inserts default service into database for this rental
	 * 
	 * @return true if inserted, else false
	 */
	private boolean addDefaultService(){
		
		String  INSERTORDERQUERY = 
				"INSERT INTO Trade " +
				"VALUES(default, 1, "+idRental+", default)";
		
		
		DatabaseInsert insertOrder = new DatabaseInsert(textArea, 
				INSERTORDERQUERY);
				
		if (insertOrder.getConnectionState() == true && insertOrder.execute()) {
			insertOrder.closeConnection();
			return true;
		} else {
			return false;
		}
		
	}

	// Variables declaration - do not modify
	private javax.swing.JTextField aDTF;
	private javax.swing.JButton bookButton;
	private javax.swing.JTextField cityTF;
	private javax.swing.JTextField countryTF;
	private javax.swing.JTextField dDTF;
	private javax.swing.JTextField emailTF;
	private javax.swing.JTextField fNTF;
	private javax.swing.JTextField idTF;
	private javax.swing.JLabel firstNameLabel;
	private javax.swing.JLabel priceLabel;
	private javax.swing.JLabel lastNameLabel;
	private javax.swing.JLabel customerIDLabel;
	private javax.swing.JLabel cityLabel;
	private javax.swing.JLabel countryLabel;
	private javax.swing.JLabel emailLabel;
	private javax.swing.JLabel roomLabel;
	private javax.swing.JLabel arrivalDateLabel;
	private javax.swing.JLabel departureDateLabel;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTextField lNTF;
	@SuppressWarnings("rawtypes")
	private javax.swing.JList offerList;
	private javax.swing.JTextField priceTF;
	private javax.swing.JTextField roomTF;
	// End of variables declaration
}
