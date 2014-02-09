package pl.edu.agh.tgmg.examples;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ThirdLvlTableExample {

    String unique;
    List<SecLvlTableExample> tab;

    public static List<ThirdLvlTableExample> feed()
    {
        List<ThirdLvlTableExample> res = new LinkedList<ThirdLvlTableExample>();

        for(int i=1;i<4;i++)
        {
            res.add(new ThirdLvlTableExample(String.format("id -- %d ---", i), SecLvlTableExample.feed()));
        }

        res.add(new ThirdLvlTableExample("endRow", Arrays.asList(new SecLvlTableExample(
                "rww","Dsadas",Arrays.asList(new Inner("dsaad"),new Inner("dsaad"))
        ))));



        return res;
    }

    public ThirdLvlTableExample(String unique,List<SecLvlTableExample> tab) {
        this.tab = tab;
        this.unique = unique;
    }
}
