package pl.edu.agh.tgmg.itext.generators.buildingblocks.parser;

import org.testng.annotations.Test;

import junit.framework.Assert;
import pl.edu.agh.tgmg.api.annotations.PdfDocument;
import pl.edu.agh.tgmg.api.annotations.styles.PageSize;
import pl.edu.agh.tgmg.api.annotations.styles.PdfBoxValues;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentMetaData;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.PdfMetadataParser;
import pl.edu.agh.tgmg.itext.generators.styles.BoxValues;

@PdfDocument(author="author", createDate=false, margins=@PdfBoxValues(forAll=13), 
    pageSize=PageSize.A0, subject="subject", title="title")
class MetadataTestDTO {
    
}

public class PdfMetadataParserTest {
    
    @Test
    public void testMetadata() {
        PdfMetadataParser parser = new PdfMetadataParser();
        DocumentMetaData metadata = parser.parse(
                MetadataTestDTO.class.getAnnotation(PdfDocument.class));
        Assert.assertEquals("author", metadata.getAuthor());
        Assert.assertEquals("title", metadata.getTitle());
        Assert.assertEquals("subject", metadata.getSubject());
        Assert.assertEquals(false, metadata.isCreateDate());
        Assert.assertEquals(com.itextpdf.text.PageSize.A0.getTop(), metadata.getPageSize().getTop());
        Assert.assertEquals(com.itextpdf.text.PageSize.A0.getRight(), metadata.getPageSize().getRight());
        Assert.assertEquals(new BoxValues<Integer>(13), metadata.getMargins());
        
    }
}
