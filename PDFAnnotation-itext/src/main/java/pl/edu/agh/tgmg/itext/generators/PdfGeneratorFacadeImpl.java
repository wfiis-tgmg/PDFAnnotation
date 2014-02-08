package pl.edu.agh.tgmg.itext.generators;

import pl.edu.agh.tgmg.api.buildingBlocks.DocumentStructure;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.PdfAnnotationParser;
import pl.edu.agh.tgmg.api.PdfDocument;
import pl.edu.agh.tgmg.api.buildingBlocks.generator.PdfDocumentGenerator;
import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;

import java.io.OutputStream;
import java.util.List;

public class PdfGeneratorFacadeImpl implements PdfGeneratorFacede {

    PdfAnnotationParser annotationParser;
    PdfDocumentGenerator pdfDocumentGenerator;

    @Override
    public void generate(OutputStream out, List<PdfDocument> dto) throws GenDocumentException {
        Class<? extends PdfDocument> dtoClazz = dto.get(0).getClass();
        DocumentStructure structure = annotationParser.parse(dtoClazz);
        pdfDocumentGenerator.generate(out,dto,structure);
    }
}
