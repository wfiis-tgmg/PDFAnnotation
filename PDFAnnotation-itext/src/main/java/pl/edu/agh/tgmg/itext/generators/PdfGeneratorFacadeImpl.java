package pl.edu.agh.tgmg.itext.generators;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import pl.edu.agh.tgmg.api.PdfElement;
import pl.edu.agh.tgmg.api.PdfGeneratorFacede;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentStructure;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.PdfAnnotationParser;
import pl.edu.agh.tgmg.api.annotations.PdfDocument;
import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;
import pl.edu.agh.tgmg.itext.generators.metadata.DefaultITextDocumentFactory;
import pl.edu.agh.tgmg.itext.generators.metadata.ITextDocumentFactory;

import java.io.OutputStream;

public class PdfGeneratorFacadeImpl implements PdfGeneratorFacede {

    PdfAnnotationParser annotationParser;
    ITextDocumentFactory documentFactory = new DefaultITextDocumentFactory();


    @Override

    public void generate(OutputStream out, PdfDocument dto) throws GenDocumentException {

        try {
            DocumentStructure structure = annotationParser.parse(dto.getClass());
            Document document = documentFactory.create(out, structure.getMetaData());

            for (PdfElement pdfElement : structure.getPdfElements()) {
                pdfElement.print(dto);
            }

            documentFactory.close(document);
        } catch (DocumentException e) {
            throw new GenDocumentException(e);
        }
    }
}
