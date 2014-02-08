package pl.edu.agh.tgmg.itext.generators.oldTable;

import com.itextpdf.text.pdf.PdfPTable;
import pl.edu.agh.tgmg.api.buildingBlocks.ColumnHeader;

import java.util.List;

public interface ITextHeaderDecorator {
    void decorate(PdfPTable document, List<ColumnHeader> headers);
}
