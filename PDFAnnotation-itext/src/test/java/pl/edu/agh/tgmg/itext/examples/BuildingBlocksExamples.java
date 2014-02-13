package pl.edu.agh.tgmg.itext.examples;

import pl.edu.agh.tgmg.api.PdfElement;

public interface BuildingBlocksExamples <E extends PdfElement> {
    Class<?> getExampleClass(int i);
    Object getExampleDTO(Class<?> clazz);
    E getExpectedElement(Class<?> clazz); 
}
