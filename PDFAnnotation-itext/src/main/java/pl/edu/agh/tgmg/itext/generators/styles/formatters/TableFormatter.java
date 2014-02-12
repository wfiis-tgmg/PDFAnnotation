package pl.edu.agh.tgmg.itext.generators.styles.formatters;

import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPTable;

public class TableFormatter implements StyleFormatter<PdfPTable> {

    private int alignment = Element.ALIGN_UNDEFINED;
    private float spacingAfter = 0.5f;
    private float spacingBefore = 0.5f;

    @Override
    public void addStyle(PdfPTable table) {

        table.setHorizontalAlignment(alignment);
        table.setSpacingAfter(spacingAfter);
        table.setSpacingBefore(spacingBefore);
    }

    public TableFormatter() {
    }

    public TableFormatter(int alignment, float spacingAfter, float spacingBefore) {
        this.alignment = alignment;
        this.spacingAfter = spacingAfter;
        this.spacingBefore = spacingBefore;
    }

    public void setAlignment(int alignment) {
        this.alignment = alignment;
    }

    public void setSpacingAfter(float spacingAfter) {
        this.spacingAfter = spacingAfter;
    }

    public void setSpacingBefore(float spacingBefore) {
        this.spacingBefore = spacingBefore;
    }
}
