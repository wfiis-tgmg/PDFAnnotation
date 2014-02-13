package pl.edu.agh.tgmg.api.buildingBlocks;

public interface CellWrapper {
    public enum Type {PHRASE,TABLE}

    Type getType();
    Object getValue();

}
