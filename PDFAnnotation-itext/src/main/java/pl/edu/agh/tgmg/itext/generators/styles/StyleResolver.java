package pl.edu.agh.tgmg.itext.generators.styles;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.tgmg.api.annotations.styles.CellHeaderStyle;
import pl.edu.agh.tgmg.api.annotations.styles.CellRowStyle;
import pl.edu.agh.tgmg.api.annotations.styles.ParagraphStyle;
import pl.edu.agh.tgmg.api.annotations.styles.TableStyle;
import pl.edu.agh.tgmg.api.exceptions.InvalidStyleException;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.formatters.CreatesPdfElement;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.CellHeaderFormatter;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.CellRowFormatter;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.ParagraphFormatter;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.StyleFormatter;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.TableFormatter;
import pl.edu.agh.tgmg.itext.generators.styles.parser.HeaderCellStyleParser;
import pl.edu.agh.tgmg.itext.generators.styles.parser.ParagraphStyleParser;
import pl.edu.agh.tgmg.itext.generators.styles.parser.RowCellStyleParser;
import pl.edu.agh.tgmg.itext.generators.styles.parser.StyleElementParser;
import pl.edu.agh.tgmg.itext.generators.styles.parser.TableStyleParser;

import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class StyleResolver {
    
    private Map<Class<? extends Annotation>, StyleFormatter<? extends Element, ? extends Annotation>> defaultFormatters;
    private Map<Class<? extends Annotation>, StyleElementParser<? extends Element, ? extends Annotation>> styleParsers;

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
        styleParsers = new HashMap<>();
        styleParsers.put(CellRowStyle.class, new RowCellStyleParser());
        styleParsers.put(CellHeaderStyle.class, new HeaderCellStyleParser());
        styleParsers.put(TableStyle.class, new TableStyleParser());
        styleParsers.put(ParagraphStyle.class, new ParagraphStyleParser());  
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
        formatter.setStyle(defaultFormatter);
    }
    
    @SuppressWarnings("unchecked")
    private <E extends Element, S extends Annotation> void applyStyle(CreatesPdfElement<E, S> element, S styleAnnotation) {
        StyleFormatter<E, S> formatter = element.getFormatter();
        Class<S> annotationClass = formatter.getFormatterStyleClass();
        Class<?> styleClass = getStyleClass(styleAnnotation);
        StyleElementParser<E, S> styleParser = (StyleElementParser<E, S>) styleParsers.get(annotationClass);
        if(!styleClass.equals(Class.class)) {
            S style = styleClass.getAnnotation(annotationClass);
            if(style == null) {
                throw new InvalidStyleException("Class " + styleClass + 
                        " has no " + annotationClass.getName());
            }
            styleParser.applyStyle(formatter, style);
        }
        styleParser.applyStyle(formatter, styleAnnotation);
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
}
