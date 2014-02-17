package pl.edu.agh.tgmg.api;

public interface MessageResolver {
    String  getMessage(String key);
    String  getMessage(String key,String defaultVal);
}
