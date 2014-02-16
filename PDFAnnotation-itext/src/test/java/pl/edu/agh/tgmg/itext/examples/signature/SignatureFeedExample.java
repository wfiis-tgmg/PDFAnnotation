package pl.edu.agh.tgmg.itext.examples.signature;

import java.util.Arrays;
import java.util.List;

import pl.edu.agh.tgmg.api.PdfElement;
import pl.edu.agh.tgmg.api.annotations.PdfDocument;
import pl.edu.agh.tgmg.api.annotations.PdfMessageFeed;
import pl.edu.agh.tgmg.api.annotations.PdfSignature;
import pl.edu.agh.tgmg.itext.examples.DocumentExample;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfSignatureElement;

@PdfDocument
public class SignatureFeedExample extends DocumentExample {
    
    @PdfSignature(title="title", dataFieldName="signature", description="desc")
    @PdfMessageFeed
    String signature = "signature feed";

    @Override
    public List<? extends PdfElement> getExpectedElements() {
        return Arrays.asList(new PdfSignatureElement("title", "desc", "", "signature"));
    }
}