package formatterGui;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Parser {
   private static final Pattern _commandNumberPattern = Pattern.compile("(^-[abnp])(\\d+$)");

   private char justify = 'l'; // l, r, c, e
   private int lineLength = 80; // >0 || <= 80
   private boolean wrapEnabled = false; // True = wrap on
   private boolean doublespace = false; // False = single spaced
   private boolean isTitle = false;
   private int indent = 0; // >= 0
   private int numColumns = 1; // 1 || 2
   private int blanks = 0;
   private String commandin[] = {"", "0"}; 
   
   ArrayList<TextSnippet> allText = new ArrayList<TextSnippet>();

   public Parser() {
   }
   
   public ArrayList<TextSnippet> ParseFile(String fileName) {
      allText.clear();
      Scanner inputStream = null;
      try
      {
         inputStream = new Scanner(new File(fileName));
      }
      catch(FileNotFoundException e)
      {
         System.out.println("Error opening the file " + fileName);
         return allText;
      }
      
      while (inputStream.hasNextLine())
      {
         this.parseLine(inputStream.nextLine());
      }
      inputStream.close();
      return allText;
   }
   
   private String stripNewline(String line) {
      String strippedLine = line;
      if (line.endsWith("\r\n"))
         strippedLine = line.substring(0, line.length() - 2);
      else if (line.endsWith("\n"))
         strippedLine = line.substring(0, line.length() - 1);
      return strippedLine;
   }
   
   private boolean isCommandWithNumber(String line, String[] cin) {
      Matcher m = _commandNumberPattern.matcher(line);    
      if (m.matches()) {
         cin[0] = m.group(1);
         cin[1] = m.group(2);
         return true;
      } else {
         cin[0] = "";
         cin[1] = "0";
         return false;
      }
   }
   
   private void parseLine(String line) {
      boolean wasCommand = false;
      String trimLine = this.stripNewline(line);
      if (line.length() > 1 && Character.toLowerCase(line.charAt(0)) == '-') {           
         String command = "";
         Integer commandVal = new Integer(0);
         if (trimLine.equals("-l") || trimLine.equals("-r") || trimLine.equals("-c") || trimLine.equals("-e")) {
            wasCommand = true;
            justify = trimLine.charAt(trimLine.length() - 1);
         } 
         else if (this.isCommandWithNumber(trimLine, commandin)) {
            command = commandin[0];
            commandVal = Integer.parseInt(commandin[1]);
            if (command.equals("-a")) {
               if (commandVal == 1 || commandVal == 2) {
                  wasCommand = true;
                  numColumns = commandVal.intValue();
               }
            }
            else if (command.equals("-b")) {
               if (commandVal > 0) {
                  wasCommand = true;
                  blanks = commandVal.intValue();
               }
            }
            else if (command.equals("-n")) {
               if (commandVal > 0 && commandVal <= 80) {
                  wasCommand = true;
                  lineLength = commandVal.intValue();
               }
            }
            else if (command.equals("-p")) {
               if (commandVal >= 0 && commandVal < 80) {
                  wasCommand = true;
                  indent = commandVal.intValue();
               }
            }
         }
         
         else if (trimLine.equals("-w+")) {
            wasCommand = true;
            wrapEnabled = true;
         }
         else if (trimLine.equals("-w-")) {
            wasCommand = true;
            wrapEnabled = false;
         }
         
         else if (trimLine.equals("-s")) {
            wasCommand = true;
            doublespace = false;
         }
         
         else if (trimLine.equals("-d")) {
            wasCommand = true;
            doublespace = true;
         }
         
         else if (trimLine.equals("-t")) {
            wasCommand = true;
            isTitle = true;
         }
      }
      if (wasCommand && blanks > 0) {
         TextSnippet newSnippet = new TextSnippet(justify, lineLength, wrapEnabled, doublespace, isTitle, indent, numColumns,
                                    blanks, false, "");
         allText.add(newSnippet);
         blanks = 0;
      } else if (!wasCommand) {
         //create new textSnippet w/ current flags and stuff into ArrayList
        
         TextSnippet newSnippet = new TextSnippet(justify, lineLength, wrapEnabled, doublespace, isTitle, indent, numColumns,
                                          blanks, true, line);
         allText.add(newSnippet);
         //reset temporary flags back to defaults
         isTitle = false;      
      }
   }
}