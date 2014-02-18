package pl.edu.agh.tgmg.itext.generators.styles;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.tgmg.api.annotations.styles.CellHeaderStyle;
import pl.edu.agh.tgmg.api.annotations.styles.CellRowStyle;
import pl.edu.agh.tgmg.api.annotations.styles.ParagraphStyle;
import pl.edu.agh.tgmg.api.annotations.styles.TableStyle;
import pl.edu.agh.tgmg.api.exceptions.InvalidStyleException;
import pl.edu.agh.tgmg.api.exceptions.ReflectionException;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.formatters.CreatesPdfElement;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.CellHeaderFormatter;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.CellRowFormatter;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.ParagraphFormatter;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.StyleFormatter;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.TableFormatter;
import pl.edu.agh.tgmg.itext.generators.styles.parser.SmallElementsParser;

import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class StyleResolverImpl implements StyleResolver {
    
    private Map<
        Class<? extends Annotation>, 
        StyleFormatter<? extends Element, ? extends Annotation>> defaultFormatters;
    
    private Class<?> rootClass;

    public StyleResolverImpl() {
        this(new CellHeaderFormatter(), new CellRowFormatter(), new TableFormatter(), new ParagraphFormatter());
    }
    
    public StyleResolverImpl(StyleFormatter<PdfPCell, CellHeaderStyle> defaultHeaderCellStyle, 
            StyleFormatter<PdfPCell, CellRowStyle> defaultRowCellStyle,
            StyleFormatter<PdfPTable, TableStyle> defaultTableStyle,
            StyleFormatter<Paragraph, ParagraphStyle> defaultParagraphStyle) {
        defaultFormatters = new HashMap<>();
        defaultFormatters.put(CellRowStyle.class, defaultRowCellStyle);
        defaultFormatters.put(CellHeaderStyle.class, defaultHeaderCellStyle);
        defaultFormatters.put(TableStyle.class, defaultTableStyle);
        defaultFormatters.put(ParagraphStyle.class, defaultParagraphStyle);
    }
    
    @Override
    public void setRootClass(Class<?> root) {
        rootClass = root;
    }
    
    @Override
    public <E extends Element, S extends Annotation> void setDefaltStyle(
            Class<S> annotationType, StyleFormatter<E, S> formatter) {
        defaultFormatters.put(annotationType, formatter);
    }
    
    public <E extends Element, S extends Annotation> void applyStyle(CreatesPdfElement<E, S> element, 
            S styleAnnotation, Class<?> declaringClass) {
        applyDefaultStyle(element);
        Class<S> annotationClass = getAnnotationType(styleAnnotation);
        S style = null;
        if(rootClass != null) {
            style = rootClass.getAnnotation(annotationClass);
        }
        if(style != null) {
            applyStyle(element, style);
        }
        style = declaringClass.getAnnotation(annotationClass);
        if(style != null) {
            applyStyle(element, style);
        }
        applyStyle(element, styleAnnotation);
    }
    
    
    
    @SuppressWarnings("unchecked")
    <E extends Element, S extends Annotation> void applyDefaultStyle(CreatesPdfElement<E, S> element) {
        StyleFormatter<E, S> formatter = element.getFormatter();
        Class<S> annotationClass = formatter.getFormatterStyleClass();
        StyleFormatter<E, S> defaultFormatter = (StyleFormatter<E, S>) defaultFormatters.get(annotationClass);
        element.setFormatter(defaultFormatter);
    }
    
    <E extends Element, S extends Annotation> void applyStyle(CreatesPdfElement<E, S> element, S styleAnnotation) {
        StyleFormatter<E, S> formatter = element.getFormatter();
        Class<S> annotationClass = formatter.getFormatterStyleClass();
        Class<?> styleClass = getStyleClass(styleAnnotation);
        if(!styleClass.equals(Class.class)) {
            S style = styleClass.getAnnotation(annotationClass);
            if(style == null) {
                throw new InvalidStyleException("Class " + styleClass + 
                        " has no " + annotationClass.getName());
            }
            applyStyle(formatter, style);
        }
        applyStyle(formatter, styleAnnotation);
    }
    
    @SuppressWarnings("unchecked")
    static <S extends Annotation> Class<S> getAnnotationType(S styleAnnotation) {
        if(styleAnnotation instanceof CellHeaderStyle) {
            return (Class<S>) CellHeaderStyle.class;
        } else if(styleAnnotation instanceof CellRowStyle) {
            return (Class<S>) CellRowStyle.class;
        } else if(styleAnnotation instanceof TableStyle) {
            return (Class<S>) TableStyle.class;
        } else if(styleAnnotation instanceof ParagraphStyle) {
            return (Class<S>) ParagraphStyle.class;
        } else {
            throw new InvalidStyleException("annotation style '" + 
                    styleAnnotation + "' is not defined");
        }
    }
    
    static <S extends Annotation> Class<?> getStyleClass(S styleAnnotation) {
        if(styleAnnotation instanceof CellHeaderStyle) {
            return ((CellHeaderStyle) styleAnnotation).styleClass();
        } else if(styleAnnotation instanceof CellRowStyle) {
            return ((CellRowStyle) styleAnnotation).styleClass();
        } else if(styleAnnotation instanceof TableStyle) {
            return ((TableStyle) styleAnnotation).styleClass();
        } else if(styleAnnotation instanceof ParagraphStyle) {
            return ((ParagraphStyle) styleAnnotation).styleClass();
        } else {
            throw new InvalidStyleException("annotation style '" + 
                    styleAnnotation + "' is not defined");
        }
    }
    
    static <E extends Element, S extends Annotation> void applyStyle(
            StyleFormatter<E, S> formatter, S style) {
        for(String valueName : formatter.getValueNames()) {
            Method getter;
            try {
                getter = style.getClass().getMethod(valueName);
            } catch (NoSuchMethodException | SecurityException e) {
                throw new ReflectionException("Couldn't get getter for value [" + valueName + 
                        "] for style [" + style + "]", e);
            }
            Object values;
            try {
                values = getter.invoke(style);
            } catch (IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
                throw new ReflectionException("Couldn't get value [" + valueName + 
                        "] for style [" + style + "]", e);
            }
            setObject(formatter, getter, values);
        }  
    }
    
    static <E extends Element, S extends Annotation> void setObject(StyleFormatter<E, S> formatter, Method getter, Object values) {
        if(!values.getClass().isArray()) {
            throw new ReflectionException("Value [" + values + 
                    "] is not an array");
        }
        if(Array.getLength(values) == 0) {
            return;
        }
        String valueName = getter.getName();
        Object value =  Array.get(values, 0);
        String setterName = "set" + valueName.substring(0, 1).toUpperCase() + valueName.substring(1);
        Class<?> valueType = getter.getReturnType().getComponentType();
        Method parser = getParserMethod(valueType);
        if(parser != null) valueType = parser.getReturnType();
        Method setter;
        try {
            setter = formatter.getClass().getMethod(setterName, valueType);
        } catch (NoSuchMethodException e) {
            throw new ReflectionException("Couldn't get setter for value [" + valueName + 
                    "] for formatter [" + formatter.getClass().getName() + "]", e);
        }
        if(parser != null) {
            try {
                value = parser.invoke(null, value);
            } catch (IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
                throw new ReflectionException("Couldn't parse value [" + valueName + 
                        "] with parser [" + parser.getName() + "]", e);
            }
        }
        try {
            setter.invoke(formatter, value);
        } catch (IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            throw new ReflectionException("Couldn't set value [" + valueName + 
                    "] for formatter [" + formatter.getClass().getName() + "]", e);
        }
    }
    
    static Method getParserMethod(Class<?> valueType) {
        for(Method method : SmallElementsParser.class.getDeclaredMethods()) {
            Class<?>[] params = method.getParameterTypes();
            if(params.length == 1 && params[0].equals(valueType) && 
                    method.getName().equals("parse")) {
                return method;
            }
        }
        return null;
    }
}
