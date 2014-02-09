package pl.edu.agh.tgmg.api.buildingBlocks.parser;

import pl.edu.agh.tgmg.api.annotations.PdfDocument;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentStructure;

public interface PdfAnnotationParser {
    DocumentStructure parse(Class<? extends PdfDocument> root);
}
