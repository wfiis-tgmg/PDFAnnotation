package pl.edu.agh.tgmg.api.buildingBlocks;

import pl.edu.agh.tgmg.api.buildingBlocks.parser.CellRow;

import java.util.List;

public interface DocumentStructure {

    List<ColumnHeader> getHeaders();
    List<CellRow> getCellRow();

    DocumentMetaData getMetaData();
}
