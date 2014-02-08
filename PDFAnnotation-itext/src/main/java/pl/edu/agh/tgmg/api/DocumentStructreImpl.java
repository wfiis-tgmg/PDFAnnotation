package pl.edu.agh.tgmg.api;

import pl.edu.agh.tgmg.api.buildingBlocks.*;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.CellRow;

import java.util.LinkedList;
import java.util.List;

@Deprecated
public class DocumentStructreImpl implements DocumentStructure
{
    public DocumentStructreImpl() {
        this(new LinkedList<CellRow>(), new LinkedList<ColumnHeader>());
    }

    public DocumentStructreImpl(List<CellRow> cellRow, List<ColumnHeader> headers) {
        this.cellRow = cellRow;
        this.headers = headers;
    }

    protected List<ColumnHeader> headers ;
    protected List<CellRow> cellRow;

    @Override
    public List<ColumnHeader> getHeaders() {

        return headers;
    }

    @Override
    public List<CellRow> getCellRow() {

        return cellRow;
    }

    @Override
    public DocumentMetaData getMetaData() {

        return new DocumentMetaDataImpl();
    }
}
