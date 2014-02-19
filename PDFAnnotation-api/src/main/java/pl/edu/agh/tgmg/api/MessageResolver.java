package pl.edu.agh.tgmg.api;

/**
 * Class that processes text and resolves references to messages stored
 * in properties files. It also handles localization.
 * @author Tom
 *
 */
public interface MessageResolver {
    
    /**
     * Processes the given text. In case the reference is not found
     * the given text is returned.
     * @param key the given text
     * @return processed text
     */
    String  getMessage(String key);
    
    /**
     * Processes the given text. In case the reference is not found
     * the <code>defaultVal</code> is returned.
     * @param key the given text
     * @return processed text
     */
    String  getMessage(String key,String defaultVal);
}
