package pl.edu.agh.tgmg.itext.examples.errors.signature;

import pl.edu.agh.tgmg.api.annotations.PdfDocument;
import pl.edu.agh.tgmg.api.annotations.PdfSignature;
import pl.edu.agh.tgmg.api.exceptions.InvalidSignatureException;
import pl.edu.agh.tgmg.itext.examples.errors.DocumentErrorTest;

@PdfDocument
public class SignatureErrorTest2 extends DocumentErrorTest {
    
    @PdfSignature(dataFieldName="str")
    String str;

    @Override
    public Class<? extends Exception> getExpectedException() {
        return InvalidSignatureException.class;
    }

    @Override
    public String getExpectedExceptionMessage() {
        return ".* Field .* from class .* is not a MessageFeed";
    }
}