package pl.edu.agh.tgmg.api;

import com.google.common.base.Strings;

import java.util.Locale;
import java.util.ResourceBundle;

public class BlankI18nResolverImpl implements I18nResolver {

    @Override
    public String translate(String key)
    {
        return key;
    }

    @Override
    public String translate(String key, String defaultVal) {
        return Strings.isNullOrEmpty(key) ? defaultVal : key;
    }
}
