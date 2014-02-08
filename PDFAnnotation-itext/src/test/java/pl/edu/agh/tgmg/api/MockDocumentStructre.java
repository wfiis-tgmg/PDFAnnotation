package pl.edu.agh.tgmg.api;

import com.google.common.collect.Lists;

import java.util.List;

public class MockDocumentStructre extends DocumentStructreImpl {

    public MockDocumentStructre() {
    }

    public void setColumnRow(ColumnRow ... columnRow) {
        this.columnRow = Lists.newArrayList(columnRow);
    }

    public void setHeaders(ColumnHeader ... headers) {
        this.headers = Lists.newArrayList(headers);
    }


}
