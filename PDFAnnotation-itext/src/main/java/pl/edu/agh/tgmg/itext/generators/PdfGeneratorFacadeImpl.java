package pl.edu.agh.tgmg.itext.generators;

import java.io.OutputStream;

import pl.edu.agh.tgmg.api.PdfElement;
import pl.edu.agh.tgmg.api.PdfGeneratorFacade;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentStructure;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.PdfAnnotationParser;
import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;
import pl.edu.agh.tgmg.itext.generators.metadata.ITextDocumentFactory;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

public class PdfGeneratorFacadeImpl implements PdfGeneratorFacade {

    PdfAnnotationParser annotationParser;
    ITextDocumentFactory documentFactory;

    public PdfGeneratorFacadeImpl(PdfAnnotationParser annotationParser, ITextDocumentFactory documentFactory) {
        this.annotationParser = annotationParser;
        this.documentFactory = documentFactory;
    }

    @Override
    public void generate(OutputStream out, Object dto) throws GenDocumentException {

        try {
            DocumentStructure structure = annotationParser.parse(dto.getClass());
            Document document = documentFactory.create(out, structure.getMetaData());

            for (PdfElement pdfElement : structure.getPdfElements()) {
                document.add(pdfElement.print(dto));
            }

            documentFactory.close(document);
        } catch (DocumentException e) {
            throw new GenDocumentException(e);
        }
    }
}
