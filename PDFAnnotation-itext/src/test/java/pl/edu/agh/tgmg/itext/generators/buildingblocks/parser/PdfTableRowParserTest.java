package pl.edu.agh.tgmg.itext.generators.buildingblocks.parser;

import org.testng.annotations.Test;

import pl.edu.agh.tgmg.itext.examples.TableExamples;
import pl.edu.agh.tgmg.itext.examples.TableErrorExamples;

public class PdfTableRowParserTest {

    TableExamples examples = new TableExamples();
    TableErrorExamples errorsExamples = new TableErrorExamples();
    
    @Test
    public void testSimpleRows() {
        examples.checkRowExamples(0);
    }
    
    @Test
    public void testSimpleRowsWithNamedColumns() {
        examples.checkRowExamples(1);
    }
    
    @Test
    public void testSimpleRowsWithColumnGroup1() {
        examples.checkRowExamples(2);
    }
    
    @Test
    public void testSimpleRowsWithColumnGroup2() {
        examples.checkRowExamples(3);
    }
    
    @Test
    public void testSimpleRowGroup() {
        examples.checkRowExamples(4);
    }
    
    @Test
    public void testSimpleTableGroup() {
        examples.checkRowExamples(5);
    }
    
    @Test
    public void testComplexNestedTable() {
        examples.checkRowExamples(6);
    }
    
    @Test
    public void testTableGroupErrors1() {
        errorsExamples.checkRowErrors(0);
    }
    
    @Test
    public void testTableGroupErrors2() {
        errorsExamples.checkRowErrors(1);
    }
    
    @Test
    public void testTableGroupErrors3() {
        errorsExamples.checkRowErrors(2);
    }
    
    @Test
    public void testTableGroupErrors4() {
        errorsExamples.checkRowErrors(3);
    }
}
