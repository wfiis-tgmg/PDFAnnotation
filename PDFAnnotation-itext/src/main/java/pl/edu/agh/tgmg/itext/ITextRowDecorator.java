package pl.edu.agh.tgmg.itext;

import com.itextpdf.text.pdf.PdfPTable;
import pl.edu.agh.tgmg.api.CellRow;

import java.util.List;

public interface ITextRowDecorator {

    void decorate(PdfPTable table, List<CellRow> cellRow, List data);
}
