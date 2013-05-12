package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JOptionPane;

import gui.Window;

public class Main {
	
	public static String URL, USER, PASSWORD;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//properties
		Properties properties = new Properties();
		
		
		//load properties file
		FileInputStream in = null;

        try{
            in = new FileInputStream("database.properties");
            properties.load(in);
        }catch (FileNotFoundException ex) {
        	JOptionPane.showMessageDialog(null, "Property file not found",
					"Property File Error", JOptionPane.ERROR_MESSAGE);
        }catch (IOException ex) {
        	JOptionPane.showMessageDialog(null, "Property reading problem",
					"Property Read Error", JOptionPane.ERROR_MESSAGE);
        }finally{
            try {
                 if (in != null) {
                     in.close();
                 }
            } catch (IOException ex) {

            }
        }

        //get database properties
        URL = properties.getProperty("db.url");
        USER = properties.getProperty("db.user");
       	PASSWORD = properties.getProperty("db.passwd");
        
       	//main window
		Window application = new Window();
		
		//settings
		application.setSize(800, 300);
		application.setVisible(true);
		application.setResizable(false);
		// application.pack();
	}

}
