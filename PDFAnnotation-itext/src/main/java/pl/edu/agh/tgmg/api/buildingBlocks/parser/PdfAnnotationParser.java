package pl.edu.agh.tgmg.api.buildingBlocks.parser;

import pl.edu.agh.tgmg.api.buildingBlocks.DocumentStructure;
import pl.edu.agh.tgmg.api.exceptions.AnnotationParserException;

public interface PdfAnnotationParser {
    DocumentStructure parse(Class<?> root) throws AnnotationParserException;
}
