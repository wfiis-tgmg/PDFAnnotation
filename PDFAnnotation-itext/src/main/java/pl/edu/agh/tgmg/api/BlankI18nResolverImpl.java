package pl.edu.agh.tgmg.api;

import java.util.Locale;
import java.util.ResourceBundle;

public class BlankI18nResolverImpl implements I18nResolver {

    @Override
    public String translate(String key)
    {
        return key;
    }
}
