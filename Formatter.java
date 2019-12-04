/*
 * Author: Rachel White, Team 24
 * Class ID: CSE 360, fall 2019
 * Assignment: Formatter Tool, final project 
 */
package formatterGui;

import java.util.*;

public class Formatter {
   private static char _justify = 'l'; // l, r, c, e
   private static int _lineLength = 80; // >0 || <= 80
   private static boolean _wrapEnabled = false; // True = wrap on
   private static boolean _doublespace = false; // False = single spaced
   private static boolean _isTitle = false;
   private static int _indent = 0; // >= 0
   private static int _numColumns = 1; // 1 || 2
   private static int _blanks = 0;
   
   private static String _lineEnding = System.lineSeparator();
   
   private Formatter() {
   
   }
   
   public static String getFormattedText(ArrayList<TextSnippet> snippets) {
      TextSnippet asnippet;
      StringBuilder document = new StringBuilder();
      StringBuilder buffer = new StringBuilder();
      
      for (int i = 0; i < snippets.size(); i++) {
         asnippet = snippets.get(i);
         
         if (buffer.length() != 0 && hasStateChanged(asnippet)) {
            document.append(formatBuffer(buffer));
            buffer = new StringBuilder();
         }
         
         _justify = asnippet.getJustify();
         _lineLength = asnippet.getLineLength();
         _wrapEnabled = asnippet.getWrapEnabled();
         _doublespace = asnippet.getDoublespace();
         _isTitle = asnippet.getIsTitle();
         if (asnippet.getIndent() != 0) {
            _indent = asnippet.getIndent();
         }
         _numColumns = asnippet.getNumColumns();
         _blanks = asnippet.getNumBlanks();
         
         if (_doublespace) {
            _lineEnding = System.lineSeparator() + System.lineSeparator();
         } else {
            _lineEnding = System.lineSeparator();
         }
         
         for (int j = 0; j < _blanks; j++) {
            document.append(System.lineSeparator());
         }
         _blanks = 0;
         
         if (asnippet.getHasText()) {
            if (!_wrapEnabled) {
               if (buffer.length() != 0) {
                  buffer.append(System.lineSeparator());
               }
               buffer.append(asnippet.getText());
//               System.out.println("Buffer contents: [" + buffer.toString() + "]");
            } else {
               buffer.append(asnippet.getText());
            }
         }
      }
      if (buffer.length() != 0) {
         document.append(formatBuffer(buffer));
      }
      
      return document.toString();
   }
   
   public static String formatBuffer(StringBuilder buffer) {
      String[] bufferLines = buffer.toString().split(System.lineSeparator());
      StringBuilder formattedLines = new StringBuilder();
      
      if (_indent > 0) {
         bufferLines[0] = String.format("%" + _indent + "s%s", "", bufferLines[0]);
         _indent = 0;
      }
      for (int i = 0; i < bufferLines.length; i++)
      {
         String temp  = "";
         int padding = 0;
         
         if (_numColumns == 2)
         {
            String[] lines = wrapIntoLines(bufferLines[i], 35);
            int whichColumn = 1;
            for (int j = 0; j < lines.length; j++)
            {
               if (whichColumn == 1) {
                  formattedLines.append(String.format("%-35s", lines[j]));
                  whichColumn = 2;
               } else {
                  formattedLines.append(String.format("%10s%s", "", lines[j]) + _lineEnding);
                  whichColumn = 1;
               }
            }
            if (whichColumn == 2) {
               formattedLines.append(_lineEnding);
            }
         }
         else
         {
            char tempJustify = _justify;
            if (_isTitle) {
               tempJustify = 'c';              
            }
            
            String[] lines = wrapIntoLines(bufferLines[i], _lineLength);
            
            for (int j = 0; j < lines.length; j++)
            {
               //apply justification
               switch(tempJustify) {
                  case 'l':
                     formattedLines.append(lines[j] + _lineEnding);
                     break;
                  case 'r':
                     formattedLines.append(String.format("%" + _lineLength + "s", lines[j]) + _lineEnding);
                     break;
                  case 'c':
                     //string formatter with padding. subtract string length from line length and divide by 2 = padding          
                     padding = (_lineLength - lines[j].length()) / 2;
                     formattedLines.append(String.format("%"+padding+"s%s", "", lines[j]) + _lineEnding);
                     
                     if (_isTitle) {
                        for (int k = 0; k < lines[j].length(); k++) {
                           temp += '-';
                        }
                        formattedLines.append(String.format("%" + padding + "s%s", "", temp) + _lineEnding);
                     }            
                     break;
                  case 'e':               
                     //split string into words; add spaces 
                     //(linelength - textlength evenly)/number words after each word
                     //add leftover spaces (linelength - textlength)%number words
                     //one at a time after each word               
                     int spacestoadd = _lineLength - lines[j].length();
                     String[] splitit = lines[j].split(" ");
                     int splititLen = splitit.length;
                     if (splititLen > 1) {
                        int spacestoeach = (spacestoadd / splititLen);
                        int spacesleft = spacestoadd % splititLen;
                        //put one space back after each wori
                        for (int k = 0; k < splititLen; k++) {
                           splitit[k] = splitit[k] + " ";
                        }
                        for (int k = 0; k < spacestoeach; k++) {
                           temp += " ";
                        }
                        if (spacestoeach > 0) {
                           for (int k = 0; k < splititLen; k++) {
                              splitit[k] = splitit[k] + temp;
                           }
                        }
                        for (int k = 0; (k < splititLen & spacesleft > 0); k++) {
                           splitit[k] = splitit[k] + " ";
                           spacesleft = spacesleft - 1;
                        }
                     }
                     StringBuilder combinedText = new StringBuilder();
                     // put string back together
                     for (int k = 0; k < splititLen; k++) {
                           combinedText.append(splitit[k]);
                     }
                     combinedText.append(_lineEnding);
                     formattedLines.append(combinedText.toString());
                     break;
                  default:
                     formattedLines.append("Error: Unreachable." + _lineEnding);
               }
            }
         }
      }
      return formattedLines.toString();
   }
   
   private static String[] wrapIntoLines(String longLine, int lineLength) {
      ArrayList<String> lines = new ArrayList<String>();
      for (int i = 0; i < longLine.length(); i += lineLength) {
         if (i + lineLength > longLine.length()) {
            lines.add(longLine.substring(i, longLine.length()));
         } else {
            lines.add(longLine.substring(i, i + lineLength));
         }
      }
      String[] ohMyGodJavaWhy = new String[0];
      return lines.toArray(ohMyGodJavaWhy);
   }
   
   private static boolean hasStateChanged(TextSnippet snippet) {
      boolean stateChanged = false;
      
      if (_justify != snippet.getJustify())
         stateChanged = true;
      if (_lineLength != snippet.getLineLength())
         stateChanged = true;
      if (_wrapEnabled != snippet.getWrapEnabled())
         stateChanged = true;
      if (_doublespace != snippet.getDoublespace())
         stateChanged = true;
      if (_isTitle != snippet.getIsTitle())
         stateChanged = true;
      if (_indent != snippet.getIndent() && snippet.getIndent() > 0)
         stateChanged = true;
      if (_numColumns != snippet.getNumColumns())
         stateChanged = true;
      if (_blanks != snippet.getNumBlanks())
         stateChanged = true;
      
      return stateChanged;
   }
}