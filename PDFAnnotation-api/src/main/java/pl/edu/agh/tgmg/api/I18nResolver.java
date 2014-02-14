package pl.edu.agh.tgmg.api;

public interface I18nResolver {
    String  translate(String key);
    String  translate(String key,String defaultVal);
}
