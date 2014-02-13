package pl.edu.agh.tgmg.api;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18nResolverImpl implements I18nResolver {

    private final String PATTERN = "^\\$\\{[a-zA-Z0-9.]*}";
    private ResourceBundle bundle;

    public I18nResolverImpl(String fileClassPath,Locale locale) {
        bundle = ResourceBundle.getBundle(fileClassPath, locale);
    }

    public I18nResolverImpl(String fileClassPath) {
        this(fileClassPath,Locale.getDefault());
    }

    @Override
    public String translate(String key)
    {
        if(!matches(key)) return key;
        return bundle.getString(key.substring(2,key.length()-1));
    }

    public boolean matches(String key) {
        return key.matches(PATTERN);
    }


}
