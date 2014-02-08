package pl.edu.agh.tgmg.api;

public interface CellWrapper {
    public enum Type {PHRASE,TABLE}

    Type getType();
    Object getValue();

}
