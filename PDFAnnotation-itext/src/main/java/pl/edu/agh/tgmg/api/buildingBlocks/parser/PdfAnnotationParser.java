package pl.edu.agh.tgmg.api.buildingBlocks.parser;

import pl.edu.agh.tgmg.api.buildingBlocks.DocumentStructure;
import pl.edu.agh.tgmg.api.exceptions.AnnotationParserException;

/**
 * Main annotation parser interface
 * @author Tom
 *
 */
public interface PdfAnnotationParser {
    
    /**
     * Parsers the <code>root</code> class and retrieves the document structure.
     * @param root
     * @return the retrieved document structure.
     * @throws AnnotationParserException
     */
    DocumentStructure parse(Class<?> root) throws AnnotationParserException;
}
