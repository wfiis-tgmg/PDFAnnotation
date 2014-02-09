package pl.edu.agh.tgmg.itext.generators.buildingblocks;

import com.google.common.collect.Lists;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.CellRow;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.CellWrapper;
import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PdfTableRow {


    List<CellRow> cellRows = Collections.emptyList();

    public PdfTableRow(List<CellRow> cellRows) {
        this.cellRows = cellRows;
    }

    public PdfTableRow(CellRow ... cellRows) {
        this.cellRows = Lists.newArrayList(cellRows);
    }



    public List<PdfPCell> print(Object data) {

        List<PdfPCell> res = new LinkedList<PdfPCell>();

        for (CellRow row : cellRows) {
            res.add(new PdfPCell(cast(row.getCell(data))));
        }

        return res;
    }

    private PdfPCell cast(CellWrapper cellWrapper) {
        Object value1 = cellWrapper.getValue();
        switch (cellWrapper.getType()) {
            case PHRASE:
                return new PdfPCell((Phrase) value1);
            case TABLE:
                PdfPCell pdfPCell = new PdfPCell((PdfPTable) value1);
                return pdfPCell;
        }
        throw new GenDocumentException();
    }

    public int getCells() {
        return cellRows.size();
    }
}
