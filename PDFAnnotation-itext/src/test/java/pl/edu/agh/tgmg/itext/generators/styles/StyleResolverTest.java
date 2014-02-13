package pl.edu.agh.tgmg.itext.generators.styles;

import java.lang.reflect.Method;

import junit.framework.Assert;

import org.testng.annotations.Test;

import pl.edu.agh.tgmg.api.annotations.PdfParagraph;
import pl.edu.agh.tgmg.api.annotations.styles.CellHeaderStyle;
import pl.edu.agh.tgmg.api.annotations.styles.CellRowStyle;
import pl.edu.agh.tgmg.api.annotations.styles.ParagraphStyle;
import pl.edu.agh.tgmg.api.annotations.styles.TableStyle;
import pl.edu.agh.tgmg.api.annotations.styles.elements.FontFamily;
import pl.edu.agh.tgmg.api.annotations.styles.elements.HorizontalAlignment;
import pl.edu.agh.tgmg.api.annotations.styles.elements.PdfBoxValuesB;
import pl.edu.agh.tgmg.api.annotations.styles.elements.PdfColor;
import pl.edu.agh.tgmg.api.exceptions.InvalidStyleException;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.ParagraphElement;
import pl.edu.agh.tgmg.itext.generators.styles.examples.SimpleStyleExample;
import pl.edu.agh.tgmg.itext.generators.styles.examples.StyleExample2;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.ParagraphFormatter;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.TableFormatter;
import pl.edu.agh.tgmg.itext.generators.styles.parser.SmallElementsParser;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;

public class StyleResolverTest {
    
    @Test
    public void applyStyleAll() throws NoSuchFieldException, SecurityException {
        ParagraphElement paragraph = new ParagraphElement("asd");
        PdfParagraph par = SimpleStyleExample.class.getField("str").getAnnotation(PdfParagraph.class);
        ParagraphFormatter expected = new ParagraphFormatter(12, new BaseColor(12, 58 ,83), Font.FontFamily.ZAPFDINGBATS, 23, 12, 0.5f, 3, 1, 87f, 13f);
        ParagraphFormatter defaultFormatter = new ParagraphFormatter();
        defaultFormatter.setExtraSpace(23);
        defaultFormatter.setFontColor(BaseColor.RED);
        StyleResolver styleResolver = new StyleResolverImpl();
        styleResolver.setDefaltStyle(ParagraphStyle.class,  defaultFormatter);
        styleResolver.applyStyle(paragraph, par.style(), SimpleStyleExample.class);
        ParagraphFormatter actual = (ParagraphFormatter) paragraph.getFormatter();
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void getStyleClass() {
        TableStyle style1 = SimpleStyleExample.class.getAnnotation(TableStyle.class);
        CellHeaderStyle style2 = SimpleStyleExample.class.getAnnotation(CellHeaderStyle.class);
        CellRowStyle style3 = SimpleStyleExample.class.getAnnotation( CellRowStyle.class);
        ParagraphStyle style4 = SimpleStyleExample.class.getAnnotation(ParagraphStyle.class);
        Assert.assertTrue(StyleResolverImpl.getStyleClass(style1).isAssignableFrom(StyleResolver.class));
        Assert.assertEquals(Class.class, StyleResolverImpl.getStyleClass(style2));
        Assert.assertTrue(StyleResolverImpl.getStyleClass(style3).isAssignableFrom(Double.class));
        Assert.assertTrue(StyleResolverImpl.getStyleClass(style4).isAssignableFrom(StyleExample2.class));
    }
    
    @Test(expectedExceptions=InvalidStyleException.class, 
            expectedExceptionsMessageRegExp="annotation style .* is not defined")
    public void getStyleClassError() {
        Test style;
        try {
            style = this.getClass().getMethod("getStyleClass").getAnnotation(Test.class);
        } catch (NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            Assert.fail("Test is faulty");
            return;
        }
        StyleResolverImpl.getStyleClass(style);
    }
    
    @Test
    public void applyStyleLast() {
        TableStyle style = SimpleStyleExample.class.getAnnotation(TableStyle.class);
        TableFormatter formatter = new TableFormatter();
        TableFormatter expected = new TableFormatter(2, 15f, 0.5f, 20f);
        StyleResolverImpl.applyStyle(formatter, style);
        Assert.assertEquals(expected, formatter);
    }
    
    @Test
    public void setObject() throws NoSuchMethodException, SecurityException {
        TableFormatter formatter = new TableFormatter();
        Method getter = TableStyle.class.getMethod("spacingAfter");
        float[] values = { 43f, 12f };
        StyleResolverImpl.setObject(formatter, getter, values);
        Assert.assertEquals(43f, formatter.getSpacingAfter(), 0.00001);
    }
    
    @Test
    public void setObject2() throws NoSuchMethodException, SecurityException {
        TableFormatter formatter = new TableFormatter();
        Method getter = TableStyle.class.getMethod("horizontalAlignment");
        HorizontalAlignment[] values = { HorizontalAlignment.RIGHT, HorizontalAlignment.LEFT };
        StyleResolverImpl.setObject(formatter, getter, values);
        Assert.assertEquals(2, formatter.getHorizontalAlignment());
    }
    
    @Test
    public void testGetParserMethod() throws NoSuchMethodException, SecurityException {
        Assert.assertEquals(SmallElementsParser.class.getMethod("parse", PdfColor.class), StyleResolverImpl.getParserMethod(PdfColor.class));
        Assert.assertEquals(SmallElementsParser.class.getMethod("parse", PdfBoxValuesB.class), StyleResolverImpl.getParserMethod(PdfBoxValuesB.class));
        Assert.assertEquals(SmallElementsParser.class.getMethod("parse", FontFamily.class), StyleResolverImpl.getParserMethod(FontFamily.class));
        Assert.assertNull(StyleResolverImpl.getParserMethod(StyleResolverImpl.class));
        Assert.assertNull(StyleResolverImpl.getParserMethod(String.class));
        Assert.assertNull(StyleResolverImpl.getParserMethod(StyleResolverTest.class));
    }
}
