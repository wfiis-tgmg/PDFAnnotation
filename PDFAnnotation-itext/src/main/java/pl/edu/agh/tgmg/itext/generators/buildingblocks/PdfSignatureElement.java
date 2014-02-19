package pl.edu.agh.tgmg.itext.generators.buildingblocks;

import pl.edu.agh.tgmg.api.PdfElement;
import pl.edu.agh.tgmg.api.annotations.ToTest;
import pl.edu.agh.tgmg.api.annotations.styles.elements.HorizontalAlignment;
import pl.edu.agh.tgmg.api.exceptions.ReflectionException;
import pl.edu.agh.tgmg.utlis.CommonUtils;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

@ToTest     // TODO Test
public class PdfSignatureElement implements PdfElement {

    private enum CellPosition { TOP, CENTER, BOTTOM }
    
    String title;
    String description;
    String staticSignature;
    String dataFieldName;
    
    HorizontalAlignment horizontalAlignment = HorizontalAlignment.RIGHT; //FIXME move to styles
    Boolean useBorders = false;     //FIXME move to styles
    Boolean useDottedLine = true;   //FIXME move to styles
    float contentPadding = 4.0f;    //FIXME move to styles

    public PdfSignatureElement(String title, String description, 
            String staticSignature, String dataFieldName) {
        super();
        this.title = title;
        this.description = description;
        this.staticSignature = staticSignature;
        this.dataFieldName = dataFieldName;
    }

    @Override
    public Element print(Object data) throws DocumentException,
            ReflectionException {
        if(dataFieldName != null && !dataFieldName.isEmpty()) {
            staticSignature = CommonUtils.getValue(data, dataFieldName).toString();
        }
        return createTable();
    }
    
    private PdfPTable createTable() {
        int rowSpan = useDottedLine ? 4 : 3;
        
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        
        if(horizontalAlignment != HorizontalAlignment.LEFT) {
            addEmptyCell(table, rowSpan);
        }
        if(horizontalAlignment == HorizontalAlignment.RIGHT) {
            addEmptyCell(table, rowSpan);
        }
        addTextCell(table, title, CellPosition.TOP);
        if(horizontalAlignment != HorizontalAlignment.RIGHT) {
            addEmptyCell(table, rowSpan);
        }
        if(horizontalAlignment == HorizontalAlignment.LEFT) {
            addEmptyCell(table, rowSpan);
        }
        addTextCell(table, staticSignature, CellPosition.CENTER);
        
        if(useDottedLine) {
            addDottedLine(table);
        }
        addTextCell(table, description, CellPosition.BOTTOM);
        return table;
    }
    
    private void addTextCell(PdfPTable table, String text, CellPosition cellPosition) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setPadding(contentPadding*2);  
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        setBorders(cell, cellPosition);
        table.addCell(cell);
    }
    
    private void addEmptyCell(PdfPTable table, int rowSpan) {
        PdfPCell cell = new PdfPCell();
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setRowspan(rowSpan);
        table.addCell(cell);
    }
    
    private void addDottedLine(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.addElement(new DottedLineSeparator());
        cell.setBorder(PdfPCell.NO_BORDER);
        setBorders(cell, CellPosition.CENTER);
        table.addCell(cell);
    }

    private void setBorders(PdfPCell cell, CellPosition cellPosition) {
        if(useBorders) {
            int border = PdfPCell.LEFT | PdfPCell.RIGHT;
            switch(cellPosition) {
            case BOTTOM:
                border |= PdfPCell.BOTTOM;
                break;
            case TOP:
                border |= PdfPCell.TOP;
            default:
            }
            cell.setBorder(border);
        } else {
            cell.setBorder(PdfPCell.NO_BORDER);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((dataFieldName == null) ? 0 : dataFieldName.hashCode());
        result = prime * result
                + ((description == null) ? 0 : description.hashCode());
        result = prime * result
                + ((staticSignature == null) ? 0 : staticSignature.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
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
        PdfSignatureElement other = (PdfSignatureElement) obj;
        if (dataFieldName == null) {
            if (other.dataFieldName != null)
                return false;
        } else if (!dataFieldName.equals(other.dataFieldName))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (staticSignature == null) {
            if (other.staticSignature != null)
                return false;
        } else if (!staticSignature.equals(other.staticSignature))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PdfSignatureElement [title=" + title + ", description="
                + description + ", staticSignature=" + staticSignature
                + ", dataFieldName=" + dataFieldName + "]";
    }
    
}
