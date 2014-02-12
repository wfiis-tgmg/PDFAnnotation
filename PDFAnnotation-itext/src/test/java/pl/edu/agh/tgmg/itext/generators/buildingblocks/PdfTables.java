package pl.edu.agh.tgmg.itext.generators.buildingblocks;

import java.util.List;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class PdfTables {
    public static void addCells(PdfPTable element, List<PdfPCell> print) {
        for (PdfPCell pdfPCell : print) {
            element.addCell(pdfPCell);
        }
    }
}
