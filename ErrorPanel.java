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

	Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);

	public ErrorPanel(int height) {
		
		Dimension size = getPreferredSize();
		size.height = height; //size in pixels, can be tuned
		setPreferredSize(size);
		
		JTextArea errorTextArea = new JTextArea("Errors: ", 3, 10);
		errorTextArea.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(1, 5, 5, 10)));
		errorTextArea.setEditable(false);
//		errorTextArea.setAlignmentX(CENTER_ALIGNMENT);
		
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
