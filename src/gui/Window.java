package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import database.DatabaseDelete;
import database.DatabaseInsert;
import database.DatabaseSelect;
import database.DatabaseUpdate;

/**
 * This is the main window
 * 
 * @author
 *
 */
public class Window extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private JButton bookButton, emptyRoomButton, checkInButton, checkOutButton,
	serviceButton, paymentButton, exitButton;
    private JTextArea textArea;
    private JTextField commandTextField;
    public static final String SEPERATOR = "******************************\n";

    public Window(int width, int heigth) {
	super("Hotel Database");
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setSize(width, heigth);
	this.setLayout(new BorderLayout());

	// key handler
	KeyHandler keyHandler = new KeyHandler();

	// buttons
	JToolBar toolBar = new JToolBar();

	bookButton = new JButton("Book");
	bookButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
		    ReservationWindow reservationWindow = new ReservationWindow(
										textArea);
		    reservationWindow.setVisible(true);

		}
	    });
	toolBar.add(bookButton);

	emptyRoomButton = new JButton("Empty Rooms");
	emptyRoomButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
		    EmptyRoomsWindow freeRommsWindow = new EmptyRoomsWindow(textArea);
		    freeRommsWindow.setVisible(true);
		}
	    });
	toolBar.add(emptyRoomButton);

	checkInButton = new JButton("Check In");
	checkInButton.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
		    CheckInWindow checkInWindow = new CheckInWindow(textArea);
		    checkInWindow.setVisible(true);
		}
	    });
	toolBar.add(checkInButton);

	checkOutButton = new JButton("Check Out");
	checkOutButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
		    CheckOutWindow checkOutWindow = new CheckOutWindow(textArea);
		    checkOutWindow.setVisible(true);
		}
	    });
	toolBar.add(checkOutButton);
		
	serviceButton = new JButton("Service");
	serviceButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
		    ServiceWindow serviceWindow = new ServiceWindow(textArea);
		    serviceWindow.setVisible(true);
		}
	    });
	toolBar.add(serviceButton);
		
	paymentButton = new JButton("Payment");
	paymentButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    PaymentWindow paymentWindow = new PaymentWindow(textArea);
		    paymentWindow.setVisible(true);
		}
	    });
	toolBar.add(paymentButton);
		
	exitButton = new JButton("Exit");
	exitButton.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
		    System.exit(0);

		}
	    });
	toolBar.add(exitButton);

	// text area
	textArea = new JTextArea(20, 70);
	textArea.setEditable(false);
	textArea.setLineWrap(true);
	textArea.setWrapStyleWord(true);
	JScrollPane scrollPane = new JScrollPane(textArea);
	scrollPane.setAutoscrolls(true);

	// command text field
	JPanel commandPanel = new JPanel();
	commandTextField = new JTextField("", 70);
	commandTextField.addKeyListener(keyHandler);
	commandPanel.add(commandTextField);
		

	// add
	this.add(toolBar, BorderLayout.NORTH);
	this.add(scrollPane, BorderLayout.CENTER);
	this.add(commandPanel, BorderLayout.SOUTH);

    }

    /**
     * Insert query action
     */
    private void insertQuery() {
	DatabaseInsert query = new DatabaseInsert(textArea,
						  commandTextField.getText());
	if (query.getConnectionState()) {
	    query.execute();
	}
    }

    /**
     * Select query action
     */
    private void selectQuery() {
	DatabaseSelect query = new DatabaseSelect(textArea,
						  commandTextField.getText());
	if (query.getConnectionState()) {
	    query.execute();
	}
    }

    /**
     * Update query action
     */
    private void updateQuery() {
	DatabaseUpdate query = new DatabaseUpdate(textArea,
						  commandTextField.getText());
	if (query.getConnectionState()) {
	    query.execute();
	}
    }

    /**
     * Delete query action
     */
    private void deleteQuery() {
	DatabaseDelete query = new DatabaseDelete(textArea,
						  commandTextField.getText());
	if (query.getConnectionState()) {
	    query.execute();
	}
    }

    /**
     * Key handler class
     * 
     * @author 
     */
    private class KeyHandler implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
	    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				
		if(commandTextField.getText().length()<6){
		    return;
		}
				
		if (commandTextField.getText().substring(0, 6)
		    .equalsIgnoreCase("insert")) {
		    insertQuery();
		} else if (commandTextField.getText().substring(0, 6)
			   .equalsIgnoreCase("select")) {
		    selectQuery();
		} else if (commandTextField.getText().substring(0, 6)
			   .equalsIgnoreCase("update")) {
		    updateQuery();
		} else if (commandTextField.getText().substring(0, 6)
			   .equalsIgnoreCase("delete")) {
		    deleteQuery();
		}
	    }

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

    }
}
