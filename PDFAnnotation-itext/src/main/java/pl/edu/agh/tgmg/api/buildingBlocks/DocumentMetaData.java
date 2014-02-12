package pl.edu.agh.tgmg.api.buildingBlocks;

import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import pl.edu.agh.tgmg.itext.generators.styles.BoxValues;

public interface DocumentMetaData {
    String getTitle();

    String getSubject();

    BoxValues<Integer> getMargins();

    boolean isCreateDate();

    String getAuthor();

    Rectangle getPageSize();
}
