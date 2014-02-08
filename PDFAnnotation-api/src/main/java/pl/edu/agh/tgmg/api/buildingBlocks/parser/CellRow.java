package pl.edu.agh.tgmg.api.buildingBlocks.parser;


import pl.edu.agh.tgmg.api.buildingBlocks.Column;

public interface CellRow extends Column {
    public CellWrapper getCell(Object o) ;
}
