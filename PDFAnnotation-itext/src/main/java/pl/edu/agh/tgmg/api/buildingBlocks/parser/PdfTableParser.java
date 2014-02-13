package pl.edu.agh.tgmg.api.buildingBlocks.parser;

import java.lang.reflect.Field;

import pl.edu.agh.tgmg.api.annotations.PdfTable;
import pl.edu.agh.tgmg.api.exceptions.InvalidGroupException;
import pl.edu.agh.tgmg.api.exceptions.InvalidTableGroupException;
import pl.edu.agh.tgmg.api.exceptions.ReflectionException;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableElementWithStaticHeader;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableHeader;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableRow;
import pl.edu.agh.tgmg.itext.generators.styles.StyleResolver;

public class PdfTableParser {

    private StyleResolver styleResolver = new StyleResolver();
    
    PdfTableHeaderParser headerParser;
    PdfTableRowParser rowParser;
   
    public PdfTableParser(StyleResolver styleResolver) {
        this.styleResolver = styleResolver;
        this.headerParser = new PdfTableHeaderParser(styleResolver);
        this.rowParser = new PdfTableRowParser(styleResolver);
    }

    public PdfTableElementWithStaticHeader parseTable(Field field) throws ReflectionException, InvalidTableGroupException, InvalidGroupException {
        Class<?> fieldClass = field.getType();
        PdfTable pdfTable = field.getAnnotation(PdfTable.class);
        PdfTableHeader header = headerParser.parse(fieldClass);
        PdfTableRow row = rowParser.parse(fieldClass);
        styleResolver.applyStyle(header, pdfTable.tableStyle(), field.getDeclaringClass());
        return new PdfTableElementWithStaticHeader(header, row);
    }
}
