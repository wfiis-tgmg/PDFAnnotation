package pl.edu.agh.tgmg.api;

import java.util.List;

public interface DocumentStructure {

    List<ColumnHeader> getHeaders();
    List<ColumnRow> getColumnRow();

    DocumentMetaData getMetaData();
}
