package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import database.DatabaseGetSelect;
import database.DatabaseInsert;


/**
 * This class implements the service ordering action with a gui interface
 * 
 * @author 
 */
public class ServiceWindow extends javax.swing.JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JTextArea textArea;
    private int idService, idRental;
    /**
     * Creates new form ServiceWindow
     */
    public ServiceWindow(JTextArea textArea) {
    	this.textArea = textArea;
        initComponents();
    }

    /**
     * Window initialization with Netbeans
     */                           
    private void initComponents() {
    	setTitle("Service");
    	
        roomLabel = new javax.swing.JLabel();
        serviceLabel = new javax.swing.JLabel();
        roomTF = new javax.swing.JTextField();
        serviceNameTF = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        addButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    serviceAction();
				
		}
	    });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        roomLabel.setText("Room");

        serviceLabel.setText("Service");

        roomTF.setColumns(10);

        serviceNameTF.setColumns(10);

        addButton.setText("Add");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup
	    (
	     layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	     .addGroup(layout.createSequentialGroup()
		       .addGap(64, 64, 64)
		       .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
				 .addComponent(addButton)
				 .addGroup(layout.createSequentialGroup()
					   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						     .addComponent(roomLabel)
						     .addComponent(serviceLabel))
					   .addGap(50, 50, 50)
					   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						     .addComponent(serviceNameTF,
								   javax.swing.GroupLayout.PREFERRED_SIZE,
								   javax.swing.GroupLayout.DEFAULT_SIZE,
								   javax.swing.GroupLayout.PREFERRED_SIZE)
						     .addComponent(roomTF,
								   javax.swing.GroupLayout.PREFERRED_SIZE,
								   javax.swing.GroupLayout.DEFAULT_SIZE,
								   javax.swing.GroupLayout.PREFERRED_SIZE))))
		       .addContainerGap(48, Short.MAX_VALUE))
	     );
        layout.setVerticalGroup
	    (
	     layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	     .addGroup(layout.createSequentialGroup()
		       .addGap(37, 37, 37)
		       .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
				 .addComponent(roomLabel)
				 .addComponent(roomTF,
					       javax.swing.GroupLayout.PREFERRED_SIZE,
					       javax.swing.GroupLayout.DEFAULT_SIZE,
					       javax.swing.GroupLayout.PREFERRED_SIZE))
		       .addGap(25, 25, 25)
		       .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
				 .addComponent(serviceLabel)
				 .addComponent(serviceNameTF,
					       javax.swing.GroupLayout.PREFERRED_SIZE,
					       javax.swing.GroupLayout.DEFAULT_SIZE,
					       javax.swing.GroupLayout.PREFERRED_SIZE))
		       .addGap(36, 36, 36)
		       .addComponent(addButton)
		       .addContainerGap(33, Short.MAX_VALUE))
	     );

        pack();
    }                      

    /**
     * Service action
     */
    private void serviceAction(){
    	//check text field validation
    	if (roomTF.getText().length() == 0||serviceNameTF.getText().length() == 0) {
	    JOptionPane.showMessageDialog(null, "Fill text fields",
					  "Empty text field", JOptionPane.INFORMATION_MESSAGE);
	    return;
	}
    	
    	if(checkIfRentExist()){
	    if(checkIfServiceExist()){
		if(!makeOrder()){
		    JOptionPane.showMessageDialog(null, "Unable to order",
						  "Order error", JOptionPane.ERROR_MESSAGE);
		}
	    }else{// service don't exist
		JOptionPane.showMessageDialog(null, "Service don't exist",
					      "Service existance", JOptionPane.INFORMATION_MESSAGE);
	    }
    	}else{//rent don't exist
	    JOptionPane.showMessageDialog(null, "Rental don't exist",
					  "Rental existance", JOptionPane.INFORMATION_MESSAGE);
    	}
    	
    	textArea.append(Window.SEPERATOR);
    }
    
    /**
     * Check if given rent exist
     * 
     * @return true if exist, else false
     */
    private boolean checkIfRentExist() {
	String RENTEXISTQUERY = 
	    "SELECT idRental " + 
	    "FROM Rental " +
	    "WHERE idRoom = '" + roomTF.getText() + "' AND "+
	    "checkIn = '1' AND checkOut = '0'";

	DatabaseGetSelect rentExist = new DatabaseGetSelect(textArea,
							    RENTEXISTQUERY);

	ResultSet rs = rentExist.getResult();
	if (rs != null) {
	    try {
		if (!rs.next()) {
		    return false;
		} else {
		    this.idRental = rs.getInt("idRental");
		    return true;
		}
	    } catch (SQLException e) {
		return false;
	    } finally {
		rentExist.closeAll();
	    }
	} else {
	    return false;
	}

    }
    
    /**
     * Check if given service exist
     * 
     * @return true if exist, else false
     */
    private boolean checkIfServiceExist() {
	String SERVICEEXISTQUERY = 
	    "SELECT idService " + 
	    "FROM Service " +
	    "WHERE name = '" + serviceNameTF.getText() + "'";

	DatabaseGetSelect serviceExist = new DatabaseGetSelect(textArea,
							       SERVICEEXISTQUERY);

	ResultSet rs = serviceExist.getResult();
	if (rs != null) {
	    try {
		if (!rs.next()) {
		    return false;
		} else {
		    this.idService = rs.getInt("idService");
		    return true;
		}
	    } catch (SQLException e) {
		return false;
	    } finally {
		serviceExist.closeAll();
	    }
	} else {
	    return false;
	}

    }
    
    /**
     * Insert into Trade the order
     * 
     * @return true if inserted, else false
     */
    private boolean makeOrder(){
	String  INSERTORDERQUERY = 
	    "INSERT INTO Trade " +
	    "VALUES(default, "+idService+", "+idRental+", default)";
		
		
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
    private javax.swing.JButton addButton;
    private javax.swing.JLabel roomLabel;
    private javax.swing.JLabel serviceLabel;
    private javax.swing.JTextField roomTF;
    private javax.swing.JTextField serviceNameTF;
    // End of variables declaration                   
}

