package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import database.DatabaseSelect;
import database.DatabaseUpdate;

/**
 * This class implements the payment actions with a gui interface
 * 
 * @author 
 */
public class PaymentWindow extends javax.swing.JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textArea;
	
	/**
     * Creates new form PaymentWindow
     */
    public PaymentWindow(JTextArea textArea) {
    	this.textArea = textArea;
        initComponents();
    }

    /**
	 * Window initialization with Netbeans
	 */                        
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        roomLabel = new javax.swing.JLabel();
        roomTF = new javax.swing.JTextField();
        payedButton = new javax.swing.JButton();
        fromLabel = new javax.swing.JLabel();
        fromTF = new javax.swing.JTextField();
        toLabel = new javax.swing.JLabel();
        toTF = new javax.swing.JTextField();
        currentInButton = new javax.swing.JButton();
        incomesButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Payment");
        setResizable(false);

        roomLabel.setText("Room");

        roomTF.setColumns(5);

        payedButton.setText("Payed");
        payedButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				paymentAction();
				
			}
		});

        fromLabel.setText("From");

        fromTF.setColumns(11);

        toLabel.setText("To");

        toTF.setColumns(11);

        currentInButton.setText("Current In");
        currentInButton.setToolTipText("shows current in unpayed rooms");
        currentInButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentlyInAction();
				
			}
		});

        incomesButton.setText("Incomes");
        incomesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				incomesAction();
				
			}
		});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fromTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fromLabel))
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(toLabel)
                                    .addComponent(toTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(roomLabel)
                                .addGap(33, 33, 33)
                                .addComponent(roomTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(payedButton))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(incomesButton)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(currentInButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roomLabel)
                    .addComponent(roomTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(payedButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fromLabel)
                    .addComponent(toLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fromTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(incomesButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(currentInButton)
                .addContainerGap())
        );

        pack();
    }                       

    /**
     * Room payment action
     */
    private void paymentAction(){
    	// check text field validation
		if (roomTF.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Fill room's text field",
					"Empty text field", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		if(roomPaymentState()){
			if(servicesPaymentState()){
				// do updates
				updatePayed();
			}
		}
		
		textArea.append(Window.SEPERATOR);
    }
    
    /**
     * Get room payment state
     * 
     * @return true of no error occurred, else false
     */
    private boolean roomPaymentState(){
    	
    	String ROOMPAYMENTSTATEQUERY = 
    		"SELECT firstName, lastName, idRoom, dayPrice, " +
    		"dateDiff(departureDate,arrivalDate) AS Days, " +
    		"dateDiff(departureDate,arrivalDate)*dayPrice AS 'Total Room Price', " +
    		"TRUNCATE(sum(discount),2) AS 'Total Discount', " +
    		"TRUNCATE(dateDiff(departureDate,arrivalDate)*dayPrice*(1-sum(discount)),2) AS 'Total' " +
    		"FROM ((Rental NATURAL JOIN Customer) NATURAL JOIN " +
    		"(Offer NATURAL JOIN Discount)) " +
    		"WHERE idRoom = '"+roomTF.getText()+"' AND checkOut = 0 AND " +
    		"checkIn = 1 AND payed = 0";
    	
    	
    	DatabaseSelect roomPayment = new DatabaseSelect(textArea, 
    			ROOMPAYMENTSTATEQUERY);
    	
    	if(roomPayment.getConnectionState()==true && roomPayment.execute()){
    		roomPayment.closeConnection();
        	return true;
    	}else{
    		JOptionPane.showMessageDialog(null, "Rooms payment unable",
					"Room error", JOptionPane.ERROR_MESSAGE);
    		roomPayment.closeConnection();
    		return false;
    	}
    }
    
    /**
     * Get services payment state
     * 
     * @return true if there is no problem, else false
     */
    private boolean servicesPaymentState(){
        String SERVICEPAYMENTSTATEQUERY =
        "SELECT firstName, lastName, idRoom, " +
        "sum(Service.price) AS 'Total Service Price' " +
        "FROM ((Rental NATURAL JOIN Customer) NATURAL JOIN " +
        "(Trade NATURAL JOIN Service)) " +
        "WHERE idRoom = '"+roomTF.getText()+"' AND checkOut = 0 AND " +
        "checkIn = 1 AND payed = 0";
       
        DatabaseSelect servicePayment = new DatabaseSelect(textArea,
        SERVICEPAYMENTSTATEQUERY);
       
        if((servicePayment.getConnectionState()==true && servicePayment.execute())){
        	servicePayment.closeConnection();
        	return true;
        }else{
        	JOptionPane.showMessageDialog(null, "Service payment unable",
        				"Service error", JOptionPane.ERROR_MESSAGE);
        	servicePayment.closeConnection();
        return false;
        }
    }
    

    /**
     * Updates payed field of a rental
     * 
     * @return true if updated, else false
     */
    private boolean updatePayed(){
    	
		
		String PAYEDUPDATEQUERY = 
				"UPDATE Rental NATURAL JOIN Trade " +
				"SET Trade.payed = 1, Rental.payed = 1 " +
				"WHERE idRoom = '"+roomTF.getText()+"' AND " +
				"checkOut = 0 AND checkIn = 1 AND " +
				"date(arrivalDate)<=current_date() AND " +
				"date(departureDate)>=current_date()";

		DatabaseUpdate payedUpdate = new DatabaseUpdate(textArea, 
				PAYEDUPDATEQUERY);
		
		if (!(payedUpdate.getConnectionState() == true
				&& payedUpdate.execute())) {
			//no error
			return false;
		} else{
			payedUpdate.closeConnection();
			return true;
		}
		
    }
    
    /**
     * Currently in state of payment. Shows those who are in and haven't payed yet.
     */
    private void currentlyInAction(){
    	String CURRENTPAYMENTSTATEQUERY = 
			"SELECT firstName, lastName, idRoom, dayPrice, " +
			"dateDiff(current_date(),arrivalDate) AS Days, " +
			"dateDiff(current_date(),arrivalDate)*dayPrice AS 'Total Room Price', " +
			"TRUNCATE(sum(discount),2) AS 'Total Discount', " +
			"TRUNCATE(dateDiff(current_date(),arrivalDate)*dayPrice*(1-sum(discount)), 2) AS 'Total' " +
    		"FROM ((Rental NATURAL JOIN Customer) NATURAL JOIN " +
    		"(Offer NATURAL JOIN Discount)) " +
			"WHERE checkOut = 0 AND checkIn = 1 AND payed = 0 " +
			"GROUP BY idRoom";
    	
    	DatabaseSelect currentState = new DatabaseSelect(textArea, 
    			CURRENTPAYMENTSTATEQUERY);
    	
    	if(currentState.getConnectionState()==true && currentState.execute()){
    		currentState.closeConnection();
    	}else{
    		JOptionPane.showMessageDialog(null, "Currently in unable",
					"Currently in error", JOptionPane.ERROR_MESSAGE);
    		currentState.closeConnection();
    	}
    	
    	textArea.append(Window.SEPERATOR);
    }
    
    /**
     * Shows the incomes for a given period of time
     */
    private void incomesAction(){
    	//check text field validation
    	if (fromTF.getText().length() != 10 || toTF.getText().length() != 10 ||
				!checkDate()) {
			JOptionPane.showMessageDialog(null, "Invalid Date (YYYY-MM-DD)",
					"Date error", JOptionPane.ERROR_MESSAGE);
			return;
		}
    	
    	String INCOMESQUERY = 
			"SELECT firstName, lastName, idRoom, dayPrice, " +
			"dateDiff(departureDate,arrivalDate) AS Days, " +
	    	"dateDiff(departureDate,arrivalDate)*dayPrice AS 'Total Room Price', " +
	    	"TRUNCATE(sum(discount),2) AS 'Total Discount', " +
	    	"TRUNCATE(dateDiff(departureDate,arrivalDate)*dayPrice*(1-sum(discount)), 2) AS 'Total' " +
	    	"FROM ((Rental NATURAL JOIN Customer) NATURAL JOIN " +
	    	"(Offer NATURAL JOIN Discount)) " +
	    	"WHERE payed = 1 AND date(arrivalDate)>='"+fromTF.getText()+"' AND " +
	    	"date(departureDate)<='"+toTF.getText()+"' " +
	    	"GROUP BY lastName, firstName, idRoom";
    	
    	DatabaseSelect incomesQuery = new DatabaseSelect(textArea, INCOMESQUERY);
    	
    	if(!(incomesQuery.getConnectionState()==true && incomesQuery.execute())){
    		JOptionPane.showMessageDialog(null, "Incomes calculation unable",
					"Incomes error", JOptionPane.ERROR_MESSAGE);
    		incomesQuery.closeConnection();
    	}
    	
    	textArea.append(Window.SEPERATOR);
    }
    
    /**
     * Date validation
     * 
     * @return true if date valid, else false
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
    private javax.swing.JButton currentInButton;
    private javax.swing.JButton incomesButton;
    private javax.swing.JLabel roomLabel;
    private javax.swing.JLabel fromLabel;
    private javax.swing.JLabel toLabel;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton payedButton;
    private javax.swing.JTextField roomTF;
    private javax.swing.JTextField toTF;
    // End of variables declaration                   
}
