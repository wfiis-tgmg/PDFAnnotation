package pl.edu.agh.tgmg.api;

import com.google.common.base.Strings;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageResolverImpl implements MessageResolver {

    private final String PATTERN = "^\\$\\{[a-zA-Z0-9.]*}";
    private ResourceBundle bundle;

    public MessageResolverImpl(String fileClassPath, Locale locale) {
        bundle = ResourceBundle.getBundle(fileClassPath, locale);
    }

    public MessageResolverImpl(String fileClassPath) {
        this(fileClassPath,Locale.getDefault());
    }

    @Override
    public String getMessage(String key)
    {
        if(!matches(key)) return key;
        return bundle.getString(key.substring(2, key.length() - 1));
    }

    @Override
    public String getMessage(String key, String defaultVal) {
        String value = Strings.isNullOrEmpty(key) ? defaultVal : key;
        return getMessage(value);
    }

    public boolean matches(String key) {
        return key.matches(PATTERN);
    }


}
