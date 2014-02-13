package pl.edu.agh.tgmg.api.buildingBlocks;


import pl.edu.agh.tgmg.itext.generators.buildingblocks.formatters.CreatesHeaderCellElement;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.formatters.CreatesRowCellElement;

public interface CellRow extends Column, CreatesRowCellElement {
    public CellWrapper getCell(Object o) ;
    
    @Override
    public boolean equals(Object o);
    
    @Override
    public int hashCode();
}
