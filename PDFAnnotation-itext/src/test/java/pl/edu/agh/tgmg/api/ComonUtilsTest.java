package pl.edu.agh.tgmg.api;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import junit.framework.Assert;

import org.testng.annotations.Test;

class A {
    List<String> a;
    String[] b;
    ArrayList<String> c;
    Iterable<String> d;
}

public class ComonUtilsTest {

    @Test
    public void testIsClassParametrized() {
        List<String> list = new ArrayList<String>();
        String string = "";
        Assert.assertTrue(CommonUtils.isClassParametriezed(list.getClass()));
        Assert.assertFalse(CommonUtils.isClassParametriezed(string.getClass()));
        Assert.assertFalse(CommonUtils.isClassParametriezed(Collection.class));
        Assert.assertFalse(CommonUtils.isClassParametriezed(String.class));
    }
    
    @Test
    public void testGetTypeParamOfIterableField() {
        for(Field field : A.class.getDeclaredFields()) {
            Assert.assertEquals(String.class, CommonUtils.getTypeParamOfIterableField(field));
        }
    }
}
