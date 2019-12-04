package formatterGui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

//importing javax.swing: https://stackoverflow.com/questions/14181142/eclipse-doesnt-find-javax-swing
//how to set up directory, see SOLVED at the end of initial post: https://www.eclipse.org/forums/index.php/t/369033/
//Swing Tutorial Series: https://www.youtube.com/watch?v=jUEOWVjnIR8
// XXX cannot be resolved: https://stackoverflow.com/questions/4322893/eclipse-error-the-import-xxx-cannot-be-resolved

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
				
				String filePath = ""; //"/Users/denbanek/eclipse-workspace/FormattingTool/src/formatterGui/testfile3.txt"
				
				JFrame frame = new MainFrame("Formatter", filePath); // open a frame with a title in constructor
				
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // terminate program when window is closed
				frame.pack();
				
//				frame.setPreferredSize(new Dimension(651, 500)); // set size
				frame.setVisible(true); // show frame
							
			}
		});
	}
}
