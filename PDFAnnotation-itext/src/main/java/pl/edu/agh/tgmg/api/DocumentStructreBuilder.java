package pl.edu.agh.tgmg.api;

import static com.google.common.collect.Lists.transform;

import java.util.LinkedList;
import java.util.List;

import pl.edu.agh.tgmg.api.buildingBlocks.DocumentMetaData;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentMetaDataImpl;

import com.google.common.base.Functions;


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

    public DocumentStructureImpl create() {
        return new DocumentStructureImpl(elements, metaData);
    }

    public DocumentStructreBuilder clear()
    {
        this.elements.clear();
        this.metaData = new DocumentMetaDataImpl();
        return this;

    }
}