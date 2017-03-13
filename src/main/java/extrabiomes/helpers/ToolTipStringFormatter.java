package extrabiomes.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused") //Some of the patterns aren't used
public class ToolTipStringFormatter {
  
  private static String defaultColorVal = "7";
  private static String colorVal = "7";
  private static boolean obfuscated = false;
  private static boolean bold = false;
  private static boolean strikethrough = false;
  private static boolean underline = false;
  private static boolean italic = false;
  
  private static final String unicodeControlCode = "\u00A7";
  private static final Pattern controlCode = Pattern.compile("\\\\");
  private static final Pattern codes = Pattern.compile("\\\\.");
  private static final Pattern resetCode = Pattern.compile("\\\\r");
  private static final Pattern codesGood = Pattern.compile("\\\\[0123456789abcdefklmnor]");
  private static final Pattern codesBad = Pattern.compile("\\\\[^0123456789abcdefklmnor]");
  
  private static final Pattern codeObf = Pattern.compile("\\\\k");
  private static final Pattern codeBold = Pattern.compile("\\\\l");
  private static final Pattern codeStrike = Pattern.compile("\\\\m");
  private static final Pattern codeUnder = Pattern.compile("\\\\n");
  private static final Pattern codeItilic = Pattern.compile("\\\\o");
  private static final Pattern codeReset = Pattern.compile("\\\\r");
  
  public static List<String> Format(String text) {
    List<String> tmp = new ArrayList<String>();
    Format(text, tmp);
    
    return tmp;
  }
  
  public static void Format(String text, List<String> lines) {
    Format(text, lines, 20);
  }
  
  public static void Format(String text, List<String> lines, int targetLength) {
    String format = "7";
    
    if(text.length() > 0 && text.charAt(0) == 92){
      format = String.valueOf(text.charAt(1));
      text = text.substring(2);
    }
    
    Format(text, lines, targetLength, format);
  }
  
  public static void Format(String text, List<String> lines, String color) {
    Format(text, lines, 20, color);
  }
  
  public static void Format(String text, List<String> lines, int targetLength, String color) {
    String[] wordArray = text.split(" ");
    int words = wordArray.length;
    
    int curLineLength = 0;
    int wordLength = 0;
    int maxLength = targetLength + 5;
    String curLine = ""; 
    
    // Set the colors
    defaultColorVal = colorVal = color;
    
    for(int word = 0; word < words; word++) {
      wordLength = WordLength(wordArray[word]);
      
      // Always place the first word on the line
      if(curLineLength == 0) {
        // Add the formatting codes
        curLine = LineFormatHeader() + ParseTags(wordArray[word]);
        
        curLineLength = wordLength;
      } else if(curLineLength >= targetLength || (curLineLength + wordLength) >= maxLength) {
        // Add the old line
        lines.add(curLine);
        
        // Start a new line with the formatting codes
        curLine = LineFormatHeader() + ParseTags(wordArray[word]);
        curLineLength = wordLength;
      } else {
        curLine += " " + ParseTags(wordArray[word]);
        curLineLength += 1 + wordLength;
      }
    }
    
    if(curLineLength > 0) {
      lines.add(curLine + unicodeControlCode + "r");
    }
  }
  
  private static int WordLength(String word) {
    return codes.matcher(word).replaceAll("").length(); 
  }
  
  private static String LineFormatHeader() {
    String tmp = unicodeControlCode + colorVal;
    
    // Add t
    if(obfuscated) tmp += unicodeControlCode + "k";
    if(bold) tmp += unicodeControlCode + "l";
    if(strikethrough) tmp += unicodeControlCode + "m";
    if(underline) tmp += unicodeControlCode + "n";
    if(italic) tmp += unicodeControlCode + "o";
    
    return tmp;
  }
  
  private static String ParseTags(String word) {
    // Remove all bad codes
    word = codesBad.matcher(word).replaceAll("");
    
    // Log the change of good codes
    Matcher matcher = codesGood.matcher(word); 
    while (matcher.find()) {
      char code = word.charAt(matcher.end() - 1);
      if(code > 106) {
        switch(code) {
          case 107:
            obfuscated = true;
            break;
          case 108:
            bold = true;
            break;
          case 109:
            strikethrough = true;
            break;
          case 110:
            underline = true;
            break;
          case 111:
            italic = true;
            break;
          default:
            colorVal = defaultColorVal;
            obfuscated = false;
            bold = false;
            strikethrough = false;
            underline = false;
            italic = false;
            break;
        }
      } else {
        colorVal = String.valueOf(code);
      }
    }
    
    // Reset needs to rest the color to the default
    word = resetCode.matcher(word).replaceAll(unicodeControlCode + "r" + unicodeControlCode + defaultColorVal);
    
    // Replace good codes
    return controlCode.matcher(word).replaceAll(unicodeControlCode);
  }
}
