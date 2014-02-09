package pl.edu.agh.tgmg.itext.generators.buildingblocks;

import com.google.common.collect.Lists;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import pl.edu.agh.tgmg.api.PdfElement;
import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;

import java.util.Collections;

public class PdfTableElement implements PdfElement{

    PdfTableHeader pdfTableHeader;
    PdfTableRow pdfTableRow;


    public PdfTableElement(PdfTableHeader pdfTableHeader) {
        this.pdfTableHeader = pdfTableHeader;
    }

    public PdfTableElement(PdfTableRow pdfTableRow) {
        this.pdfTableRow = pdfTableRow;
    }

    public PdfTableElement(PdfTableHeader pdfTableHeader, PdfTableRow pdfTableRow) {
        this.pdfTableHeader = pdfTableHeader;
        this.pdfTableRow = pdfTableRow;
    }

    @Override
    public PdfPTable print(Object data) throws DocumentException {

        PdfPTable t = pdfTableHeader.createPdfTable();
        for (Object element : getIterable(data)) {
            for (PdfPCell c : pdfTableRow.print(element)) {
                t.addCell(c);
            }
        }
        return t;
    }

    private Iterable getIterable(Object data) {
        Iterable iter = Collections.emptyList();
        if(data instanceof  Iterable)
        {
           iter  = (Iterable)data;
        }
        else if(data.getClass().isArray())
        {
            iter = Lists.newArrayList((Object[]) data);
        } else new GenDocumentException();
        return iter;
    }


}
