package pl.edu.agh.tgmg.examples;


import java.util.LinkedList;
import java.util.List;

public class SimpleRow {
    String row1;
    String row2;
    int row3;

    public static List<SimpleRow> feed()
    {
        List<SimpleRow> res = new LinkedList<SimpleRow>();
        for(int i=0;i<5;i++)
            res.add(new SimpleRow(String.format("some %d",i),String.format("Name %d",i),i*i));
        return res;
    }

    public SimpleRow(String row1, String row2, int row3) {
        this.row1 = row1;
        this.row2 = row2;
        this.row3 = row3;
    }
}
