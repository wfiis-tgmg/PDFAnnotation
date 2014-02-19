package pl.edu.agh.tgmg.api.buildingBlocks.parser;

import pl.edu.agh.tgmg.api.MessageResolver;
import pl.edu.agh.tgmg.api.annotations.PdfDocument;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentMetaData;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentMetaDataImpl;
import pl.edu.agh.tgmg.itext.generators.styles.elements.BoxValues;
import pl.edu.agh.tgmg.itext.generators.styles.parser.SmallElementsParser;
import pl.edu.agh.tgmg.utlis.CommonUtils;

import com.itextpdf.text.Rectangle;

public class PdfMetadataParser {

    private MessageResolver messageResolver;

    public PdfMetadataParser(MessageResolver messageResolver) {
        this.messageResolver = messageResolver;
    }

    public DocumentMetaData parse(PdfDocument document) {
        String author = messageResolver.getMessage(document.author());
        String subject = messageResolver.getMessage(document.subject());
        String title = messageResolver.getMessage(document.title());
        boolean createDate = document.createDate();
        BoxValues<Float> margins = SmallElementsParser.parse(document.margins());
        Rectangle size = SmallElementsParser.parse(document.pageSize());
        return new DocumentMetaDataImpl(author, createDate, margins, subject, size, title);
    }
}
