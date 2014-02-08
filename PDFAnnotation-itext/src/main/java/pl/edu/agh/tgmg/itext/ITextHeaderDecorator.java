package pl.edu.agh.tgmg.itext;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import pl.edu.agh.tgmg.api.ColumnHeader;

import java.util.List;

public interface ITextHeaderDecorator {
    void decorate(PdfPTable document, List<ColumnHeader> headers);
}
