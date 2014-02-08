package pl.edu.agh.tgmg.itext.wrapper;

import com.itextpdf.text.Phrase;
import pl.edu.agh.tgmg.api.annotations.ToTest;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.CellRow;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.CellWrapper;

import java.lang.reflect.Field;


@ToTest
public class StringCellRow implements CellRow {
    String name;

    public StringCellRow(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public CellWrapper getCell(Object o) {
        return new ITextPhraseWrapper(new Phrase(getValue(o)));
    }

    public String getValue(Object o)  {
        try {
            Field field = o.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(o).toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

