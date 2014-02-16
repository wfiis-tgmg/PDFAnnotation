package pl.edu.agh.tgmg.itext.examples.errors;

import org.testng.annotations.Test;

import pl.edu.agh.tgmg.api.PdfElement;

@Test
public abstract class BuildingBlocksErrorTest <E extends PdfElement> extends ErrorTest {

    public abstract E parseDocument(Class<?> clazz);
    
}
