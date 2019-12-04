package formatterGui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.event.EventListenerList;
import javax.swing.filechooser.*; 

public class DetailsPanel extends JPanel {
	
	private EventListenerList listenerList = new EventListenerList();
	
	Border border = BorderFactory.createLineBorder(Color.WHITE);
	JButton formatButton;
	JButton openButton;
	JButton saveButton;
	
	private String content = "";
	
	String getContent() {
		return this.content;
	}
	
	public void writeContent(String content) {
		// TODO Auto-generated method stub
		this.content = content;
	}
	
	public DetailsPanel(int height) {
		
		Dimension size = getPreferredSize();
		size.height = height; //size in pixels, can be tuned
		setPreferredSize(size);
		
		setBorder(BorderFactory.createTitledBorder(""));
		
		formatButton = new JButton("Format");
		openButton = new JButton("Open");
		saveButton = new JButton("Save")  ;
		
		
		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String buttonName = "none";
				
				if (e.getSource() == formatButton) {
					buttonName = "format";
				}
				else if (e.getSource() == openButton) {
					buttonName = "open";
				}
				else if (e.getSource() == saveButton) {
					buttonName = "save";
				}
								
				fireDetailEvent(new DetailEvent(this, buttonName));
			}
		});
		
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String buttonName = "none";
				
				if (e.getSource() == formatButton) {
					buttonName = "format";
				}
				else if (e.getSource() == openButton) {
					buttonName = "open";
				}
				else if (e.getSource() == saveButton) {
					buttonName = "save";
				}
								
				fireDetailEvent(new DetailEvent(this, buttonName));
			}
		});
		
		formatButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String buttonName = "none";
				
				if (e.getSource() == formatButton) {
					buttonName = "format";
				}
				else if (e.getSource() == openButton) {
					buttonName = "open";
				}
				else if (e.getSource() == saveButton) {
					buttonName = "save";
				}
								
				fireDetailEvent(new DetailEvent(this, buttonName));
			}
		});
		
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(openButton, gbc);
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(saveButton, gbc);
		
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(formatButton, gbc);
		
	}
	public JButton getOpen() {
		return openButton;
	}
	
	public JButton getFormat() {
		return formatButton;
	}
	
	public JButton getSave() {
		return saveButton;
	}
	
	public void fireDetailEvent(DetailEvent event) {
		Object[] listeners = listenerList.getListenerList();
		
		for (int i = 0; i < listeners.length; i = i + 2) {
			if(listeners[i] == DetailListener.class) {
				((DetailListener)listeners[i + 1]).detailEventOccurred(event);
			}
		}
	}
	
	public void addDetailListener(DetailListener listener) {
		listenerList.add(DetailListener.class, listener);
	}


}
