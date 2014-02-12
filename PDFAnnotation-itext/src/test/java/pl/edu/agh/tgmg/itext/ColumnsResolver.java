package pl.edu.agh.tgmg.itext;


import static com.google.common.collect.ImmutableList.of;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import org.testng.annotations.Test;

import pl.edu.agh.tgmg.api.annotations.PdfColumn;
import pl.edu.agh.tgmg.api.buildingBlocks.TableHeaderColumn;
import pl.edu.agh.tgmg.oldApi.ColumnHeaderImpl;

@Test(groups = "init")
public class ColumnsResolver
{
    static class DtoNoAnnotations
    {
        public String name;
        public int amoung;

        String getName() {
            return name;
        }

        int getAmoung() {
            return amoung;
        }
    }

    static class DtoWithOrder
    {
        @PdfColumn(order = 2)
        String name;
        @PdfColumn(order = 1)
        int amoung;

        int getAmoung() {
            return amoung;
        }

        String getName() {
            return name;
        }
    }

    static class DtoWithAlias
    {
        @PdfColumn(name = "some1")
        String name;
        @PdfColumn(name = "some2")
        int amoung;

        String getName() {
            return name;
        }

        int getAmoung() {
            return amoung;
        }
    }


    static class DtoWithI18n
    {
        @PdfColumn(name = "#{i18n1}")
        String name;
        @PdfColumn(name = "#{i18n2}")
        int amoung;

        int getAmoung() {
            return amoung;
        }

        String getName() {
            return name;
        }
    }

    static class ColumnResolver
    {
        public List<TableHeaderColumn> resolve(Class<?> clazz)
        {

            Field[] fields = clazz.getFields();
            LinkedList<TableHeaderColumn> columnHeaders = new LinkedList<TableHeaderColumn>();
            for (Field field : fields) {
                String name = field.getName();
                columnHeaders.add(new ColumnHeaderImpl(field.getName()));
            }
            return columnHeaders;
        }
    }

    ColumnResolver columnResolver = new ColumnResolver();
//
//    @Test
//    public void testWithoutAnnotations() throws Exception {
//        List<TableHeaderColumn> resolve = columnResolver.resolve(DtoNoAnnotations.class);
//        assertEquals(resolve.size(), 2);
//        assertTrue(resolve.containsAll(of(new ColumnHeaderImpl("name"), new ColumnHeaderImpl("amoung"))));
//    }
//
//    @Test
//    public void testColumnOrder() throws Exception {
//        List<TableHeaderColumn> resolve = columnResolver.resolve(DtoWithOrder.class);
//        assertEquals(resolve.size(), 2);
//        assertTrue(resolve.equals(of(new ColumnHeaderImpl("amoung"),new ColumnHeaderImpl("name"))));
//    }
//
//    @Test
//    public void testColumnAlias() throws Exception {
//        List<TableHeaderColumn> resolve = columnResolver.resolve(DtoWithAlias.class);
//        assertEquals(resolve.size(), 2);
//        assertTrue(resolve.containsAll(of(new ColumnHeaderImpl("some1"),new ColumnHeaderImpl("some2"))));
//    }
//
//    @Test
//    public void testColumnWithI18n() throws Exception {
//        List<TableHeaderColumn> resolve = columnResolver.resolve(DtoWithI18n.class);
//        assertEquals(resolve.size(), 2);
//        assertTrue(resolve.containsAll(of(new ColumnHeaderImpl("lang1"),new ColumnHeaderImpl("lang2"))));
//    }


}
