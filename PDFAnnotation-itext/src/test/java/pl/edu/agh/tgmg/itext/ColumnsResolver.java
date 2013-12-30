package pl.edu.agh.tgmg.itext;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.testng.annotations.Test;
import pl.edu.agh.tgmg.api.ColumnHeader;
import pl.edu.agh.tgmg.api.PdfColumn;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Test(groups = "init")
public class ColumnsResolver
{
    @Getter
    static class DtoNoAnnotations
    {
        public String name;
        public int amoung;
    }

    @Getter
    static class DtoWithOrder
    {
        @PdfColumn(order = 2)
        String name;
        @PdfColumn(order = 1)
        int amoung;
    }

    @Getter
    static class DtoWithAlias
    {
        @PdfColumn(name = "some1")
        String name;
        @PdfColumn(name = "some2")
        int amoung;
    }

    @Getter
    static class DtoWithI18n
    {
        @PdfColumn(name = "#{i18n1}")
        String name;
        @PdfColumn(name = "#{i18n2}")
        int amoung;
    }

    static class ColumnResolver
    {
        public List<ColumnHeader> resolve(Class<?> clazz)
        {

            Field[] fields = clazz.getFields();
            LinkedList<ColumnHeader> columnHeaders = new LinkedList<ColumnHeader>();
            for (Field field : fields) {
                String name = field.getName();
                columnHeaders.add(new ColumnHeaderImpl(field.getName()));
            }
            return columnHeaders;
        }
    }

    ColumnResolver columnResolver = new ColumnResolver();

    @Test
    public void testWithoutAnnotations() throws Exception {
        List<ColumnHeader> resolve = columnResolver.resolve(DtoNoAnnotations.class);
        assertEquals(resolve.size(), 2);
        assertTrue(resolve.containsAll(of(new ColumnHeaderImpl("name"), new ColumnHeaderImpl("amoung"))));
    }

    @Test
    public void testColumnOrder() throws Exception {
        List<ColumnHeader> resolve = columnResolver.resolve(DtoWithOrder.class);
        assertEquals(resolve.size(), 2);
        assertTrue(resolve.equals(of(new ColumnHeaderImpl("amoung"),new ColumnHeaderImpl("name"))));
    }

    @Test
    public void testColumnAlias() throws Exception {
        List<ColumnHeader> resolve = columnResolver.resolve(DtoWithAlias.class);
        assertEquals(resolve.size(), 2);
        assertTrue(resolve.containsAll(of(new ColumnHeaderImpl("some1"),new ColumnHeaderImpl("some2"))));
    }

    @Test
    public void testColumnWithI18n() throws Exception {
        List<ColumnHeader> resolve = columnResolver.resolve(DtoWithI18n.class);
        assertEquals(resolve.size(), 2);
        assertTrue(resolve.containsAll(of(new ColumnHeaderImpl("lang1"),new ColumnHeaderImpl("lang2"))));
    }




    @Data
    @AllArgsConstructor
    private static class ColumnHeaderImpl implements ColumnHeader {
        String name;
    }
}
