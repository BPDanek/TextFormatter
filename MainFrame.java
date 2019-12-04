package formatterGui;


import java.awt.BorderLayout; // abstract window toolkit
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

// the frame that will be the GUI for our app
public class MainFrame extends JFrame {
	
	public DetailsPanel detailsPanel;
	private ErrorPanel errorPanel;
	Scanner inputStream = null;
	
	public static String currentText = "";
	
	public String noFormat(String filePath) {
		Path pathpath = Paths.get(filePath);
		String content;
		try {
			content = Files.readString(pathpath, StandardCharsets.US_ASCII);
			return content;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error opening the file " + filePath);
	    	return "";
		}
	}

	public String doFormat(String filePath) {
		ArrayList<TextSnippet> alist = new ArrayList<TextSnippet>();
		
		Parser aparser = new Parser();
		  
		//TextSnippet elements - justify, lineLength, wrapEnabled, doublespace, isTitle, indent, numColumns, blanks, false, text)
		alist = aparser.ParseFile(filePath);
		
		String content = Formatter.getFormattedText(alist);
		
		return content;
	}
	
	private static void writeToDir(String data, String path) {
        try {
            Files.write(Paths.get(path), data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	private static String open_selected() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		int status = fileChooser.showOpenDialog(null);
		String parent = "blank";
		String name = "blank";
		if (status == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			parent = selectedFile.getParent();
			name = selectedFile.getName();
		} else if (status == JFileChooser.CANCEL_OPTION) {
			System.out.println("Canceled");
		}
		
		String returnString = (parent + "/" + name);
		
		if (parent == "blank" || name == "blank") {
			returnString = "nonexistantdirectory";
		}
		
		System.out.println(returnString);
		return returnString;
	}
	
	public MainFrame(String title, String path) {
		super(title); // inherit constructor behavior
		
		// make content frame
		Container appVis = getContentPane();
		
		Border border = BorderFactory.createLineBorder(Color.WHITE);
		
		JTextArea formatTextArea = new JTextArea(20, 80);
		
		errorPanel = new ErrorPanel(50);
		
		detailsPanel = new DetailsPanel(30); // param is height
		detailsPanel.addDetailListener(new DetailListener() {
			

			String filePath = "";
			
			@Override
			public void detailEventOccurred(DetailEvent event) {
				// TODO Auto-generated method stub
				String commandName = event.getName();
				if (commandName == "open") {
					filePath = open_selected(); 
					detailsPanel.writeContent(noFormat(filePath));
					currentText = detailsPanel.getContent();
					formatTextArea.setText(currentText);
				}
				else if (commandName == "save") {
					filePath = open_selected();
					if (filePath != "nonexistantdirectory") {
						writeToDir(currentText, filePath);
					}
				}
				else if (commandName == "format") {
					detailsPanel.writeContent(doFormat(filePath));
					currentText = detailsPanel.getContent();
					formatTextArea.setText(currentText);
				}
				
			}
		});
			
		JScrollPane scrollPane = new JScrollPane(formatTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		appVis.add(scrollPane, BorderLayout.CENTER);
		
		
		Font font = new Font(Font.MONOSPACED, Font.PLAIN, 13);
		
		formatTextArea.setFont(font);
		formatTextArea.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(1, 5, 5, 10)));
		formatTextArea.setAlignmentX(CENTER_ALIGNMENT);
		formatTextArea.setEditable(false);
		
		appVis.add(scrollPane, BorderLayout.CENTER);
		appVis.add(errorPanel, BorderLayout.SOUTH);
		appVis.add(detailsPanel, BorderLayout.NORTH);
		
		formatTextArea.append("");
	}
}
