package pl.edu.agh.tgmg.api.buildingBlocks.parser;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.RectangleReadOnly;

import pl.edu.agh.tgmg.api.CommonUtils;
import pl.edu.agh.tgmg.api.annotations.PdfDocument;
import pl.edu.agh.tgmg.api.annotations.styles.PageSize;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentMetaData;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentMetaDataImpl;
import pl.edu.agh.tgmg.itext.generators.styles.BoxValues;

public class PdfMetadataParser {
    
    public DocumentMetaData parse(PdfDocument document) {
        String author = CommonUtils.processText(document.author());
        String subject = CommonUtils.processText(document.subject());
        String title = CommonUtils.processText(document.title());
        boolean createDate = document.createDate();
        BoxValues<Integer> margins = new PdfBoxValueParser().parse(document.margins());
        PageSize pageSize = document.pageSize();
        Rectangle p = new RectangleReadOnly(pageSize.getUrx(),pageSize.getUry());
        return new DocumentMetaDataImpl(author, createDate, margins, subject, p, title);
    }
}
