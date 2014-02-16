package pl.edu.agh.tgmg.itext.examples.errors;

import java.util.List;

import org.mockito.Mockito;

import pl.edu.agh.tgmg.api.PdfElement;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.PdfAnnotationParser;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.PdfAnnotationParserImpl;
import pl.edu.agh.tgmg.itext.generators.styles.StyleResolver;

public abstract class DocumentErrorTest extends ErrorTest {

    StyleResolver styleResolver = Mockito.mock(StyleResolver.class);
    PdfAnnotationParser parser = new PdfAnnotationParserImpl(styleResolver);
    
    @Override
    public List<? extends PdfElement> parseDocument(Class<?> clazz) {
        return parser.parse(clazz).getPdfElements();
    }

}
