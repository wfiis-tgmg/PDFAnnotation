package pl.edu.agh.tgmg.itext.wrapper;

import pl.edu.agh.tgmg.api.CommonUtils;
import pl.edu.agh.tgmg.api.annotations.ToTest;
import pl.edu.agh.tgmg.api.annotations.styles.CellRowStyle;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.CellRow;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.CellWrapper;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.CellRowFormatter;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.StyleFormatter;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;


@ToTest
public class StringCellRow implements CellRow {
    String name;
    
    StyleFormatter<PdfPCell, CellRowStyle> cellFormatter = new CellRowFormatter();

    public StringCellRow(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public CellWrapper getCell(Object o) {
        return new ITextPhraseWrapper(new Phrase(CommonUtils.getValue(o,name).toString()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        StringCellRow other = (StringCellRow) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public StyleFormatter<PdfPCell, CellRowStyle> getFormatter() {
        return cellFormatter;
    }

    
}

