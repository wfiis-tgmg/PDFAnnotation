package pl.edu.agh.tgmg.itext.generators.buildingblocks;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import pl.edu.agh.tgmg.api.CommonUtils;
import pl.edu.agh.tgmg.api.PdfElement;
import pl.edu.agh.tgmg.api.PdfTableElement;

import java.util.List;

public class PdfTableElementWithStaticHeader implements PdfTableElement {

    PdfTableHeader pdfTableHeader;
    PdfTableRow pdfTableRow;
    

    public PdfTableHeader getPdfTableHeader() {
		return pdfTableHeader;
	}

    public PdfTableElementWithStaticHeader(PdfTableHeader pdfTableHeader) {
        this.pdfTableHeader = pdfTableHeader;
    }

    public PdfTableElementWithStaticHeader(PdfTableRow pdfTableRow) {
        pdfTableHeader = new PdfTableHeader(pdfTableRow.getCells());
        this.pdfTableRow = pdfTableRow;
    }

    public PdfTableElementWithStaticHeader(PdfTableHeader pdfTableHeader, PdfTableRow pdfTableRow) {
        this.pdfTableHeader = pdfTableHeader;
        this.pdfTableRow = pdfTableRow;
    }

    @Override
    public PdfPTable print(Object dataList) throws DocumentException {

        PdfPTable t = pdfTableHeader.createPdfTable();
        for (Object dataRow : CommonUtils.getIterable(dataList)) {
            List<PdfPCell> cells = pdfTableRow.print(dataRow);
            for (PdfPCell c : cells) {
                c.setColspan(2);
                t.addCell(c);
            }
        }
        return t;
    }


}
