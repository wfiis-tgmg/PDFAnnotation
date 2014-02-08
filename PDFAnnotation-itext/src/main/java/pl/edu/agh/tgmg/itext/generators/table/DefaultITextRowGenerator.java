package pl.edu.agh.tgmg.itext.generators.table;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.CellRow;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.CellWrapper;
import pl.edu.agh.tgmg.itext.generators.table.ITextRowGenerator;

import java.util.List;

public class DefaultITextRowGenerator implements ITextRowGenerator {
    @Override
    public void decorate(PdfPTable table, List<CellRow> cellRow, List data) {
        for (Object obj : data) {
            for (CellRow row : cellRow) {
                CellWrapper cell = row.getCell(obj);
                Object value = cell.getValue();
                switch (cell.getType()) {
                    case PHRASE:
                        table.addCell((Phrase) value);
                        break;
                    case TABLE:
                        table.addCell((PdfPTable) value);
                        break;
                }
            }
        }

    }
}
