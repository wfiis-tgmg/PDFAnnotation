package pl.edu.agh.tgmg.itext.generators.buildingblocks;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.util.List;

public class PdfTables {
    public static void addCells(PdfPTable element, List<PdfPCell> print) {
        for (PdfPCell pdfPCell : print) {
            element.addCell(pdfPCell);
        }
    }
}
