package myjavafx;

import java.util.Collection;
import javafx.scene.control.TextField;

/**
 * TextField improved.
 * @author Johan
 * @see javafx.scene.control.TextField
 */
public class MyTextField extends TextField {
    /**
     * Creates a TextField with empty text content.
     */
    public MyTextField() {
        super();
    }
    
    /**
     * Replaces each substring of this TextField's text that matches the given 
     * regular expression with the given replacement.
     * @param regex the regular expression to which this string is to be matched
     * @param replacement the string to be substituted for each match
     * @see String#replaceAll(String, String) 
     */
    public void replaceAll(String regex, String replacement) {
        setText(getText().replaceAll(regex, replacement));
    }
    
    /**
     * Change this TextField's text.
     * Same behavior than {@link TextField#setText(String)}.
     * @param text the new text of this TextField
     */
    public void set(String text) {
        setText(text);
    }
    
    /**
     * Return true is this TextField's text is empty, otherwise false.
     * @return true is this TextField's text is empty, otherwise false
     */
    public boolean isEmpty() {
        return getText().isEmpty();
    }
    
    /**
     * Tells whether or not this TextField's text matches the given regular expression.
     * @param regex the regular expression to which this string is to be matched
     * @return {@code true} if, and only if, this string matches the given regular expression
     */
    public boolean matches(String regex) {
        return getText().matches(regex);
    }

    /**
     * Add the given style to this ComboBox's style. This method have 
     * no effect if the ComboBox already have the given style.
     * @param style the new style for this ComboBox
     */
    public void addStyleClass(String style) {
        if (!getStyleClass().contains(style)) getStyleClass().add(style);
    }
    
    /**
     * Add all the given styles to this ComboBox's style. This method doesn't 
     * add duplicates styles.
     * @param stylesCollection the new styles for this ComboBox list
     */
    public void addAllStyleClass(Collection<? extends String> stylesCollection) {
        stylesCollection.forEach(this::addStyleClass);
    }
    
    /**
     * Add all the given styles to this ComboBox's styles. This method doesn't 
     * add duplicates styles.
     * @param styles the new styles for this ComboBox
     */
    public void addAllStyleClass(String... styles) {
        for (String style : styles) addStyleClass(style);
    }
    
    /**
     * Returns <tt>true</tt> if this ComboxBox's styles contains the specified element.
     * More formally, returns <tt>true</tt> if and only if this ComboxBox's styles contains
     * at least one style <tt>e</tt> such that
     * <tt>(style==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;style.equals(e))</tt>.
     *
     * @param style style to be tested
     * @return <tt>true</tt> if this ComboBox's styles contains the specified element
     */
    public boolean containsStyleClass(String style) {
        return getStyleClass().contains(style);
    }
    
    /**
     * Remove all the occurences of the given style to this ComboBox.
     * @param style the style to be remove
     */
    public void removeStyleClass(String style) {
        getStyleClass().removeAll(style);
    }
    
    /**
     * Remove all the occurences of the given styles to this ComboBox.
     * @param styles the styles to be remove
     */
    public void removeStylesClass(Collection<String> styles) {
        getStyleClass().removeAll(styles);
    }
    
    /**
     * Remove all the occurences of the given styles to this ComboBox.
     * @param styles the styles to be remove
     */
    public void removeStylesClass(String... styles) {
        getStyleClass().removeAll(styles);
    }
}
