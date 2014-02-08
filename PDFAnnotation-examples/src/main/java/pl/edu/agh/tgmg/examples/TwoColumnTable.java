package pl.edu.agh.tgmg.examples;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.edu.agh.tgmg.api.PdfDocument;

import java.util.LinkedList;
import java.util.List;

@Getter
@AllArgsConstructor
public class TwoColumnTable implements PdfDocument {
    String surname;
    String name;

    public static List<TwoColumnTable> feed()
    {
        List<TwoColumnTable> res = new LinkedList<TwoColumnTable>();
        for(int i=0;i<20;i++)
            res.add(new TwoColumnTable(String.format("Surname %d",i),String.format("Name %d",i)));
        return res;
    }
}
