package formatterGui;


import java.awt.BorderLayout; // abstract window toolkit
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
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
	
	public String noFormat(String filePath) {
//		try {
//	    	  
//	    	  inputStream = new Scanner(new File(filePath));
//	      
//	      } catch (FileNotFoundException e) {
//	    	  
//	    	  System.out.println("Error opening the file " + filPath);
//	    	  return "";
//	    	  
//	      }
		
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
		//System.out.println("calling parser");
		ArrayList<TextSnippet> alist = new ArrayList<TextSnippet>();
		
		Parser aparser = new Parser();
		  
		//TextSnippet elements - justify, lineLength, wrapEnabled, doublespace, isTitle, indent, numColumns, blanks, false, text)
		alist = aparser.ParseFile(filePath);
		  
		//for (int i = 0; i < alist.size(); i++) 
		//{ 		              
		//	System.out.println(alist.get(i).toString());
		//	}	   
		
		String content = Formatter.getFormattedText(alist);
		// System.out.println(content);
		
		//System.out.println("after parser call");
		
		return content;
	}
	
	private static String open_selected() {
		JFileChooser fileChooser = new JFileChooser("/Users/denbanek/eclipse-workspace/FormattingTool/src/formatterGui");
		fileChooser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				System.out.println("got it");
			}
		});
		
		int status = fileChooser.showOpenDialog(null);
		String parent = "";
		String name = "";
		if (status == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			parent = selectedFile.getParent();
			name = selectedFile.getName();
		} else if (status == JFileChooser.CANCEL_OPTION) {
			System.out.println("Canceled");
		}
		
		return (parent + "/" + name);
	}
	
	public MainFrame(String title, String path) {
		super(title); // inherit constructor behavior
		
		this.setSize(300,300);
		
		//set layout manager
		setLayout(new BorderLayout());
		
		// make content frame
		Container appVis = getContentPane();
		
		Border border = BorderFactory.createLineBorder(Color.WHITE);
		
		detailsPanel = new DetailsPanel(30); // param is height
		
		errorPanel = new ErrorPanel(50);
		
		JTextArea formatTextArea = new JTextArea();
		
		detailsPanel.addDetailListener(new DetailListener() {
			
			String filePath = "";
			
			@Override
			public void detailEventOccurred(DetailEvent event) {
				// TODO Auto-generated method stub
				String commandName = event.getName();
				if (commandName == "open") {
					filePath = open_selected(); 
					detailsPanel.writeContent(noFormat(filePath));
					formatTextArea.setText(detailsPanel.getContent());
				}
				else if (commandName == "save") {
					
				}
				else if (commandName == "format") {
					detailsPanel.writeContent(doFormat(filePath));
					formatTextArea.setText(detailsPanel.getContent());
				}
				
			}
		});
		
		
		JScrollPane scroller = new JScrollPane(formatTextArea,
                						  JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                						  JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		Font font = new Font(Font.MONOSPACED, Font.PLAIN, 13);
		
		formatTextArea.setFont(font);
		formatTextArea.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(1, 5, 5, 10)));
		formatTextArea.setAlignmentX(CENTER_ALIGNMENT);
		appVis.add(scroller);
		
		
		appVis.add(formatTextArea, BorderLayout.CENTER);
		appVis.add(errorPanel, BorderLayout.SOUTH);
		appVis.add(detailsPanel, BorderLayout.NORTH);
		
		
		// add sample content to textArea
		formatTextArea.append("");
	}
}
