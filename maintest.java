package formatterGui;

import java.util.*;
import java.io.*;
 
class Maintest
{
   public static void main(String args[])
   {
      String filename = "testfile4.txt";
      char snip_justify; // l, r, c, e
      int  snip_lineLength; // >0 || <= 80
      boolean snip_wrapEnabled; // True = wrap on
      boolean snip_doublespace; // False = single spaced
      boolean snip_isTitle;
      int snip_indent; // >= 0
      int snip_numColumns; // 1 || 2
      int snip_numBlanks; // >= 0
      boolean snip_hasText;
      String snip_text;

      
      System.out.println("calling parser");
      ArrayList<TextSnippet> alist = new ArrayList<TextSnippet>();

      Parser aparser = new Parser();
      TextSnippet alistSnippet;
      
      //TextSnippet elements - justify, lineLength, wrapEnabled, doublespace, isTitle, indent, numColumns, blanks, false, text)
      alist = aparser.ParseFile(filename);
      
      for (int i = 0; i < alist.size(); i++) 
      { 		              
         System.out.println(alist.get(i).toString());
 	   }   
      
      /*
      for (int i = 0; i < alist.size(); i++) {
         alistSnippet = alist.get(i);
         alistSnippet.applyFormatting();
         snip_text = alistSnippet.getFormattedText();
         System.out.print(snip_text);
         //snip_text = alistSnippet.getText();
         //System.out.println(snip_text);
      }
      //*/
      
      System.out.print(Formatter.getFormattedText(alist));

      System.out.println("after parser call");
         
   }
}      
