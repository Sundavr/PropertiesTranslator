package propertiestranslator;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Encoding manager.
 * @author Johan
 */
public class EncodingManager {
    //prevent EncodingManager constructor calls
    private EncodingManager() {}
    
    /**
     * Returns <tt>true</tt> if the given character respect UTF-8 encoding, false otherwise.
     * @param character the character to test
     * @return <tt>true</tt> if the given character respect UTF-8 encoding.
     */
    public static boolean isUTF8(int character) {
        return character != 65533;
    }
    
    /**
     * Returns <tt>true</tt> if the given character respect ASCII encoding, false otherwise.
     * @param character the character to test
     * @return <tt>true</tt> if the given character respect ASCII encoding.
     */
    public static boolean isASCII(int character) {
        return Charset.forName("US-ASCII").newEncoder().canEncode((char)character);
    }
    
    /**
     * Transorm the given character into unicode code.
     * @param character character whose unicode code is desired
     * @return the unicode value of the given character
     */
    public static String toUnicode(int character) {
        return "\\u" + Integer.toHexString(character | 0x10000).substring(1);
    }
    
    /**
     * Transorm the given character into unicode code.
     * @param character character whose unicode code is desired
     * @return the unicode value of the given character
     */
    public static String toUnicode(Character character) {
        return toUnicode((int)character);
    }
    
    /**
     * Transorm the given text into unicode.
     * @param text text whose unicode equivalent is desired
     * @return the unicode value of all the characters presents in the given text
     */
    public static String toUnicode(String text) {
        String result = "";
        for (Character character : text.toCharArray()) {
            result += toUnicode(character);
        }
        return result;
    }
    
    /**
     * Transform all the non ASCII characters from the given text to unicode code.
     * @param text text to transform into ASCII-unicode acceptable String
     * @return the text from which all the non ASCII characters have been transforms 
     * into unicode
     */
    public static String nonASCIIToUnicode(String text) {
        String result = "";
        for (Character c : text.toCharArray()) {
            result += (isASCII(c)) ? c : toUnicode(c);
        }
        return result;
    }
    
    /**
     * Retrieve a character from his unicode code.
     * (e.g.) "\\u000D" become '\n'
     * @param unicode code unicode
     * @return the character corresponding to the given unicode code
     */
    public static char fromUnicode(String unicode) {
        return (char)Integer.parseInt(unicode.substring(2), 16);
    }
    
    /**
     * Test weither the given code use unicode encoding or not. (e.g.) "\\u000D". (1 backslash)
     * In other words, if the code length is 6 and start with \ return <tt>true</tt>.
     * @param code code to be tested
     * @return <tt>true</tt> if the code is a unicode character
     */
    public static boolean isUnicodeEncoding(String code) {
        return (code.length() == 6) && (code.charAt(0) == '\\');
    }
    
