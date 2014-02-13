package pl.edu.agh.tgmg.api.buildingBlocks;

import pl.edu.agh.tgmg.itext.generators.styles.elements.BoxValues;

import com.itextpdf.text.Rectangle;

public interface DocumentMetaData {
    String getTitle();

    String getSubject();

    BoxValues<Float> getMargins();

    boolean isCreateDate();

    String getAuthor();

    Rectangle getPageSize();
}
