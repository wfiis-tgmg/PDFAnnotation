package pl.edu.agh.tgmg.itext.generators.buildingblocks;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import pl.edu.agh.tgmg.api.CommonUtils;
import pl.edu.agh.tgmg.api.PdfTableElement;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.CellRow;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.CellWrapper;
import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;
import pl.edu.agh.tgmg.itext.wrapper.ITextTableWrapper;

import java.util.List;

public class PdfTableWithDynamicHeader implements PdfTableElement, CellRow {

    SingleDataTable singleDataTable;
    PdfTableRow pdfTableRow;
    String listFieldName;

    public PdfTableWithDynamicHeader(String listFieldName, PdfTableRow pdfTableRow, SingleDataTable singleDataTable) {
        this.listFieldName = listFieldName;
        this.pdfTableRow = pdfTableRow;
        this.singleDataTable = singleDataTable;
    }

    @Override
    public PdfPTable print(Object dataList) throws DocumentException {

        PdfPTable t = singleDataTable.createPdfTable(dataList);
        Object iter = CommonUtils.getValue(dataList, listFieldName);
        for (Object dataRow : CommonUtils.getIterable(iter)) {
            List<PdfPCell> cells = pdfTableRow.print(dataRow);
            for (PdfPCell c : cells) {
                t.addCell(c);
            }
        }
        return t;
    }

    @Override
    public CellWrapper getCell(Object o) {

        try {
            return new ITextTableWrapper(print(o));
        } catch (DocumentException e) {
            throw new GenDocumentException();
        }
    }

    @Override
    public String getName() {

        return listFieldName;
    }
}
