package pl.edu.agh.tgmg.api.buildingBlocks.parser;

import pl.edu.agh.tgmg.api.CommonUtils;
import pl.edu.agh.tgmg.api.I18nResolver;
import pl.edu.agh.tgmg.api.annotations.PdfDocument;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentMetaData;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentMetaDataImpl;
import pl.edu.agh.tgmg.itext.generators.styles.elements.BoxValues;
import pl.edu.agh.tgmg.itext.generators.styles.parser.SmallElementsParser;

import com.itextpdf.text.Rectangle;

public class PdfMetadataParser {

    private I18nResolver i18nResolver;

    public PdfMetadataParser(I18nResolver i18nResolver) {
        this.i18nResolver = i18nResolver;
    }

    public DocumentMetaData parse(PdfDocument document) {
        String author = i18nResolver.translate(document.author());
        String subject = i18nResolver.translate(document.subject());
        String title = i18nResolver.translate(document.title());
        boolean createDate = document.createDate();
        BoxValues<Float> margins = SmallElementsParser.parse(document.margins());
        Rectangle size = SmallElementsParser.parse(document.pageSize());
        return new DocumentMetaDataImpl(author, createDate, margins, subject, size, title);
    }
}
