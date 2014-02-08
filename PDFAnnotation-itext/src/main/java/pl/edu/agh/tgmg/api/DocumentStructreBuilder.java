package pl.edu.agh.tgmg.api;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import pl.edu.agh.tgmg.itext.ColumnHeaderImpl;

import java.util.LinkedList;
import java.util.List;

import static com.google.common.collect.Lists.*;

public class DocumentStructreBuilder {
    private List<CellRow> cellRow = new LinkedList<CellRow>();
    private List<ColumnHeader> headers = new LinkedList<ColumnHeader>();

    public DocumentStructreBuilder setCellRow(List<? extends CellRow> cellRow) {
        this.cellRow = transform(cellRow, Functions.<CellRow>identity());
        return this;
    }

    public DocumentStructreBuilder setHeaders(List<? extends ColumnHeader> headers) {
        this.headers = transform(headers, Functions.<ColumnHeader>identity());
        return this;
    }

    public DocumentStructreBuilder setColumnRowString(String ... columnRow) {
        this.cellRow = transform(newArrayList(columnRow), new Function<String, CellRow>() {
            @Override
            public CellRow apply(java.lang.String s) {
                return new StringCellRow(s);
            }
        });
        return this;
    }

    public DocumentStructreBuilder setHeadersString(String ... headers) {
        this.headers = transform(newArrayList(headers), new Function<String, ColumnHeader>() {
            @Override
            public ColumnHeader apply(java.lang.String s) {
                return new ColumnHeaderImpl(s);
            }
        });
        return this;
    }

    public DocumentStructreImpl create() {
        return new DocumentStructreImpl(cellRow, headers);
    }

    public DocumentStructreBuilder clear()
    {
        this.headers.clear();
        this.cellRow.clear();
        return this;

    }
}