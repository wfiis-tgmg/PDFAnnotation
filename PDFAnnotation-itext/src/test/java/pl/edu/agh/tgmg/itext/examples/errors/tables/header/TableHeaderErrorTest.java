package pl.edu.agh.tgmg.itext.examples.errors.tables.header;

import org.mockito.Mockito;

import pl.edu.agh.tgmg.api.buildingBlocks.parser.PdfTableHeaderParser;
import pl.edu.agh.tgmg.itext.examples.errors.BuildingBlocksErrorTest;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableHeader;
import pl.edu.agh.tgmg.itext.generators.styles.StyleResolver;

public abstract class TableHeaderErrorTest extends BuildingBlocksErrorTest<PdfTableHeader> {

    @Override
    public PdfTableHeader parseDocument(Class<?> clazz) {
        StyleResolver styleResolver = Mockito.mock(StyleResolver.class);
        return new PdfTableHeaderParser(styleResolver).parse(clazz);
    }

}
