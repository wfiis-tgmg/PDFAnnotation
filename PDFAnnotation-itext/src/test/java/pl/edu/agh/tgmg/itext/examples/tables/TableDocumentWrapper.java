package pl.edu.agh.tgmg.itext.examples.tables;

import java.lang.reflect.Field;
import java.util.List;

import org.testng.Assert;

import pl.edu.agh.tgmg.api.annotations.PdfDocument;

@PdfDocument
public abstract class TableDocumentWrapper <E extends TableExample> {

    public abstract List<E> getTable();
    public abstract String getTableFieldName();
    
    public Field getTableField() {
        String name = getTableFieldName();
        try {
            return this.getClass().getDeclaredField(name);
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
            Assert.fail("Could not get table field: " + name, e);
            return null;
        }
    }
}
