package pl.edu.agh.tgmg.itext.examples.system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.edu.agh.tgmg.api.PdfElement;
import pl.edu.agh.tgmg.api.annotations.PdfAfterDocument;
import pl.edu.agh.tgmg.api.annotations.PdfDocument;
import pl.edu.agh.tgmg.api.annotations.PdfParagraph;
import pl.edu.agh.tgmg.api.annotations.PdfSignature;
import pl.edu.agh.tgmg.api.annotations.PdfTable;
import pl.edu.agh.tgmg.api.annotations.styles.CellHeaderStyle;
import pl.edu.agh.tgmg.api.annotations.styles.ParagraphStyle;
import pl.edu.agh.tgmg.api.annotations.styles.TableStyle;
import pl.edu.agh.tgmg.api.annotations.styles.elements.FontStyle;
import pl.edu.agh.tgmg.api.annotations.styles.elements.PdfColor;
import pl.edu.agh.tgmg.itext.examples.tables.SimpleColumnsExample;
import pl.edu.agh.tgmg.itext.examples.tables.TableWithComlexTableNestingExample;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.ParagraphElement;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfSignatureElement;

@PdfAfterDocument(
        signatures={@PdfSignature(description="(date, signature)", title="Manager")} )
@CellHeaderStyle(backgroundColor=@PdfColor(R=240,G=240,B=240))
@ParagraphStyle(fontSize=26, spacingAfter=20, fontStyle=FontStyle.BOLD)
@PdfDocument
public class SystemTestExample1 extends SystemTest {

    @PdfParagraph("Some paragraph")
    
    @PdfTable(tableStyle=@TableStyle(spacingAfter=20))
    List<SimpleColumnsExample> table1 = Collections.nCopies(5, new SimpleColumnsExample());
    
    @PdfTable(tableStyle=@TableStyle(spacingAfter=20))
    List<TableWithComlexTableNestingExample> table3 = (List<TableWithComlexTableNestingExample>) exampleTable.getDocummentWrapper().getTable();
    
    @Override
    public List<? extends PdfElement> getExpectedElements() {
        List<PdfElement> elements = new ArrayList<>();
        elements.add(new ParagraphElement("Some paragraph"));
        elements.add(new SimpleColumnsExample().getExpectedElement());
        elements.add(exampleTable.getExpectedElement());
        elements.add(new PdfSignatureElement("Manager", "(date, signature)", "", ""));
        return elements;
    }
    
    static TableWithComlexTableNestingExample exampleTable = new TableWithComlexTableNestingExample();

}
