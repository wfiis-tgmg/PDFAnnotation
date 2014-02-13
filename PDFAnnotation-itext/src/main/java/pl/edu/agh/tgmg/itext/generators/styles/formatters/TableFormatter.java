package pl.edu.agh.tgmg.itext.generators.styles.formatters;

import java.util.Arrays;
import java.util.List;

import pl.edu.agh.tgmg.api.annotations.styles.TableStyle;

import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPTable;

public class TableFormatter implements StyleFormatter<PdfPTable, TableStyle> {

    private int horizontalAlignment = Element.ALIGN_UNDEFINED;
    private float spacingAfter = 0.5f;
    private float spacingBefore = 0.5f;
    private float widthPercentage = 100.0f;

    @Override
    public void addStyle(PdfPTable table) {

        table.setHorizontalAlignment(horizontalAlignment);
        table.setSpacingAfter(spacingAfter);
        table.setSpacingBefore(spacingBefore);
        table.setWidthPercentage(widthPercentage);
    }

    public TableFormatter() {
    }

    public TableFormatter(int alignment, float spacingAfter,
            float spacingBefore, float widthPercentage) {
        this.horizontalAlignment = alignment;
        this.spacingAfter = spacingAfter;
        this.spacingBefore = spacingBefore;
        this.widthPercentage = widthPercentage;
    }

    public void setHorizontalAlignment(int horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
    }

    public void setSpacingAfter(float spacingAfter) {
        this.spacingAfter = spacingAfter;
    }

    public void setSpacingBefore(float spacingBefore) {
        this.spacingBefore = spacingBefore;
    }

    public void setWidthPercentage(float widthPercentage) {
        this.widthPercentage = widthPercentage;
    }

    @Override
    public Class<TableStyle> getFormatterStyleClass() {
        return TableStyle.class;
    }

    @Override
    public List<String> getValueNames() {
        return Arrays.asList(
            "horizontalAlignment",
            "spacingAfter",
            "spacingBefore",
            "widthPercentage");
    }

}
