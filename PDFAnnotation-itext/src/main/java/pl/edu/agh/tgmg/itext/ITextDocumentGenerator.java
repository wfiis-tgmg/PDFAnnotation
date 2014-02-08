package pl.edu.agh.tgmg.itext;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import pl.edu.agh.tgmg.api.ColumnHeader;
import pl.edu.agh.tgmg.api.DocumentStructure;
import pl.edu.agh.tgmg.api.PdfContainer;
import pl.edu.agh.tgmg.api.PdfDocumentGenerator;
import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;

import java.io.OutputStream;
import java.util.List;

public class ITextDocumentGenerator implements PdfDocumentGenerator {

    ITextDocumentFactory documentFactory = new DefaultITextDocumentFactory();
    ITextHeaderDecorator headerDecorator = new DefaultItextHeaderDecorator();
    ITextRowDecorator rowDecorator = new DefaultITextRowDecorator();



    @Override
    public void generate(OutputStream out, List<? extends PdfContainer> data, DocumentStructure documentStructure) throws GenDocumentException {

        try
        {
        Document document = documentFactory.create(out,documentStructure.getMetaData());
        headerDecorator.decorate(document,documentStructure.getHeaders());


        //generate header


        int allSubColumns = 0;
        for (ColumnHeader h : documentStructure.getHeaders()) {
            allSubColumns += h.getSubColumnAmount();
        }

        PdfPTable table = new PdfPTable(allSubColumns);
//        table.setWidthPercentage(90);
//        table.setTotalWidth(500.0f);
//        table.setLockedWidth(true);
//        table.setHorizontalAlignment(Element.ALIGN_CENTER);

        for (ColumnHeader h : documentStructure.getHeaders()) {
            PdfPCell cell = new PdfPCell(new Phrase(h.getName()));
            if(h.getSubColumnAmount() > 1)
            cell.setColspan(h.getSubColumnAmount());
            table.addCell(cell);
        }
        //        cell.setFixedHeight(2.5f*cellHeight);
//        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);

        //generate rows;


        document.add(table);
        document.newPage();


        document.close();

        } catch (DocumentException e) {
            throw new GenDocumentException(e);
        }
    }
}
