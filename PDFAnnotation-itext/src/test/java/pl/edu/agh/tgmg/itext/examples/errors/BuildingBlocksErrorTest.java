package pl.edu.agh.tgmg.itext.examples.errors;

import org.testng.Assert;
import org.testng.annotations.Test;

import pl.edu.agh.tgmg.api.PdfElement;

@Test
public abstract class BuildingBlocksErrorTest <E extends PdfElement> {
    public abstract Class<? extends Exception> getExpectedException();
    public abstract String getExpectedExceptionMessage();
    public abstract E parseDocument(Class<?> clazz);
    
    @Test
    public void checkErrorTest() {
        Class<? extends Exception> exception = getExpectedException();
        String message = getExpectedExceptionMessage();
        try {
            parseDocument(this.getClass());
            Assert.fail("Expected exception: "  + exception);
        } catch(Exception e) {
            Assert.assertTrue(exception.isAssignableFrom(e.getClass()), 
                    "Expected exception: "  + exception + ", but is instead: " + e);
            Assert.assertTrue(e.getMessage().matches(message), 
                    "Expected message: " + message + ", but is instead: "  + e.getMessage());
        }
    }
}
