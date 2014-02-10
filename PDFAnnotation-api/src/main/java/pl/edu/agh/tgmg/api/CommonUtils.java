package pl.edu.agh.tgmg.api;

import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;

public class CommonUtils {


     static public Object getValue(Object o,String name)  {
        try {

            Field field = o.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(o);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static public Iterable getIterable(Object data) {
        Iterable iter = Collections.emptyList();
        if(data instanceof  Iterable)
        {
           iter  = (Iterable)data;
        }
        else if(data.getClass().isArray())
        {
            iter = Arrays.asList((Object[]) data);
        } else new GenDocumentException();
        return iter;
    }
}
