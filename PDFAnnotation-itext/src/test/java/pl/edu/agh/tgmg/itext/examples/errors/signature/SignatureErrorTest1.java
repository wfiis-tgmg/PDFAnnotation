package pl.edu.agh.tgmg.itext.examples.errors.signature;

import pl.edu.agh.tgmg.api.annotations.PdfDocument;
import pl.edu.agh.tgmg.api.annotations.PdfSignature;
import pl.edu.agh.tgmg.api.exceptions.InvalidSignatureException;
import pl.edu.agh.tgmg.itext.examples.errors.DocumentErrorTest;

@PdfDocument
public class SignatureErrorTest1 extends DocumentErrorTest {
    
    @PdfSignature(dataFieldName="invalidField")
    String str;

    @Override
    public Class<? extends Exception> getExpectedException() {
        return InvalidSignatureException.class;
    }

    @Override
    public String getExpectedExceptionMessage() {
        return ".* Message feed .* does not exists in class .*";
    }
}