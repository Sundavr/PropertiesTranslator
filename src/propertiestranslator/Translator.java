package propertiestranslator;

import com.google.api.GoogleAPI;
import com.google.api.GoogleAPIException;
import com.google.api.translate.Language;
import com.google.api.translate.Translate;

/**
 * Translator.
 * Make sure to initialize the key with {@link #setKey(String)} with a valid key.
 * Do not forget to activate the Google translate API on your google account (Google Cloud Platform).
 * @author Johan
 */
public class Translator {
    private final static String HTTP = "http://code.google.com/p/google-api-translate-java/";
    private static String KEY;
    private static Translator instance;
    
    /**
     * Return the translator singleton.
     * @return the translator singleton
     */
    public static synchronized Translator getInstance() {
        if (instance == null) {
            instance = new Translator();
            GoogleAPI.setHttpReferrer(HTTP);
            GoogleAPI.setKey(KEY);
        }
        return instance;
    }
    
    /**
     * Define the Google API key.
     * @param key the Google API key
     */
    public static void setKey(String key) {
        KEY = key;
        GoogleAPI.setKey(KEY);
    }
    
    /**
     * Translate the given text from <code>srcLanguage</code> to <code>destLanguage</code>.
     * @param text the text to translate
     * @param srcLanguage source language
     * @param destLanguage destination language
     * @return the traduction of the given text in <code>destLanguage</code>
     * @throws GoogleAPIException Impossible to reach the Google API
     */
    public String translate(String text, Language srcLanguage, Language destLanguage) throws GoogleAPIException {
        return Translate.DEFAULT.execute(text, srcLanguage, destLanguage);
    }
    
    /**
     * Translate the given text from <code>srcLanguage</code> to <code>destLanguages</code>.
     * @param text the text to translate
     * @param srcLanguage source language
     * @param destLanguages destination languages
     * @return all the traduction of the given text in <code>destLanguages</code>
     * @throws GoogleAPIException Impossible to reach the Google API
     */
    public String[] translate(String text, Language srcLanguage, Language[] destLanguages) throws GoogleAPIException {
        return Translate.DEFAULT.execute(text, srcLanguage, destLanguages);
    }
    
    /**
     * Translate all the given texts from <code>srcLanguage</code> to <code>destLanguage</code>.
     * @param texts the text to translate
     * @param srcLanguage source language
     * @param destLanguage destination language
     * @return the traduction of all the given texts in <code>destLanguage</code>
     * @throws GoogleAPIException Impossible to reach the Google API
     */
    public String[] translate(String[] texts, Language srcLanguage, Language destLanguage) throws GoogleAPIException {
        return Translate.DEFAULT.execute(texts, srcLanguage, destLanguage);
    }
    
    /**
     * Translate all the given texts from <code>srcLanguages</code> to <code>destLanguages</code>.
     * @param texts the texts to translate
     * @param srcLanguages source languages
     * @param destLanguages destination languages
     * @return the traduction of all the given texts from the corresponding <code>srcLanguages</code> to the corresponding <code>destLanguage</code>
     * @throws GoogleAPIException Impossible to reach the Google API
     */
    public String[] translate(String[] texts, Language[] srcLanguages, Language[] destLanguages) throws GoogleAPIException {
        return Translate.DEFAULT.execute(texts, srcLanguages, destLanguages);
    }
}
