package pl.edu.agh.tgmg.itext.generators.styles;

import java.io.ObjectInputStream.GetField;
import java.lang.annotation.Annotation;

import pl.edu.agh.tgmg.api.annotations.styles.CellStyle;
import pl.edu.agh.tgmg.api.annotations.styles.ParagraphStyle;
import pl.edu.agh.tgmg.api.annotations.styles.TableStyle;
import pl.edu.agh.tgmg.api.exceptions.InvalidStyleException;
import pl.edu.agh.tgmg.api.exceptions.ReflectionException;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.CreatesCellElement;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.CreatesParagraphElement;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.CreatesTableElement;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.CellFormatter;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.ParagraphFormatter;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.TableFormatter;
import pl.edu.agh.tgmg.itext.generators.styles.parser.CellStyleParser;
import pl.edu.agh.tgmg.itext.generators.styles.parser.ParagraphStyleParser;
import pl.edu.agh.tgmg.itext.generators.styles.parser.TableStyleParser;

public class StyleResolver {

    private final CellFormatter defaultCellStyle;
    private final TableFormatter defaultTableStyle;
    private final ParagraphFormatter defaultParagraphStyle;
    
    private final CellStyleParser cellStyleParser = new CellStyleParser();
    private final TableStyleParser tableStyleParser = new TableStyleParser();
    private final ParagraphStyleParser paragraphStyleParser = new ParagraphStyleParser();
    
    public StyleResolver() {
        this.defaultCellStyle = new CellFormatter();
        this.defaultTableStyle = new TableFormatter();
        this.defaultParagraphStyle = new ParagraphFormatter();
    }
    
    public CellFormatter getDefaultCellStyle() {
        return defaultCellStyle;
    }

    public TableFormatter getDefaultTableStyle() {
        return defaultTableStyle;
    }

    public ParagraphFormatter getDefaultParagraphStyle() {
        return defaultParagraphStyle;
    }

    public StyleResolver(CellFormatter defaultCellStyle,
            TableFormatter defaultTableStyle,
            ParagraphFormatter defaultParagraphStyle) {
        this.defaultCellStyle = defaultCellStyle;
        this.defaultTableStyle = defaultTableStyle;
        this.defaultParagraphStyle = defaultParagraphStyle;
    }

    
    
    public void applyStyle(CreatesCellElement element, CellStyle style) {
        if(!style.styleClass().isEmpty()) {
            CellStyle classStyle = getStyleFromClass(style.styleClass(), CellStyle.class);
            //cellStyleParser.applyStyle(formatter, classStyle);
        }
        //cellStyleParser.applyStyle(formatter, style);
    }
    
    public void applyStyle(CreatesParagraphElement element, ParagraphStyle style) {
        
    }
    
    public void applyStyle(CreatesTableElement element, TableStyle style) {
        
    }
    
    private <T extends Annotation> T getStyleFromClass(String className, Class<T> annotationClass) {
        try {
            Class<?> styleClass = Class.forName(className);
            T style = styleClass.getAnnotation(annotationClass);
            if(style == null) {
                throw new InvalidStyleException("Class " + className + " has no " + annotationClass.getName());
            }
            return style;
        } catch (ClassNotFoundException e) {
            throw new InvalidStyleException("Couldn't read Style from class " 
                    + className +". Class not found");
        }
    } 
}
