package pl.edu.agh.tgmg.itext.examples.metadata;

import junit.framework.Assert;

import org.testng.annotations.Test;

import pl.edu.agh.tgmg.api.BlankI18nResolverImpl;
import pl.edu.agh.tgmg.api.annotations.PdfDocument;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentMetaData;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.PdfMetadataParser;

public abstract class MetadataExample {
    
    PdfMetadataParser parser = new PdfMetadataParser(new BlankI18nResolverImpl());

    public abstract DocumentMetaData getExpectedMetadata();
    
    @Test
    public void checkMetadataParsing() {
        PdfDocument document = this.getClass().getAnnotation(PdfDocument.class);
        Assert.assertNotNull(document);
        DocumentMetaData metadata = parser.parse(document);
        DocumentMetaData expected = getExpectedMetadata();
        Assert.assertEquals(expected, metadata);
    }
}
