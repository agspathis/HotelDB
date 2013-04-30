package main;

import gui.Window;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Window application = new Window();
		application.setSize(800, 300);
		application.setVisible(true);
		application.setResizable(false);
		//application.pack();
	}

}
