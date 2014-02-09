package pl.edu.agh.tgmg.api;

import java.lang.reflect.Field;

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

}
