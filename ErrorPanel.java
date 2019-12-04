/*
 * Author: Benjamin Danek, Team 24
 * Class ID: CSE 360, fall 2019
 * Assignment: Formatter Tool, final project 
 */
package formatterGui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

// adding this class causes every import to go red. Need to remove current JRE and re-add it
// possibly re-do order and export.

public class ErrorPanel extends JPanel {

	//Setting the boarder to gray
	Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);

	public ErrorPanel(int height) {
		
		//Setting the size of the panel
		Dimension size = getPreferredSize();
		size.height = height; //size in pixels, can be tuned
		setPreferredSize(size);
		
		//Text Area of the Error panel
		JTextArea errorTextArea = new JTextArea("Errors: ", 3, 10);
		errorTextArea.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(1, 5, 5, 10)));
		errorTextArea.setEditable(false);
//		errorTextArea.setAlignmentX(CENTER_ALIGNMENT);
		
		//Setting layout to the grid
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.weightx = 10;
		gbc.weighty = 0.5;
		
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(errorTextArea, gbc);
	}
}
