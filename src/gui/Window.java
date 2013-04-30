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

public class Window extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton bookButton, freeRoomButton, checkIn, checkOut, paymentButton,
					commandButton, exitButton;
	private JTextArea textArea;
	private JTextField commandTextField;

	public Window(){
		super("Hotel Database");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		//action handler
		ActionHandler actionHandler = new ActionHandler();
		
		//key handler
		KeyHandler keyHandler = new KeyHandler();
		
		//buttons
		JToolBar toolBar = new JToolBar();
		
		bookButton = new JButton("Book");
		toolBar.add(bookButton);
		
		freeRoomButton = new JButton("Free Rooms");
		toolBar.add(freeRoomButton);
		
		checkIn = new JButton("Check In");
		toolBar.add(checkIn);
		
		checkOut = new JButton("Check Out");
		toolBar.add(checkOut);
		
		paymentButton = new JButton("Payment");
		toolBar.add(paymentButton);
				
		commandButton = new JButton("Command");
		commandButton.addActionListener(actionHandler);
		
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
				
			}
		});
		toolBar.add(exitButton);
		
		//text area
		textArea = new JTextArea(20, 60);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setAutoscrolls(true);
		
		//command text field
		JPanel commandPanel = new JPanel();
		commandTextField = new JTextField("", 60);
		commandTextField.addKeyListener(keyHandler);
		commandPanel.add(commandTextField);
		commandPanel.add(commandButton);
		
		//add
		this.add(toolBar, BorderLayout.NORTH);
		this.add(scrollPane, BorderLayout.CENTER);
		this.add(commandPanel, BorderLayout.SOUTH);
		
	
		
	}
	
	private void insertQuery(){
		@SuppressWarnings("unused")
		DatabaseInsert query = new DatabaseInsert(textArea, 
				commandTextField.getText());
	}
	
	private void selectQuery(){
		@SuppressWarnings("unused")
		DatabaseSelect query = new DatabaseSelect(textArea, 
				commandTextField.getText());
	}
	
	private void updateQuery(){
		@SuppressWarnings("unused")
		DatabaseUpdate query = new DatabaseUpdate(textArea, 
				commandTextField.getText());
	}
	
	private void deleteQuery(){
		@SuppressWarnings("unused")
		DatabaseDelete query = new DatabaseDelete(textArea, 
				commandTextField.getText());
	}
	
	private class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==commandButton){
				
				if(commandTextField.getText().substring(0, 6).
						equalsIgnoreCase("insert")){
					insertQuery();
				}else if(commandTextField.getText().substring(0, 6).
						equalsIgnoreCase("select")){
					selectQuery();
				}else if(commandTextField.getText().substring(0, 6).
						equalsIgnoreCase("update")){
					updateQuery();
				}else if(commandTextField.getText().substring(0, 6).
						equalsIgnoreCase("delete")){
					deleteQuery();
				}
			}
			
		}
		
	}
	
	private class KeyHandler implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				
				if(commandTextField.getText().substring(0, 6).
						equalsIgnoreCase("insert")){
					insertQuery();
				}else if(commandTextField.getText().substring(0, 6).
						equalsIgnoreCase("select")){
					selectQuery();
				}else if(commandTextField.getText().substring(0, 6).
						equalsIgnoreCase("update")){
					updateQuery();
				}else if(commandTextField.getText().substring(0, 6).
						equalsIgnoreCase("delete")){
					deleteQuery();
				}
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
		
		
}
