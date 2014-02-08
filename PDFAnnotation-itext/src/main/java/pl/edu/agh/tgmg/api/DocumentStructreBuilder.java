package pl.edu.agh.tgmg.api;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentMetaData;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentMetaDataImpl;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.CellRow;
import pl.edu.agh.tgmg.api.buildingBlocks.ColumnHeader;
import pl.edu.agh.tgmg.itext.generators.oldTable.ColumnHeaderImpl;
import pl.edu.agh.tgmg.itext.wrapper.StringCellRow;

import java.util.LinkedList;
import java.util.List;

import static com.google.common.collect.Lists.*;


public class DocumentStructreBuilder {
    protected List<PdfElement> elements = new LinkedList<PdfElement>();
    protected DocumentMetaData metaData = new DocumentMetaDataImpl();

    public DocumentStructreBuilder setElements(List<? extends PdfElement> elements) {
        this.elements = transform(elements, Functions.<PdfElement>identity());
        return this;
    }

    public DocumentStructreBuilder setMetaData(DocumentMetaData metaData) {
        this.metaData = metaData;
        return this;
    }

    public DocumentStructreImpl create() {
        return new DocumentStructreImpl(elements, metaData);
    }

    public DocumentStructreBuilder clear()
    {
        this.elements.clear();
        this.metaData = new DocumentMetaDataImpl();
        return this;

    }
}