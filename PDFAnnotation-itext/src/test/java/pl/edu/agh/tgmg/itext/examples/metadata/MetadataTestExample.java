package pl.edu.agh.tgmg.itext.examples.metadata;

import pl.edu.agh.tgmg.api.annotations.PdfDocument;
import pl.edu.agh.tgmg.api.annotations.styles.elements.PageSize;
import pl.edu.agh.tgmg.api.annotations.styles.elements.PdfBoxValuesF;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentMetaData;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentMetaDataImpl;
import pl.edu.agh.tgmg.itext.generators.styles.elements.BoxValues;

@PdfDocument(author="author", createDate=false, margins=@PdfBoxValuesF(forAll=13), 
    pageSize=PageSize.A3, subject="subject", title="title")
public class MetadataTestExample extends MetadataExample {

    @Override
    public DocumentMetaData getExpectedMetadata() {
        return  new DocumentMetaDataImpl("author", 
                false, new BoxValues<Float>(13.0f), "subject", 
                com.itextpdf.text.PageSize.A3, "title");
    }
    
}