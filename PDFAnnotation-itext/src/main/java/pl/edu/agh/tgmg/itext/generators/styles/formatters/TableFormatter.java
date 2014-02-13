package pl.edu.agh.tgmg.itext.generators.styles.formatters;

import pl.edu.agh.tgmg.api.annotations.styles.TableStyle;

import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPTable;

public class TableFormatter implements StyleFormatter<PdfPTable, TableStyle> {

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


    @Override
    public void setStyle(StyleFormatter<PdfPTable, TableStyle> other) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Class<TableStyle> getFormatterStyleClass() {
        // TODO Auto-generated method stub
        return TableStyle.class;
    }
}
