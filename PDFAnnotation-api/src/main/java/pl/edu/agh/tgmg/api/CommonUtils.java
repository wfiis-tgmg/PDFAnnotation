package pl.edu.agh.tgmg.api;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;

import pl.edu.agh.tgmg.api.annotations.PdfTableGroup;
import pl.edu.agh.tgmg.api.annotations.PdfRowGroup;
import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;
import pl.edu.agh.tgmg.api.exceptions.InvalidAnnotationException;

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

    // TODO add localization
    public static String processText(String input, String defaultValue) {
        if(input == null || input.isEmpty()) {
            return defaultValue;
        }
        return input;
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
        } else throw new GenDocumentException("Object is not Iterable");
        return iter;
    }
    
    public static boolean isClassParametriezed(Class<?> clazz) {
        Type type = clazz.getGenericSuperclass();
        return type instanceof ParameterizedType;
    }
    
    
    public static Class<?> getTypeParamOfIterableField(Field field) {
        Class<?> fieldClass = field.getType();
        if(fieldClass.isArray()) {
            return fieldClass.getComponentType();
        } 
        if(!Iterable.class.isAssignableFrom(fieldClass)) {
            throw new InvalidAnnotationException("class " + fieldClass.getName() + " is not Iterable");
        }
        Type type = field.getGenericType();
        if (!(type instanceof ParameterizedType)) {
            throw new InvalidAnnotationException("class " + fieldClass.getName() + " is not parameterized");
        }
        if(((ParameterizedType) type).getActualTypeArguments().length < 1) {
            throw new InvalidAnnotationException("class " + fieldClass.getName() + " has no parameters");
        }
        return (Class<?>) (((ParameterizedType) type).getActualTypeArguments()[0]);
    }
    
    static void inspect(Object o) {
        Type type = o.getClass();
        while (type != null) {
            System.out.print(type + " implements");
            Class<?> rawType =
                    (type instanceof ParameterizedType)
                    ? (Class<?>)((ParameterizedType)type).getRawType()
                    : (Class<?>)type;
            Type[] interfaceTypes = rawType.getGenericInterfaces();
            if (interfaceTypes.length > 0) {
                System.out.println(":");
                for (Type interfaceType : interfaceTypes) {
                    if (interfaceType instanceof ParameterizedType) {
                        ParameterizedType parameterizedType = (ParameterizedType)interfaceType;
                        System.out.print("  " + parameterizedType.getRawType() + " with type args: ");
                        Type[] actualTypeArgs = parameterizedType.getActualTypeArguments();
                        System.out.println(Arrays.toString(actualTypeArgs));
                    }
                    else {
                        System.out.println("  " + interfaceType);
                    }
                }
            }
            else {
                System.out.println(" nothing");
            }
            type = rawType.getGenericSuperclass();
        }
    }

    public static boolean isFieldANestedTable(Field field) {
        return field.isAnnotationPresent(PdfRowGroup.class) ||
                field.isAnnotationPresent(PdfTableGroup.class);
    }
}
