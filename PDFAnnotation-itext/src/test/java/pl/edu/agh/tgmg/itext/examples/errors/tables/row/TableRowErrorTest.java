package pl.edu.agh.tgmg.itext.examples.errors.tables.row;

import org.mockito.Mockito;

import pl.edu.agh.tgmg.api.buildingBlocks.parser.PdfTableRowParser;
import pl.edu.agh.tgmg.itext.examples.errors.BuildingBlocksErrorTest;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableRow;
import pl.edu.agh.tgmg.itext.generators.styles.StyleResolver;

public abstract class TableRowErrorTest extends BuildingBlocksErrorTest<PdfTableRow> {

    @Override
    public PdfTableRow parseDocument(Class<?> clazz) {
        StyleResolver styleResolver = Mockito.mock(StyleResolver.class);
        return new PdfTableRowParser(styleResolver).parse(clazz);
    }

}
