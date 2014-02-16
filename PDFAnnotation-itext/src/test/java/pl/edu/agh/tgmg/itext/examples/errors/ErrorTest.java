package pl.edu.agh.tgmg.itext.examples.errors;

import org.testng.Assert;
import org.testng.annotations.Test;

public abstract class ErrorTest {
    public abstract Class<? extends Exception> getExpectedException();
    public abstract String getExpectedExceptionMessage();
    public abstract Object parseDocument(Class<?> clazz);
    
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
