package pl.edu.agh.tgmg.itext;

import com.itextpdf.text.Document;
import pl.edu.agh.tgmg.api.ColumnHeader;

import java.util.List;

public interface ITextHeaderDecorator {
    void decorate(Document document, List<ColumnHeader> headers);
}
