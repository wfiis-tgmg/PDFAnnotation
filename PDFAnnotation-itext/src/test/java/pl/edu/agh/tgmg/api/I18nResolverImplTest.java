package pl.edu.agh.tgmg.api;

import junit.framework.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.edu.agh.tgmg.TestGroup;

import java.util.Locale;
import java.util.MissingResourceException;

import static org.testng.Assert.*;

@Test(groups = TestGroup.BASIC)
public class I18nResolverImplTest {

    private final String validClassPath = "i18n/test";
    I18nResolverImpl resolver;

    @BeforeMethod
    public void setUp() throws Exception {
        resolver = new I18nResolverImpl(validClassPath);
    }



    @Test(dependsOnMethods = "testValidFile")
    public void testI18nKeys() throws Exception {
        I18nResolverImpl resolver = new I18nResolverImpl(validClassPath);
        assertTrue(resolver.matches("${some}"));
        assertTrue(resolver.matches("${32some}"));
        assertTrue(resolver.matches("${som32e}"));
        assertTrue(resolver.matches("${some.das32.32dsa}"));
    }

    @Test
    public void testStandardKeys() throws Exception {
        assertFalse( resolver.matches("{some}"));
        assertFalse( resolver.matches("dasdas}"));
        assertFalse( resolver.matches("dadfdas32"));
        assertFalse( resolver.matches("$dasda"));
    }

    @Test
    public void testValidFile() throws Exception {
         new I18nResolverImpl(validClassPath);
    }

    @Test(expectedExceptions = MissingResourceException.class, expectedExceptionsMessageRegExp = "Can't find bundle for base name invalidFile, locale pl_PL")
    public void testInvalidFile() throws Exception {
        new I18nResolverImpl("invalidFile");
        fail("expected exception");
    }

    @Test
    public void testPlaceholderForDifferentLocale() throws Exception {
        assertEquals("SomeTextValue", new I18nResolverImpl(validClassPath, Locale.forLanguageTag("pl")).translate("${some.text}"));
        assertEquals("SomeTextValue ENG", new I18nResolverImpl(validClassPath, Locale.ENGLISH).translate("${some.text}"));
    }

    @Test(expectedExceptions = MissingResourceException.class)
    public void testPlaceHolderNotFound() throws Exception {
        String translate = new I18nResolverImpl(validClassPath).translate("${some.notExisted}");
        Assert.assertNotNull(translate);
        fail();
    }
}
