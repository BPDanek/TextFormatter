package formatterGui;

import java.awt.BorderLayout; // abstract window toolkit
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.border.Border;

// the frame that will be the GUI for our app
public class MainFrame extends JFrame {
	
	private DetailsPanel detailsPanel;
	private ErrorPanel errorPanel;
	
	public MainFrame(String title, String content) {
		super(title); // inherit constructor behavior
		
		//set layout manager
		setLayout(new BorderLayout());
		
		// make content frame
		Container appVis = getContentPane();
		
		Border border = BorderFactory.createLineBorder(Color.WHITE);
		
		detailsPanel = new DetailsPanel(30); // param is height
		errorPanel = new ErrorPanel(50);
		
		JTextArea formatTextArea = new JTextArea("Author: J. Apple\nDate: 1.11.89\n", 10, 1);
		formatTextArea.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(1, 5, 5, 10)));
		formatTextArea.setAlignmentX(CENTER_ALIGNMENT);
		
		
		appVis.add(formatTextArea, BorderLayout.CENTER);
		appVis.add(errorPanel, BorderLayout.SOUTH);
		appVis.add(detailsPanel, BorderLayout.NORTH);
		
		
		// add sample content to textArea
		formatTextArea.append(content);
	}
}
