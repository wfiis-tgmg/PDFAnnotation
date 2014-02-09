package pl.edu.agh.tgmg.examples;


import java.util.LinkedList;
import java.util.List;

public class SecLvlTableExample {
    String row1;
    String row2;
    List<Inner> row3;

    public static List<SecLvlTableExample> feed()
    {
        List<SecLvlTableExample> res = new LinkedList<SecLvlTableExample>();
        for(int i=1;i<5;i++)
        {

            List<Inner> inners = new LinkedList<Inner>();
            for(int j=0;j<i;j++)
            {
                inners.add(new Inner("Innner "+i));
            }

            res.add(new SecLvlTableExample(String.format("some %d",i),String.format("Name %d",i),inners));
        }

        return res;
    }

    public SecLvlTableExample(String row1, String row2, List<Inner> row3) {
        this.row1 = row1;
        this.row2 = row2;
        this.row3 = row3;
    }
}

class Inner
{
    Inner(String name) {
        this.name = name;
    }

    String name;
}
