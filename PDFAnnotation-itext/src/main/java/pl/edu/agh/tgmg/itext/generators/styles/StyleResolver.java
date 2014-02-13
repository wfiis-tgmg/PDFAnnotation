package pl.edu.agh.tgmg.itext.generators.styles;

import java.lang.annotation.Annotation;
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
import pl.edu.agh.tgmg.itext.generators.styles.parser.HeaderCellStyleParser;
import pl.edu.agh.tgmg.itext.generators.styles.parser.ParagraphStyleParser;
import pl.edu.agh.tgmg.itext.generators.styles.parser.RowCellStyleParser;
import pl.edu.agh.tgmg.itext.generators.styles.parser.SmallElementsParser;
import pl.edu.agh.tgmg.itext.generators.styles.parser.StyleElementParser;
import pl.edu.agh.tgmg.itext.generators.styles.parser.TableStyleParser;

import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class StyleResolver {
    
    private Map<Class<? extends Annotation>, StyleFormatter<? extends Element, ? extends Annotation>> defaultFormatters;

    public StyleResolver() {
        this(new CellHeaderFormatter(), new CellRowFormatter(), new TableFormatter(), new ParagraphFormatter());
    }
    
    public StyleResolver(StyleFormatter<PdfPCell, CellHeaderStyle> defaultHeaderCellStyle, 
            StyleFormatter<PdfPCell, CellRowStyle> defaultRowCellStyle,
            StyleFormatter<PdfPTable, TableStyle> defaultTableStyle,
            StyleFormatter<Paragraph, ParagraphStyle> defaultParagraphStyle) {
        defaultFormatters = new HashMap<>();
        defaultFormatters.put(CellRowStyle.class, defaultRowCellStyle);
        defaultFormatters.put(CellHeaderStyle.class, defaultHeaderCellStyle);
        defaultFormatters.put(TableStyle.class, defaultTableStyle);
        defaultFormatters.put(ParagraphStyle.class, defaultParagraphStyle);
    }
    
    @SuppressWarnings("unchecked")
    public <E extends Element, S extends Annotation> void applyStyle(CreatesPdfElement<E, S> element, 
            S styleAnnotation, Class<?> declaringClass) {
        applyDefaultStyle(element);
        Class<S> annotationClass = (Class<S>) styleAnnotation.getClass();
        S style = declaringClass.getAnnotation(annotationClass);
        if(style != null) {
            applyStyle(element, style);
        }
        applyStyle(element, styleAnnotation);
    }
    
    @SuppressWarnings("unchecked")
    private <E extends Element, S extends Annotation> void applyDefaultStyle(CreatesPdfElement<E, S> element) {
        StyleFormatter<E, S> formatter = element.getFormatter();
        Class<S> annotationClass = formatter.getFormatterStyleClass();
        StyleFormatter<E, S> defaultFormatter = (StyleFormatter<E, S>) defaultFormatters.get(annotationClass);
        element.setFormatter(defaultFormatter);
    }
    
    @SuppressWarnings("unchecked")
    private <E extends Element, S extends Annotation> void applyStyle(CreatesPdfElement<E, S> element, S styleAnnotation) {
        StyleFormatter<E, S> formatter = element.getFormatter();
        Class<S> annotationClass = formatter.getFormatterStyleClass();
        Class<?> styleClass = getStyleClass(styleAnnotation);
        //StyleElementParser<E, S> styleParser = (StyleElementParser<E, S>) styleParsers.get(annotationClass);
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
    
    private <S extends Annotation> Class<?> getStyleClass(S styleAnnotation) {
        if(styleAnnotation instanceof CellHeaderStyle) {
            return ((CellHeaderStyle) styleAnnotation).styleClass();
        } else if(styleAnnotation instanceof CellRowStyle) {
            return ((CellRowStyle) styleAnnotation).styleClass();
        } else if(styleAnnotation instanceof TableStyle) {
            return ((TableStyle) styleAnnotation).styleClass();
        } else if(styleAnnotation instanceof ParagraphStyle) {
            return ((ParagraphStyle) styleAnnotation).styleClass();
        } else {
            throw new InvalidStyleException("Parser for style annotation '" + 
                    styleAnnotation + "' is not defined");
        }
    }
    
    private static <E extends Element, S extends Annotation> void applyStyle(
            StyleFormatter<E, S> formatter,S style) {
        for(String valueName : formatter.getValueNames()) {
            Method getter;
            try {
                getter = style.getClass().getMethod(valueName);
            } catch (NoSuchMethodException | SecurityException e) {
                throw new ReflectionException("Couldn't get getter for value [" + valueName + 
                        "] for style [" + style + "]", e);
            }
            Object[] values;
            try {
                values = (Object[]) getter.invoke(style);
            } catch (IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
                throw new ReflectionException("Couldn't get value [" + valueName + 
                        "] for style [" + style + "]", e);
            }
            setObject(formatter, getter, values);
        }  
    }
    
    private static <E extends Element, S extends Annotation> void setObject(StyleFormatter<E, S> formatter, Method getter, Object[] values) {
        if(values.length == 0) {
            return;
        }
        String valueName = getter.getName();
        Object value = values[0];
        String setterName = "set" + valueName.substring(0, 1).toUpperCase() + valueName.substring(1);
        Class<?> valueType = getter.getReturnType();
        Method parser = getParserMethod(getter.getReturnType());
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
    
    private static Method getParserMethod(Class<?> valueType) {
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
