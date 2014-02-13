package pl.edu.agh.tgmg.itext.wrapper;

import pl.edu.agh.tgmg.api.buildingBlocks.CellWrapper;

import com.itextpdf.text.pdf.PdfPTable;

public class ITextTableWrapper implements CellWrapper {


    PdfPTable table;

    public ITextTableWrapper(PdfPTable table) {
        this.table = table;
    }

    @Override
    public Type getType() {
        return Type.TABLE;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object getValue() {
        return table;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
