package pl.edu.agh.tgmg.itext.generators.buildingblocks;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import org.testng.annotations.Test;
import pl.edu.agh.tgmg.TestGroup;
import pl.edu.agh.tgmg.itext.generators.dto.TableHeaderColumn;
import pl.edu.agh.tgmg.itext.generators.metadata.DefaultITextDocumentFactory;
import pl.edu.agh.tgmg.itext.generators.metadata.DocumentFactoryBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

@Test(groups = TestGroup.GEN_DOC)
public class PdfTableHeaderTest {

    DefaultITextDocumentFactory factory = new DocumentFactoryBuilder().create();

    @Test
    public void testGenFlat() throws Exception {

        gen("headerflat.pdf", 3, Arrays.asList(
                new TableHeaderColumn("group1"),
                new TableHeaderColumn("group2"),
                new TableHeaderColumn("group3")
        ));
    }

    @Test
    public void testSubGroup() throws Exception {
        gen("headerSubgroup.pdf", 2, Arrays.asList(
                new TableHeaderColumn(1,3,"A"),
                new TableHeaderColumn(1,1,"B -> A"),
                new TableHeaderColumn(1,2,"C -> A")
        ));
    }

    @Test
    public void testAdvanceGrouping() throws Exception {
        gen("headerAdvance.pdf", 4, Arrays.asList(
                new TableHeaderColumn(3,1,"col1"),
                new TableHeaderColumn(1,3,"colummns"),
                new TableHeaderColumn(2,1,"col2"),
                new TableHeaderColumn(1,2,"small"),
                new TableHeaderColumn(1,1,"col3"),
                new TableHeaderColumn(1,1,"col4")
        ));
    }

    private void gen(String pathname, int column, List<TableHeaderColumn> headerColumns) throws FileNotFoundException, DocumentException {
        Document document = factory.create(new FileOutputStream(new File(pathname)));
        PdfTableHeader pdfTableHeader = new PdfTableHeader(column,headerColumns);
        document.add(pdfTableHeader.createPdfTable());
        factory.close(document);
    }
}
