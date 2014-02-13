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

    public int getHorizontalAlignment() {
        return horizontalAlignment;
    }

    public float getSpacingAfter() {
        return spacingAfter;
    }

    public float getSpacingBefore() {
        return spacingBefore;
    }

    public float getWidthPercentage() {
        return widthPercentage;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + horizontalAlignment;
        result = prime * result + Float.floatToIntBits(spacingAfter);
        result = prime * result + Float.floatToIntBits(spacingBefore);
        result = prime * result + Float.floatToIntBits(widthPercentage);
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
        TableFormatter other = (TableFormatter) obj;
        if (horizontalAlignment != other.horizontalAlignment)
            return false;
        if (Float.floatToIntBits(spacingAfter) != Float
                .floatToIntBits(other.spacingAfter))
            return false;
        if (Float.floatToIntBits(spacingBefore) != Float
                .floatToIntBits(other.spacingBefore))
            return false;
        if (Float.floatToIntBits(widthPercentage) != Float
                .floatToIntBits(other.widthPercentage))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TableFormatter [horizontalAlignment=" + horizontalAlignment
                + ", spacingAfter=" + spacingAfter + ", spacingBefore="
                + spacingBefore + ", widthPercentage=" + widthPercentage + "]";
    }
    
}
