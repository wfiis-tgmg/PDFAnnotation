package pl.edu.agh.tgmg.api;

import com.google.common.collect.Lists;

public class MockDocumentStructre extends DocumentStructreImpl {

    public MockDocumentStructre() {
    }

    public void setColumnRow(CellRow... cellRow) {
        this.cellRow = Lists.newArrayList(cellRow);
    }

    public void setHeaders(ColumnHeader ... headers) {
        this.headers = Lists.newArrayList(headers);
    }


}
