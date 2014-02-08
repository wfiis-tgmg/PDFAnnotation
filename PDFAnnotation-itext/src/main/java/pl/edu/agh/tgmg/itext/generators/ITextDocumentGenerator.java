package pl.edu.agh.tgmg.itext.generators;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import pl.edu.agh.tgmg.api.buildingBlocks.ColumnHeader;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentStructure;
import pl.edu.agh.tgmg.api.PdfDocument;
import pl.edu.agh.tgmg.api.buildingBlocks.generator.PdfDocumentGenerator;
import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;
import pl.edu.agh.tgmg.itext.generators.metadata.DefaultITextDocumentFactory;
import pl.edu.agh.tgmg.itext.generators.oldTable.DefaultITextRowGenerator;
import pl.edu.agh.tgmg.itext.generators.oldTable.DefaultItextHeaderDecorator;
import pl.edu.agh.tgmg.itext.generators.metadata.ITextDocumentFactory;
import pl.edu.agh.tgmg.itext.generators.oldTable.ITextHeaderDecorator;
import pl.edu.agh.tgmg.itext.generators.oldTable.ITextRowGenerator;

import java.io.OutputStream;
import java.util.List;

public class ITextDocumentGenerator implements PdfDocumentGenerator {

    ITextDocumentFactory documentFactory = new DefaultITextDocumentFactory();
    ITextHeaderDecorator headerDecorator = new DefaultItextHeaderDecorator();
    ITextRowGenerator rowDecorator = new DefaultITextRowGenerator();



    @Override
    public void generate(OutputStream out, List<? extends PdfDocument> data, DocumentStructure documentStructure) throws GenDocumentException {

        try
        {
        Document document = documentFactory.create(out,documentStructure.getMetaData());
        //for po polach klasy

        Element table = createTable(data, documentStructure);
        document.add(table);

        document.close();

        } catch (DocumentException e) {
            throw new GenDocumentException(e);
        }
    }

    private PdfPTable createTable(List<? extends PdfDocument> data, DocumentStructure documentStructure) {
        int allSubColumns = 0;
        for (ColumnHeader h : documentStructure.getHeaders()) {
            allSubColumns += h.getSubColumnAmount();
        }

        if(allSubColumns<1) throw new GenDocumentException("The number of columns in PdfPTable constructor must be greater");

        PdfPTable table = new PdfPTable(allSubColumns);
        headerDecorator.decorate(table,documentStructure.getHeaders());
        rowDecorator.decorate(table,documentStructure.getCellRow(),data);
        return table;
    }
}
