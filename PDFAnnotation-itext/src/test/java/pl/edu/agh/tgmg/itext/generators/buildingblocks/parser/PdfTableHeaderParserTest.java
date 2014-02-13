package pl.edu.agh.tgmg.itext.generators.buildingblocks.parser;

import org.testng.annotations.Test;

import pl.edu.agh.tgmg.itext.examples.TablerExamples;
import pl.edu.agh.tgmg.itext.examples.TableErrorExamples;

public class PdfTableHeaderParserTest {

    TablerExamples examples = new TablerExamples();
    TableErrorExamples errorsExamples = new TableErrorExamples();
    
    //--------- SIMPLE COLUMNS ----------
    
    @Test
    public void testSimpleColumns() {    
        examples.checkHeaderExamples(0);
    }
    
    // --------- COLUMN GROUPING ----------
    
    @Test
    public void testNamedColumns() {
        examples.checkHeaderExamples(1);
    }
    
    @Test
    public void testColumnGrouping() {
        examples.checkHeaderExamples(2);
    }
    
    @Test
    public void testColumnGrouping2() {    
        examples.checkHeaderExamples(3);
    }
    
    //--------- COLUMNS WITH SIMPLE NESTED TABLES ----------
    
    @Test
    public void testColumnWithSimpleRowGroup() {
        examples.checkHeaderExamples(4);
    }
    
    @Test
    public void testColumnWithSimpleTableGroup() {    
        examples.checkHeaderExamples(5);
    }
    
    //--------- COLUMN GROUPING WITH COMPLEX NESTED TABLES ----------
    
    @Test
    public void testColumnWithComlexTableNesting () {
        examples.checkHeaderExamples(6);
    }
    
    //--------- ERRORS ----------
    
    @Test
    public void testGroupingErrors1() {
        errorsExamples.checkHeaderErrors(0);
    }
    
    @Test
    public void testGroupingErrors2() {
        errorsExamples.checkHeaderErrors(1);
    }
    
    @Test
    public void testGroupingErrors3() {
        errorsExamples.checkHeaderErrors(2);
    }
    
    @Test
    public void testGroupingErrors4() {
        errorsExamples.checkHeaderErrors(3);
    }
}
