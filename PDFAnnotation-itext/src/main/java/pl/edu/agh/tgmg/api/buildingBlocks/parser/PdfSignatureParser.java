package pl.edu.agh.tgmg.api.buildingBlocks.parser;

import pl.edu.agh.tgmg.api.CommonUtils;
import pl.edu.agh.tgmg.api.annotations.PdfSignature;
import pl.edu.agh.tgmg.api.exceptions.InvalidAnnotationException;
import pl.edu.agh.tgmg.api.exceptions.InvalidSignatureException;
import pl.edu.agh.tgmg.api.exceptions.ReflectionException;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfSignatureElement;
import pl.edu.agh.tgmg.itext.generators.styles.StyleResolver;

public class PdfSignatureParser {
    
    private StyleResolver styleRepository = new StyleResolver();
    
    public PdfSignatureParser() {}
    
    public PdfSignatureParser(StyleResolver styleRepository) {
        this.styleRepository = styleRepository;
    }

    public PdfSignatureElement parse(PdfSignature signature, Class<?> root) throws InvalidSignatureException {
        if(!signature.dataFieldName().isEmpty()) {
            checkField(signature.dataFieldName(), root);
        }
        return new PdfSignatureElement(signature.title(), 
                    signature.description(), signature.staticSignature(), 
                    signature.dataFieldName());
        
    }
    
    private void checkField(String name, Class<?> root) throws InvalidSignatureException {
        try {
            CommonUtils.checkFieldMessageFeed(name, root);
        } catch (InvalidAnnotationException | ReflectionException e) {
            throw new InvalidSignatureException(e);
        }
    }
}
