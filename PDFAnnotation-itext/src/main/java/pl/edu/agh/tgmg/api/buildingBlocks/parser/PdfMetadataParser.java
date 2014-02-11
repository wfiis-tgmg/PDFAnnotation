package pl.edu.agh.tgmg.api.buildingBlocks.parser;

import pl.edu.agh.tgmg.api.annotations.PdfDocument;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentMetaData;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentMetaDataImpl;

public class PdfMetadataParser {
    public DocumentMetaData parse(PdfDocument document) {
        return new DocumentMetaDataImpl();
    }
}
