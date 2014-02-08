package pl.edu.agh.tgmg.itext.generators.table;

import com.itextpdf.text.pdf.PdfPTable;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.CellRow;

import java.util.List;

public interface ITextRowGenerator {

    void decorate(PdfPTable table, List<CellRow> cellRow, List data);
}
