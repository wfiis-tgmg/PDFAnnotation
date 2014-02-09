package pl.edu.agh.tgmg.oldApi;

import pl.edu.agh.tgmg.api.buildingBlocks.ColumnHeader;


public class ColumnHeaderImpl implements ColumnHeader {
    String name;

    public ColumnHeaderImpl(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getSubColumnAmount() {

        return 1;
    }
}
