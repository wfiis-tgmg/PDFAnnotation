package pl.edu.agh.tgmg.api;

import java.util.LinkedList;
import java.util.List;

public class DocumentStructreImpl implements DocumentStructure
{
    public DocumentStructreImpl() {
        this(new LinkedList<ColumnRow>(), new LinkedList<ColumnHeader>());
    }

    public DocumentStructreImpl(List<ColumnRow> columnRow, List<ColumnHeader> headers) {
        this.columnRow = columnRow;
        this.headers = headers;
    }

    protected List<ColumnHeader> headers ;
    protected List<ColumnRow> columnRow ;

    @Override
    public List<ColumnHeader> getHeaders() {

        return headers;
    }

    @Override
    public List<ColumnRow> getColumnRow() {

        return columnRow;
    }
}
