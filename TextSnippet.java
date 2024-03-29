/**
 * 
 * Rachel White, Team 24
 * CSE360 85141 
 * Team Project
 * 
 * This class defines a TextSnippet.
 * 
 * package formatterGui;
 * @author Rachel White
 */

package formatterGui;

public class TextSnippet {
   private char _justify; // l, r, c, e
   private int _lineLength; // >0 || <= 80
   private boolean _wrapEnabled; // True = wrap on
   private boolean _doublespace; // False = single spaced
   private boolean _isTitle;
   private boolean _hasText;
   private int _indent; // >= 0
   private int _numColumns; // 1 || 2
   private int _numBlanks; // >= 0
   private String _text;
   private String _formattedText;
   
   /*
    * Class constructor.
    */
   public TextSnippet(char justify, int lineLength, boolean wrapEnabled, boolean doublespace, boolean isTitle,
                         int indent, int numColumns, int numBlanks, boolean hasText, String text) {                     
      _justify = justify;
      _lineLength = lineLength;
      _wrapEnabled = wrapEnabled;
      _doublespace = doublespace;
      _isTitle = isTitle;
      _indent = indent;
      _numColumns = numColumns;
      _numBlanks = numBlanks;
      _hasText = hasText;
      _text = text;
      _formattedText = "";
   }
   
   /*
    * toString() primarily for debug and testing purposes.
    */
   public String toString() {
      
      return("Snippet is: Justify: " + _justify + " length: " + _lineLength + 
             " wrap: " + _wrapEnabled + " doublespace: " + _doublespace +
             " istitle: "  + _isTitle +  " indent: " + _indent +  " numcolumns: " + _numColumns + 
             " numblanks: " + _numBlanks + " hastext: " + _hasText + System.lineSeparator() + "text: "  + _text);
   }
   
   public char getJustify() {
      return _justify;
   }

   public int getLineLength() {
      return _lineLength;
   }

   public boolean getWrapEnabled() {
      return _wrapEnabled;
   }

   public boolean getDoublespace() {
      return _doublespace;
   }

   public boolean getIsTitle() {
      return _isTitle;
   }

   public int getIndent() {
      return _indent;
   }
   
   public int getNumColumns() {
      return _numColumns;
   }

   public int getNumBlanks() {
      return _numBlanks;
   }

   public boolean getHasText() {
      return _hasText;
   }

   public String getText() {
      return _text;
   }
   
   public String getFormattedText() {
      return _formattedText;
   }
}