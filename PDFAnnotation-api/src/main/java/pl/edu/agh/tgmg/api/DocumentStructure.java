package pl.edu.agh.tgmg.api;

import java.util.List;

public interface DocumentStructure {

    List<ColumnHeader> getHeaders();
    List<CellRow> getCellRow();

    DocumentMetaData getMetaData();
}
