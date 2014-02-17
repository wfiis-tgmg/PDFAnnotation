package pl.edu.agh.tgmg.api;

import com.google.common.base.Strings;

import java.util.Locale;
import java.util.ResourceBundle;

public class BlankMessageResolverImpl implements MessageResolver {

    @Override
    public String getMessage(String key)
    {
        return key;
    }

    @Override
    public String getMessage(String key, String defaultVal) {
        return Strings.isNullOrEmpty(key) ? defaultVal : key;
    }
}
