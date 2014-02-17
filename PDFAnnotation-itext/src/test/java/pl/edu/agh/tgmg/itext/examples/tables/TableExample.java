package pl.edu.agh.tgmg.itext.examples.tables;

import java.lang.reflect.Field;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import pl.edu.agh.tgmg.api.buildingBlocks.parser.PdfTableHeaderParser;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.PdfTableParser;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.PdfTableRowParser;
import pl.edu.agh.tgmg.itext.examples.BuildingBlocksExample;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableElementWithStaticHeader;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableHeader;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableRow;
import pl.edu.agh.tgmg.itext.generators.styles.StyleResolver;


public abstract class TableExample extends BuildingBlocksExample <PdfTableElementWithStaticHeader>  {
    
    StyleResolver styleResolver = Mockito.mock(StyleResolver.class);

    public abstract PdfTableHeader getExpectedHeader();
    public abstract PdfTableRow getExpectedRow();
    public abstract TableDocumentWrapper<? extends TableExample> getDocummentWrapper();
    
    @Override
    public PdfTableElementWithStaticHeader getExpectedElement() {
        return new PdfTableElementWithStaticHeader(
            getExpectedHeader(),
            getExpectedRow());
    }
    
    @Override
    public Class<?> getDocumentWithThisExample() {
        return getDocumentWithThisExample().getClass();
    }
    
    @Override
    public Object getExampleDTO() {
        return getDocummentWrapper().getTable();
    }

    
    @Test
    public void checkHeaderExamples() {
        PdfTableHeaderParser headerParser = new PdfTableHeaderParser(styleResolver);
        PdfTableHeader header = headerParser.parse(this.getClass());
        Assert.assertEquals(header, getExpectedHeader());
    }
    
    @Test
    public void checkRowExamples() {
        PdfTableRowParser rowParser = new PdfTableRowParser(styleResolver);
        PdfTableRow row = rowParser.parse(this.getClass());
        Assert.assertEquals(row, getExpectedRow());
    }

    @Test
    @Override
    public void checkElementParsing() {
        PdfTableParser tableParser = new PdfTableParser(styleResolver);
        getDocummentWrapper();
        Field field = getDocummentWrapper().getTableField();
        PdfTableElementWithStaticHeader table = tableParser.parse(field);
        Assert.assertEquals(table, getExpectedElement());
    }
}
