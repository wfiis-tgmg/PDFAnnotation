package pl.edu.agh.tgmg.itext.generators.table;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import pl.edu.agh.tgmg.api.buildingBlocks.ColumnHeader;
import pl.edu.agh.tgmg.itext.generators.table.ITextHeaderDecorator;

import java.util.List;

public class DefaultItextHeaderDecorator implements ITextHeaderDecorator {

    @Override
    public void decorate(PdfPTable table, List<ColumnHeader> headers) {
        for (ColumnHeader h : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(h.getName()));
            if(h.getSubColumnAmount() > 1)
                cell.setColspan(h.getSubColumnAmount());
            table.addCell(cell);
        }
    }
}
