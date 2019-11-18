package formatterGui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class DetailsPanel extends JPanel {
	
	Border border = BorderFactory.createLineBorder(Color.WHITE);
	
	public DetailsPanel(int height) {
		
		Dimension size = getPreferredSize();
		size.height = height; //size in pixels, can be tuned
		setPreferredSize(size);
		
		setBorder(BorderFactory.createTitledBorder(""));
		
		final JButton formatButton = new JButton("Format");
		
		final JButton openButton = new JButton("Open");
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(openButton, gbc);
		
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(formatButton, gbc);
		
	}
	
}
