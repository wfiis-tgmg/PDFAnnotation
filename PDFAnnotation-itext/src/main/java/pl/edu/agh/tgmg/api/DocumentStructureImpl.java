package pl.edu.agh.tgmg.api;

import java.util.LinkedList;
import java.util.List;

import pl.edu.agh.tgmg.api.buildingBlocks.DocumentMetaData;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentMetaDataImpl;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentStructure;

public class DocumentStructureImpl implements DocumentStructure
{
    public DocumentStructureImpl() {
        this(new LinkedList<PdfElement>(), new DocumentMetaDataImpl());
    }


    public DocumentStructureImpl(List<PdfElement> headers, DocumentMetaData metaData) {
        this.elements = headers;
        this.metaData = metaData;
    }

    protected List<PdfElement> elements;
    protected DocumentMetaData metaData;


    @Override
    public List<PdfElement> getPdfElements() {

        return elements;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public DocumentMetaData getMetaData() {

        return metaData;
    }
}
