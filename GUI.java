package formatterGui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class GUI {
	
	
		
	public static void main(String[] args) {
		
//		String content = "\n\tArtificial intelligence is often used as an umbrella term to \n"
//					+ "cover various applications of machine learning. It is commonly applied to  \n"
//					+ "describe intelligent agents that take inputs from their environment and attempt  \n"
//					+ "to achieve a  cognitive outcome as a human would. This requires a certain \n"
//					+ "level of programming, training, or applied problem-solving to become\n "
//					+ "practical.";
			
		// Swing apps need their own threading, this is the way to do that
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
