package pl.edu.agh.tgmg.itext.generators.buildingblocks;

import java.util.List;

import pl.edu.agh.tgmg.api.CommonUtils;
import pl.edu.agh.tgmg.api.PdfTableElement;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.CellRow;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.CellWrapper;
import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;
import pl.edu.agh.tgmg.itext.wrapper.ITextTableWrapper;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class PdfTableWithDynamicHeader implements PdfTableElement, CellRow {

    SingleDataTable singleDataTable;
    PdfTableRow pdfTableRow;
    String listFieldName;

    public PdfTableWithDynamicHeader(String listFieldName, PdfTableRow pdfTableRow, SingleDataTable singleDataTable) {
        this.listFieldName = listFieldName;
        this.pdfTableRow = pdfTableRow;
        this.singleDataTable = singleDataTable;
    }
    
    public SingleDataTable getSingleDataTable() {
        return singleDataTable;
    }
    
    public PdfTableRow getPdfTableRow() {
        return pdfTableRow;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((listFieldName == null) ? 0 : listFieldName.hashCode());
        result = prime * result
                + ((pdfTableRow == null) ? 0 : pdfTableRow.hashCode());
        result = prime * result
                + ((singleDataTable == null) ? 0 : singleDataTable.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PdfTableWithDynamicHeader other = (PdfTableWithDynamicHeader) obj;
        if (listFieldName == null) {
            if (other.listFieldName != null)
                return false;
        } else if (!listFieldName.equals(other.listFieldName))
            return false;
        if (pdfTableRow == null) {
            if (other.pdfTableRow != null)
                return false;
        } else if (!pdfTableRow.equals(other.pdfTableRow))
            return false;
        if (singleDataTable == null) {
            if (other.singleDataTable != null)
                return false;
        } else if (!singleDataTable.equals(other.singleDataTable))
            return false;
        return true;
    }
    
    
}
