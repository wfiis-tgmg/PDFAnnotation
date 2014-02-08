package pl.edu.agh.tgmg.api;

public class ColumnRowImpl implements ColumnRow {
    String name;

    public ColumnRowImpl(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

