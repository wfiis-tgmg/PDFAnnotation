package pl.edu.agh.tgmg.api;

import java.io.OutputStream;

import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;

/**
 * Main interface for generation of a PDF document. 
 * @author Tom
 *
 */
public interface PdfGeneratorFacade {
    
    /**
     * Allows to generate a PDF document with a given DTO object. The object's 
     * class has to be annoted with @{link PdfDocument}. 
     * @param out Output for the generated document
     * @param dto the input object that should be used for PDF generation
     * @throws GenDocumentException
     */
    void generate(OutputStream out, Object dto ) throws GenDocumentException;
}
