package pl.edu.agh.tgmg.itext.examples.signature;

import java.util.Arrays;
import java.util.List;

import pl.edu.agh.tgmg.api.PdfElement;
import pl.edu.agh.tgmg.api.annotations.PdfDocument;
import pl.edu.agh.tgmg.api.annotations.PdfSignature;
import pl.edu.agh.tgmg.itext.examples.DocumentExample;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfSignatureElement;

@PdfDocument
public class SimpleSignatureExample extends DocumentExample {
    
    @PdfSignature(title="title")
    
    String str;

    @Override
    public List<? extends PdfElement> getExpectedElements() {
        return Arrays.asList(new PdfSignatureElement("title", "( signature )", "" , ""));
    }
}