package pl.edu.agh.tgmg.api;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.Lists;
import pl.edu.agh.tgmg.itext.ColumnHeaderImpl;

import java.util.LinkedList;
import java.util.List;

import static com.google.common.collect.Lists.*;

public class DocumentStructreBuilder {
    private List<ColumnRow> columnRow = new LinkedList<ColumnRow>();
    private List<ColumnHeader> headers = new LinkedList<ColumnHeader>();

    public DocumentStructreBuilder setColumnRow(List<? extends ColumnRow> columnRow) {
        this.columnRow = transform(columnRow, Functions.<ColumnRow>identity());
        return this;
    }

    public DocumentStructreBuilder setHeaders(List<? extends ColumnHeader> headers) {
        this.headers = transform(headers, Functions.<ColumnHeader>identity());
        return this;
    }

    public DocumentStructreBuilder setColumnRowString(String ... columnRow) {
        this.columnRow = transform(newArrayList(columnRow), new Function<String, ColumnRow>() {
            @Override
            public ColumnRow apply(java.lang.String s) {
                return new ColumnRowImpl(s);
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
        return new DocumentStructreImpl(columnRow, headers);
    }

    public DocumentStructreBuilder clear()
    {
        this.headers.clear();
        this.columnRow.clear();
        return this;

    }
}