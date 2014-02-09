package pl.edu.agh.tgmg.examples;

import pl.edu.agh.tgmg.api.annotations.PdfDocument;

import java.util.LinkedList;
import java.util.List;

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

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public TwoColumnTable(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