    /**
     * Returns <tt>true</tt> if the text contains any unicode encoding, otherwise false.
     * @param text text to be tested
     * @return <tt>true</tt> if the text contains any unicode encoding
     */
    public static boolean containsUnicodeEncoding(String text) {
        for (int i=0; i<text.length(); i++) {
            if ((i<text.length()-5) && (text.charAt(i) == '\\')) {
                if (isUnicodeEncoding(text.subSequence(i, i+6).toString())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Parse the given text and transform each unicode encoding found to a 
     * normal character.
     * (e.g.) "\\u000D" to '\n'.
     * @param text text to parse
     * @return the text with all unicode code parsed
     */
    public static String parseUnicodeEncoding(String text) {
        String result = "";
        for (int i=0; i<text.length(); i++) {
            if ((i<text.length()-5) 
             && (text.charAt(i) == '\\') 
             && (isUnicodeEncoding(text.subSequence(i, i+6).toString()))) {
                result += fromUnicode(text.subSequence(i, i+6).toString());
                i += 5;
            } else {
                result += text.charAt(i);
            }
        }
        return result;
    }
    
    /**
     * Replace all the '\\u000D' of this text with \r.
     * @param text text to parse
     * @return the parsed text
     */
    public static String parseUnicodeCarriageReturns(String text) {
        return text.replaceAll("\\\\u000D", "\r");
    }
    
    /**
     * Returns <tt>true</tt> if the given character if a line feed like character.
     * It means any '\n' (10), '\r' (13) or \u000D (unicode) character will be 
     * consider as line feed.
     * @param character character to test
     * @return <tt>true</tt> if the given character if a line feed
     */
    public static boolean isLineFeed(char character) {
        return (character == 13) || (character == 10);
    }
    
    /**
     * Split the given text in relation to its lines feeds or carriage returns.
     * @param text text to split
     * @return the text splitted
     */
    public static ArrayList<String> splitLines(String text) {
        String parsedText = parseUnicodeCarriageReturns(text);
        ArrayList<String> result = new ArrayList<>();
        String subsequence = "";
        for (int i=0; i<parsedText.length(); i++) {
            if ((parsedText.charAt(i) == 13) && (i < parsedText.length()-1) && (parsedText.charAt(i+1) == 10)) { // '\r\n'
                i++; //skip '\n'
            }
            if (isLineFeed(parsedText.charAt(i))) {
                if (!subsequence.isEmpty()) result.add(subsequence);
                subsequence = "";
            } else {
                subsequence += parsedText.charAt(i);
            }
        }
        if (!subsequence.isEmpty()) result.add(subsequence);
        return result;
    }
    
    /**
     * Convert each line feed to the code : '\\u000D'.
     * @param text text to parse
     * @return the text with all the line feeds transforms into unicode
     */
    public static String lineFeedsToUnicode(String text) {
        return text.replaceAll("\r\n", "\\\\u000D").replaceAll("\r", "\\\\u000D").replaceAll("\n", "\\\\u000D");
    }
    
    /**
     * Parse the given text and transform each argument into arg tag.
     * Args tags are : <br>
     * {X} -> {argX}<br>
     * '{X}' -> {sQargX} (for simple quotted arg)<br>
     * ''{X}'' -> {QargX} (for quotted arg)<br>
     * X must be a positiv integer.
     * In addition, if the text contains arguments, transform each "''" into "'".
     * @param text text to parse
     * @return the parse text
     */
    public static String parseArguments(String text) {
        Pattern numberPattern = Pattern.compile("\\d+");
        String argRegex = "((?<!')\\{\\d+\\}(?!'))"; // {digit+} not preced or follow by '
        String sQargRegex = "((?<!')'\\{\\d+\\}'(?!'))"; // '{digit+}' not preced or follow by '
        String QargRegex = "(''\\{\\d+\\}'')"; // ''{digit+}''
        Pattern argPattern = Pattern.compile(argRegex);
        Pattern sQargPattern = Pattern.compile(sQargRegex);
        Pattern QargPattern = Pattern.compile(QargRegex);
        
        String[] splittedText = text.split("(?=" + argRegex + "|" + sQargRegex + "|" + QargRegex + ")");
        for (int i=0; i<splittedText.length; i++) {
            String s = splittedText[i];
            Matcher argMatcher = argPattern.matcher(s);
            Matcher sQargMatcher = sQargPattern.matcher(s);
            Matcher QargMatcher = QargPattern.matcher(s);
            if (argMatcher.find()) {
                Matcher numberMatcher = numberPattern.matcher(argMatcher.group());
                numberMatcher.find();
                s = s.replaceAll(argRegex, "{arg" + numberMatcher.group() + "}");
            } else if (sQargMatcher.find()) {
                Matcher numberMatcher = numberPattern.matcher(sQargMatcher.group());
                numberMatcher.find();
                s = s.replaceAll(sQargRegex, "{sQarg" + numberMatcher.group() + "}");
            } else if (QargMatcher.find()) {
                Matcher numberMatcher = numberPattern.matcher(QargMatcher.group());
                numberMatcher.find();
                s = s.replaceAll(QargRegex, "{Qarg" + numberMatcher.group() + "}");
            }
            if (splittedText.length > 1) { //contains arg, doubles quotes convert into simple quotes
                splittedText[i] = s.replaceAll("''", "'");
            } else {
                splittedText[i] = s;
            }
        }
        return String.join("", splittedText);
    }
    
    /**
     * Inversion of {@link #parseArguments(String)} method.
     * Recreate the arguments with the correct format like this:<br>
     * {argX} -> {X}<br>
     * '{argX}' -> '{X}'<br>
     * ''{argX}'' -> ''{X}''
     * @param text text to parse
     * @return the parsed text
     */
    public static String retrieveArguments(String text) {
        Pattern numberPattern = Pattern.compile("\\d+");
        String argRegex = "(\\{arg\\d+\\})"; // {digit+} not preced or follow by '
        String sQargRegex = "(\\{sQarg\\d+\\})"; // '{digit+}' not preced or follow by '
        String QargRegex = "(\\{Qarg\\d+\\})"; // ''{digit+}''
        Pattern argPattern = Pattern.compile(argRegex);
        Pattern sQargPattern = Pattern.compile(sQargRegex);
        Pattern QargPattern = Pattern.compile(QargRegex);
        
        String[] splittedText = text.split("(?=" + argRegex + "|" + sQargRegex + "|" + QargRegex + ")");
        for (int i=0; i<splittedText.length; i++) {
            String s = splittedText[i];
            Matcher argMatcher = argPattern.matcher(s);
            Matcher sQargMatcher = sQargPattern.matcher(s);
            Matcher QargMatcher = QargPattern.matcher(s);
            if (splittedText.length > 1) { //contains arg, simple quotes are convert back into double quotes
                s = s.replaceAll("'", "''");
            }
            if (argMatcher.find()) {
                Matcher numberMatcher = numberPattern.matcher(argMatcher.group());
                numberMatcher.find();
                s = s.replaceAll(argRegex, "{" + numberMatcher.group() + "}");
            } else if (sQargMatcher.find()) {
                Matcher numberMatcher = numberPattern.matcher(sQargMatcher.group());
                numberMatcher.find();
                s = s.replaceAll(sQargRegex, "'{" + numberMatcher.group() + "}'");
            } else if (QargMatcher.find()) {
                Matcher numberMatcher = numberPattern.matcher(QargMatcher.group());
                numberMatcher.find();
                s = s.replaceAll(QargRegex, "''{" + numberMatcher.group() + "}''");
            }
            splittedText[i] = s;
        }
        return String.join("", splittedText);
    }
    
    /**
     * Fully parse the given text, including unicodes characters, arguments , 
     * line feeds and carriage returns.
     * In other words, successivily call the methods:<br>
     * {@link #parseUnicodeEncoding(String)}<br>
     * {@link #parseArguments(String)}<br>
     * {@link #splitLines(String)}
     * @param text the text to parse
     * @return the fully parsed text
     */
    public static ArrayList<String> parse(String text) {
        return splitLines(parseArguments(parseUnicodeEncoding(text)));
    }
    
    /**
     * Fully parse the given text.
     * @param text the text to parse
     * @return the fully parsed text
     */
    public static String unParse(String text) {
        return retrieveArguments(lineFeedsToUnicode(nonASCIIToUnicode(text)));
    }
}