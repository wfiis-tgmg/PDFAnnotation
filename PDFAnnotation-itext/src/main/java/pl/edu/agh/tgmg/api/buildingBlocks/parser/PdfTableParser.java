package pl.edu.agh.tgmg.api.buildingBlocks.parser;

import java.lang.reflect.Field;

import pl.edu.agh.tgmg.api.BlankI18nResolverImpl;
import pl.edu.agh.tgmg.api.CommonUtils;
import pl.edu.agh.tgmg.api.I18nResolver;
import pl.edu.agh.tgmg.api.annotations.PdfTable;
import pl.edu.agh.tgmg.api.exceptions.InvalidGroupException;
import pl.edu.agh.tgmg.api.exceptions.InvalidTableGroupException;
import pl.edu.agh.tgmg.api.exceptions.ReflectionException;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableElementWithStaticHeader;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableHeader;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableRow;
import pl.edu.agh.tgmg.itext.generators.styles.StyleResolver;
import pl.edu.agh.tgmg.itext.generators.styles.StyleResolverImpl;

public class PdfTableParser {

    private StyleResolver styleResolver = new StyleResolverImpl();
    
    PdfTableHeaderParser headerParser;
    PdfTableRowParser rowParser;

    public PdfTableParser(StyleResolver styleResolver) {
        this(styleResolver, new BlankI18nResolverImpl());
    }

    public PdfTableParser(StyleResolver styleResolver,I18nResolver i18nResolver) {
        this.styleResolver = styleResolver;
        this.headerParser = new PdfTableHeaderParser(styleResolver,i18nResolver);
        this.rowParser = new PdfTableRowParser(styleResolver,i18nResolver);
    }

    public PdfTableElementWithStaticHeader parse(Field field) throws ReflectionException, InvalidTableGroupException, InvalidGroupException {
        Class<?> fieldClass = CommonUtils.getTypeParamOfIterableField(field);
        PdfTable pdfTable = field.getAnnotation(PdfTable.class);
        PdfTableHeader header = headerParser.parse(fieldClass);
        PdfTableRow row = rowParser.parse(fieldClass);
        styleResolver.applyStyle(header, pdfTable.tableStyle(), field.getDeclaringClass());
        return new PdfTableElementWithStaticHeader(header, row);
    }
}
