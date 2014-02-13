package pl.edu.agh.tgmg.itext.generators.buildingblocks.parser;

import org.testng.annotations.Test;

import pl.edu.agh.tgmg.itext.examples.TablerExamples;

public class PdfTableTestParser {
    
    TablerExamples examples = new TablerExamples();
    
    @Test
    public void testTableParsing1() {
        examples.checkTableExamples(0);
    }
    
    @Test
    public void testTableParsing2() {
        examples.checkTableExamples(1);
    }
    
    @Test
    public void testTableParsing3() {
        examples.checkTableExamples(2);
    }
    
    @Test
    public void testTableParsing4() {
        examples.checkTableExamples(3);
    }
    
    @Test
    public void testTableParsing5() {
        examples.checkTableExamples(4);
    }
    
    @Test
    public void testTableParsing6() {
        examples.checkTableExamples(5);
    }
    
    @Test
    public void testTableParsing7() {
        examples.checkTableExamples(6);
    }
}
