/*
 * Author: Benjamin Danek
 * Class ID: CSE 360, fall 2019
 * Assignment: Formatter Tool, final project 
 */

package formatterGui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/*
 * This class is the entry point of the program, it starts a runnable swing thread and calls the MainFrame, a class
 * which handles the GUI operations. 
 */

public class GUI {
		
	// entry point of program
	public static void main(String[] args) {
		
//		String content = "\n\tArtificial intelligence is often used as an umbrella term to \n"
//					+ "cover various applications of machine learning. It is commonly applied to  \n"
//					+ "describe intelligent agents that take inputs from their environment and attempt  \n"
//					+ "to achieve a  cognitive outcome as a human would. This requires a certain \n"
//					+ "level of programming, training, or applied problem-solving to become\n "
//					+ "practical.";
			
		// Swing apps need their own threading, this is the way to do that
		// this runnable thread entails the program operations
		SwingUtilities.invokeLater(new Runnable() {
	
			public void run() {
				
				String filePath = "";
				
				JFrame frame = new MainFrame("Formatter", filePath); // open a frame with a title in constructor
				
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // terminate program when window is closed
				frame.pack();
				
				frame.setVisible(true); // show frame
							
			}
		});
	}
}
