package pl.edu.agh.tgmg.itext;

import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import pl.edu.agh.tgmg.api.CellRow;
import pl.edu.agh.tgmg.api.CellWrapper;

import java.util.List;

public class DefaultITextRowDecorator implements ITextRowDecorator {
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
