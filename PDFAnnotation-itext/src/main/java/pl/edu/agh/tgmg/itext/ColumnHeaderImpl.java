package pl.edu.agh.tgmg.itext;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.edu.agh.tgmg.api.ColumnHeader;


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
