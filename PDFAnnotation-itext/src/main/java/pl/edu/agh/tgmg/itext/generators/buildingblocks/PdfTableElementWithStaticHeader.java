package pl.edu.agh.tgmg.itext.generators.buildingblocks;

import java.util.List;

import pl.edu.agh.tgmg.api.CommonUtils;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class PdfTableElementWithStaticHeader implements PdfTableElement {

    PdfTableHeader pdfTableHeader;
    PdfTableRow pdfTableRow;
    String listFieldName;
    
    public PdfTableHeader getPdfTableHeader() {
        return pdfTableHeader;
    }
    

    public PdfTableRow getPdfTableRow() {
        return pdfTableRow;
    }

    public PdfTableElementWithStaticHeader(PdfTableHeader pdfTableHeader) {
        this.pdfTableHeader = pdfTableHeader;
    }

    public PdfTableElementWithStaticHeader(PdfTableRow pdfTableRow) {
        pdfTableHeader = new PdfTableHeader(pdfTableRow.getColumnCount());
        this.pdfTableRow = pdfTableRow;
    }

    public PdfTableElementWithStaticHeader(PdfTableHeader pdfTableHeader, PdfTableRow pdfTableRow) {
        this.pdfTableHeader = pdfTableHeader;
        this.pdfTableRow = pdfTableRow;
    }
    
    public void setListFieldName(String listFieldName) {
        this.listFieldName = listFieldName;
    }

    @Override
    public PdfPTable print(Object dataList) throws DocumentException {

        PdfPTable t = pdfTableHeader.createPdfTable();

        Object iter = dataList;
        if(listFieldName != null) {
            iter = CommonUtils.getValue(dataList, listFieldName);
        }
        
        for (Object dataRow : CommonUtils.getIterable(iter)) {
            List<PdfPCell> cells = pdfTableRow.printCells(dataRow);
            for (PdfPCell c : cells) {
             //   c.setColspan(pdfTableHeader.getColumns());
                t.addCell(c);
            }
        }
        return t;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((pdfTableHeader == null) ? 0 : pdfTableHeader.hashCode());
        result = prime * result
                + ((pdfTableRow == null) ? 0 : pdfTableRow.hashCode());
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
        PdfTableElementWithStaticHeader other = (PdfTableElementWithStaticHeader) obj;
        if (pdfTableHeader == null) {
            if (other.pdfTableHeader != null)
                return false;
        } else if (!pdfTableHeader.equals(other.pdfTableHeader))
            return false;
        if (pdfTableRow == null) {
            if (other.pdfTableRow != null)
                return false;
        } else if (!pdfTableRow.equals(other.pdfTableRow))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "PdfTableElementWithStaticHeader [pdfTableHeader="
                + pdfTableHeader + ", pdfTableRow=" + pdfTableRow + "]";
    }


    @Override
    public int getColumnCount() {
        if(pdfTableRow != null) {
            return pdfTableRow.getColumnCount();
        }
        return pdfTableHeader.getColumnCount();
    }
}
