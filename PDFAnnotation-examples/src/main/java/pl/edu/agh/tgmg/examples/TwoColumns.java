package pl.edu.agh.tgmg.examples;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.core.LombokImmutableList;
import pl.edu.agh.tgmg.api.ColumnHeader;
import pl.edu.agh.tgmg.api.PdfContainer;

import java.util.LinkedList;
import java.util.List;

@Getter
@AllArgsConstructor
public class TwoColumns implements PdfContainer {
    String surname;
    String name;

    public static List<TwoColumns> feed()
    {
        List<TwoColumns> res = new LinkedList<TwoColumns>();
        for(int i=0;i<20;i++)
            res.add(new TwoColumns(String.format("Surname %d",i),String.format("Name %d",i)));
        return res;
    }

}
