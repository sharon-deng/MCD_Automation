package automation.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Verify {

    private static final Logger LOGGER = LogManager.getLogger();



    /**
     * Asserts that a condition is true. If it isn't, an AssertionError, with the given message, is
     * thrown.
     *
     * @param condition the condition to evaluate
     * @param message the assertion error message
     */
    public static void assertTrue(boolean condition, String message) {
        try{
            org.testng.Assert.assertTrue(condition,message);
            LOGGER.debug("<assertTrue> returns True - " + message);
            Snapshot.attachDriverSnapShot("Pass: - " + message);
//            insertCheckpointToDb(message, true);
        } catch (AssertionError error){
//            insertCheckpointToDb(message, false);
            LOGGER.error("<assertTrue> returns False - " + message, error);
            Snapshot.attachDriverSnapShot("Fail: - " + message);
            org.testng.Assert.assertTrue(condition,message);
        }
    }


    /**
     * Asserts that a condition is false. If it isn't, an AssertionError, with the given message, is
     * thrown.
     *
     * @param condition the condition to evaluate
     * @param message the assertion error message
     */
    public static void assertFalse(boolean condition, String message) {
        try{
            org.testng.Assert.assertFalse(condition,message);
            LOGGER.debug("<assertFalse> returns False  - " + message);
            Snapshot.attachDriverSnapShot("Pass: - " + message);
//            insertCheckpointToDb(message, true);
        } catch (AssertionError error){
//            insertCheckpointToDb(message, false);
            LOGGER.error("<assertFalse> returns True - " + message, error);
            Snapshot.attachDriverSnapShot("Fail: - " + message);
            org.testng.Assert.assertFalse(condition,message);
        }
    }


    /**
     * Fails a test with the given message.
     *
     * @param message the assertion error message
     */
    public static void fail(String message) {
        LOGGER.error("<fail> - " + message);
        Snapshot.attachDriverSnapShot("Fail: - " + message);
        org.testng.Assert.fail(message);
    }


    /**
     * Fails a test with the given message.
     *
     * @param message the assertion error message
     */
    public static void fail(String message, Throwable ex) {
        LOGGER.error("<fail> - " + message, ex);
        Snapshot.attachDriverSnapShot("Fail: - " + message);
        org.testng.Assert.fail(message);
    }



    /**
     * Asserts that two objects are equal. If they are not, an AssertionError, with the given message,
     * is thrown.
     *
     * @param actual the actual value
     * @param expected the expected value
     * @param message the assertion error message
     */
    public static void assertEquals(Object actual, Object expected, String message) {
        try{
            org.testng.Assert.assertEquals(actual, expected, message);
            equalsPass(message);
        } catch (AssertionError error){
            equalsFail(message, error);
            org.testng.Assert.assertEquals(actual, expected, message);
        }
    }

    private static void equalsPass(String message){
        LOGGER.debug("<assertEquals> returns Equals - " + message);
        Snapshot.attachDriverSnapShot("Pass: - " + message);
    }
    private static void equalsFail(String message, AssertionError error){
        LOGGER.error("<assertEquals> returns Not Equals - " + message, error);
        Snapshot.attachDriverSnapShot("Fail: - " + message);
    }

    /**
     * Asserts that two arrays contain the same elements in the same order. If they do not, an
     * AssertionError, with the given message, is thrown.
     *
     * @param actual the actual value
     * @param expected the expected value
     * @param message the assertion error message
     */
    public static void assertEquals(byte[] actual, byte[] expected, String message) {
        try{
            org.testng.Assert.assertEquals(actual, expected, message);
            equalsPass(message);
        } catch (AssertionError error){
            equalsFail(message, error);
            org.testng.Assert.assertEquals(actual, expected, message);
        }
    }


    /**
     * Asserts that two arrays contain the same elements in the same order. If they do not, an
     * AssertionError, with the given message, is thrown.
     *
     * @param actual the actual value
     * @param expected the expected value
     * @param message the assertion error message
     */
    public static void assertEquals(short[] actual, short[] expected, String message) {
        try{
            org.testng.Assert.assertEquals(actual, expected, message);
            equalsPass(message);
        } catch (AssertionError error){
            equalsFail(message, error);
            org.testng.Assert.assertEquals(actual, expected, message);
        }
    }



    /**
     * Asserts that two arrays contain the same elements in the same order. If they do not, an
     * AssertionError, with the given message, is thrown.
     *
     * @param actual the actual value
     * @param expected the expected value
     * @param message the assertion error message
     */
    public static void assertEquals(int[] actual, int[] expected, String message) {
        try{
            org.testng.Assert.assertEquals(actual, expected, message);
            equalsPass(message);
        } catch (AssertionError error){
            equalsFail(message, error);
            org.testng.Assert.assertEquals(actual, expected, message);
        }
    }

    /**
     * Asserts that two arrays contain the same elements in the same order. If they do not, an
     * AssertionError, with the given message, is thrown.
     *
     * @param actual the actual value
     * @param expected the expected value
     * @param message the assertion error message
     */
    public static void assertEquals(boolean[] actual, boolean[] expected, String message) {
        try{
            org.testng.Assert.assertEquals(actual, expected, message);
            equalsPass(message);
        } catch (AssertionError error){
            equalsFail(message, error);
            org.testng.Assert.assertEquals(actual, expected, message);
        }
    }


    /**
     * Asserts that two arrays contain the same elements in the same order. If they do not, an
     * AssertionError, with the given message, is thrown.
     *
     * @param actual the actual value
     * @param expected the expected value
     * @param message the assertion error message
     */
    public static void assertEquals(char[] actual, char[] expected, String message) {
        try{
            org.testng.Assert.assertEquals(actual, expected, message);
            equalsPass(message);
        } catch (AssertionError error){
            equalsFail(message, error);
            org.testng.Assert.assertEquals(actual, expected, message);
        }
    }


    /**
     * Asserts that two arrays contain the same elements in the same order. If they do not, an
     * AssertionError, with the given message, is thrown.
     *
     * @param actual the actual value
     * @param expected the expected value
     * @param message the assertion error message
     */
    public static void assertEquals(float[] actual, float[] expected, String message) {
        try{
            org.testng.Assert.assertEquals(actual, expected, message);
            equalsPass(message);
        } catch (AssertionError error){
            equalsFail(message, error);
            org.testng.Assert.assertEquals(actual, expected, message);
        }
    }


    /**
     * Asserts that two arrays contain the equal elements concerning a delta in the same order. If
     * they do not, an
     * AssertionError is thrown.
     *
     * @param actual the actual value
     * @param expected the expected value
     * @param delta the absolute tolerable difference between the actual and expected values
     * @param message the assertion error message
     */
    public static void assertEquals(float[] actual, float[] expected, float delta, String message) {
        try{
            org.testng.Assert.assertEquals(actual, expected, delta, message);
            equalsPass(message);
        } catch (AssertionError error){
            equalsFail(message, error);
            org.testng.Assert.assertEquals(actual, expected, delta, message);
        }
    }


    /**
     * Asserts that two arrays contain the same elements in the same order. If they do not, an
     * AssertionError, with the given message, is thrown.
     *
     * @param actual the actual value
     * @param expected the expected value
     * @param message the assertion error message
     */
    public static void assertEquals(double[] actual, double[] expected, String message) {
        try{
            org.testng.Assert.assertEquals(actual, expected, message);
            equalsPass(message);
        } catch (AssertionError error){
            equalsFail(message, error);
            org.testng.Assert.assertEquals(actual, expected, message);
        }
    }


    /**
     * Asserts that two arrays contain the equal elements concerning a delta in the same order. If they do not, an
     * AssertionError, with the given message, is thrown.
     *
     * @param actual the actual value
     * @param expected the expected value
     * @param delta the absolute tolerable difference between the actual and expected values
     * @param message the assertion error message
     */
    public static void assertEquals(double[] actual, double[] expected, double delta, String message) {
        try{
            org.testng.Assert.assertEquals(actual, expected, delta, message);
            equalsPass(message);
        } catch (AssertionError error){
            equalsFail(message, error);
            org.testng.Assert.assertEquals(actual, expected, delta, message);
        }
    }


    /**
     * Asserts that two arrays contain the same elements in the same order. If they do not, an
     * AssertionError, with the given message, is thrown.
     *
     * @param actual the actual value
     * @param expected the expected value
     * @param message the assertion error message
     */
    public static void assertEquals(long[] actual, long[] expected, String message) {
        try{
            org.testng.Assert.assertEquals(actual, expected, message);
            equalsPass(message);
        } catch (AssertionError error){
            equalsFail(message, error);
            org.testng.Assert.assertEquals(actual, expected, message);
        }
    }

    /**
     * Asserts that two Strings are equal. If they are not, an AssertionError, with the given message,
     * is thrown.
     *
     * @param actual the actual value
     * @param expected the expected value
     * @param message the assertion error message
     */
    public static void assertEquals(String actual, String expected, String message) {
        try{
            org.testng.Assert.assertEquals(actual, expected, message);
            equalsPass(message);
        } catch (AssertionError error){
            equalsFail(message, error);
            org.testng.Assert.assertEquals(actual, expected, message);
        }
    }


    /**
     * Asserts that two doubles are equal concerning a delta. If they are not, an AssertionError, with
     * the given message, is thrown. If the expected value is infinity then the delta value is
     * ignored.
     *
     * @param actual the actual value
     * @param expected the expected value
     * @param delta the absolute tolerable difference between the actual and expected values
     * @param message the assertion error message
     */
    public static void assertEquals(double actual, double expected, double delta, String message) {
        try{
            org.testng.Assert.assertEquals(actual, expected, delta, message);
            equalsPass(message);
        } catch (AssertionError error){
            equalsFail(message, error);
            org.testng.Assert.assertEquals(actual, expected, delta, message);
        }
    }


    /**
     * Asserts that two doubles are equal. If they are not, an AssertionError, with the given message,
     * is thrown.
     *
     * @param actual the actual value
     * @param expected the expected value
     * @param message the assertion error message
     */
    public static void assertEquals(double actual, double expected, String message) {
        try{
            org.testng.Assert.assertEquals(actual, expected, message);
            equalsPass(message);
        } catch (AssertionError error){
            equalsFail(message, error);
            org.testng.Assert.assertEquals(actual, expected, message);
        }
    }

    /**
     * Asserts that two floats are equal concerning a delta. If they are not, an AssertionError, with
     * the given message, is thrown. If the expected value is infinity then the delta value is
     * ignored.
     *
     * @param actual the actual value
     * @param expected the expected value
     * @param delta the absolute tolerable difference between the actual and expected values
     * @param message the assertion error message
     */
    public static void assertEquals(float actual, float expected, float delta, String message) {
        try{
            org.testng.Assert.assertEquals(actual, expected, delta, message);
            equalsPass(message);
        } catch (AssertionError error){
            equalsFail(message, error);
            org.testng.Assert.assertEquals(actual, expected, delta, message);
        }
    }


    /**
     * Asserts that two floats are equal. If they are not, an AssertionError, with the given message,
     * is thrown.
     *
     * @param actual the actual value
     * @param expected the expected value
     * @param message the assertion error message
     */
    public static void assertEquals(float actual, float expected, String message) {
        try{
            org.testng.Assert.assertEquals(actual, expected, message);
            equalsPass(message);
        } catch (AssertionError error){
            equalsFail(message, error);
            org.testng.Assert.assertEquals(actual, expected, message);
        }
    }


    /**
     * Asserts that two longs are equal. If they are not, an AssertionError, with the given message,
     * is thrown.
     *
     * @param actual the actual value
     * @param expected the expected value
     * @param message the assertion error message
     */
    public static void assertEquals(long actual, long expected, String message) {
        try{
            org.testng.Assert.assertEquals(actual, expected, message);
            equalsPass(message);
        } catch (AssertionError error){
            equalsFail(message, error);
            org.testng.Assert.assertEquals(actual, expected, message);
        }
    }


    /**
     * Asserts that two booleans are equal. If they are not, an AssertionError, with the given
     * message, is thrown.
     *
     * @param actual the actual value
     * @param expected the expected value
     * @param message the assertion error message
     */
    public static void assertEquals(boolean actual, boolean expected, String message) {
        try{
            org.testng.Assert.assertEquals(actual, expected, message);
            equalsPass(message);
        } catch (AssertionError error){
            equalsFail(message, error);
            org.testng.Assert.assertEquals(actual, expected, message);
        }
    }


    /**
     * Asserts that two bytes are equal. If they are not, an AssertionError, with the given message,
     * is thrown.
     *
     * @param actual the actual value
     * @param expected the expected value
     * @param message the assertion error message
     */
    public static void assertEquals(byte actual, byte expected, String message) {
        try{
            org.testng.Assert.assertEquals(actual, expected, message);
            equalsPass(message);
        } catch (AssertionError error){
            equalsFail(message, error);
            org.testng.Assert.assertEquals(actual, expected, message);
        }
    }

    /**
     * Asserts that two chars are equal. If they are not, an AssertionFailedError, with the given
     * message, is thrown.
     *
     * @param actual the actual value
     * @param expected the expected value
     * @param message the assertion error message
     */
    public static void assertEquals(char actual, char expected, String message) {
        try{
            org.testng.Assert.assertEquals(actual, expected, message);
            equalsPass(message);
        } catch (AssertionError error){
            equalsFail(message, error);
            org.testng.Assert.assertEquals(actual, expected, message);
        }
    }

    /**
     * Asserts that two shorts are equal. If they are not, an AssertionFailedError, with the given
     * message, is thrown.
     *
     * @param actual the actual value
     * @param expected the expected value
     * @param message the assertion error message
     */
    public static void assertEquals(short actual, short expected, String message) {
        try{
            org.testng.Assert.assertEquals(actual, expected, message);
            equalsPass(message);
        } catch (AssertionError error){
            equalsFail(message, error);
            org.testng.Assert.assertEquals(actual, expected, message);
        }
    }


    /**
     * Asserts that two ints are equal. If they are not, an AssertionFailedError, with the given
     * message, is thrown.
     *
     * @param actual the actual value
     * @param expected the expected value
     * @param message the assertion error message
     */
    public static void assertEquals(int actual, int expected, String message) {
        try{
            org.testng.Assert.assertEquals(actual, expected, message);
            equalsPass(message);
        } catch (AssertionError error){
            equalsFail(message, error);
            org.testng.Assert.assertEquals(actual, expected, message);
        }
    }

    /**
     * Asserts that an object isn't null. If it is, an AssertionFailedError, with the given message,
     * is thrown.
     *
     * @param object the assertion object
     * @param message the assertion error message
     */
    public static void assertNotNull(Object object, String message) {
        try{
            org.testng.Assert.assertNotNull(object, message);
            LOGGER.debug("<assertNotNull> returns Not Null - " + message);
            Snapshot.attachDriverSnapShot("Pass: - " + message);
//            insertCheckpointToDb(message, true);
        } catch (AssertionError error){
//            insertCheckpointToDb(message, false);
            LOGGER.error("<assertNotNull> returns Null - " + message, error);
            Snapshot.attachDriverSnapShot("Fail: - " + message);
            org.testng.Assert.assertNotNull(object, message);
        }
    }


    /**
     * Asserts that an object is null. If it is not, an AssertionFailedError, with the given message,
     * is thrown.
     *
     * @param object the assertion object
     * @param message the assertion error message
     */
    public static void assertNull(Object object, String message) {
        try{
            org.testng.Assert.assertNull(object, message);
            LOGGER.debug("<assertNull> returns Null - " + message);
            Snapshot.attachDriverSnapShot("Pass: - " + message);
//            insertCheckpointToDb(message, true);
        } catch (AssertionError error){
//            insertCheckpointToDb(message, false);
            LOGGER.error("<assertNull> returns Not Null - " + message, error);
            Snapshot.attachDriverSnapShot("Fail: - " + message);
            org.testng.Assert.assertNull(object, message);
        }
    }

    /**
     * Asserts that two objects refer to the same object. If they do not, an AssertionFailedError,
     * with the given message, is thrown.
     *
     * @param actual the actual value
     * @param expected the expected value
     * @param message the assertion error message
     */
    public static void assertSame(Object actual, Object expected, String message) {
        try{
            org.testng.Assert.assertSame(actual, expected, message);
            LOGGER.debug("<assertSame> returns Same - " + message);
            Snapshot.attachDriverSnapShot("Pass: - " + message);
//            insertCheckpointToDb(message, true);
        } catch (AssertionError error){
//            insertCheckpointToDb(message, false);
            LOGGER.error("<assertSame> returns Not Same - " + message, error);
            Snapshot.attachDriverSnapShot("Fail: - " + message);
            org.testng.Assert.assertSame(actual, expected, message);
        }
    }


    /**
     * Asserts that two objects do not refer to the same objects. If they do, an AssertionError, with
     * the given message, is thrown.
     *
     * @param actual the actual value
     * @param expected the expected value
     * @param message the assertion error message
     */
    public static void assertNotSame(Object actual, Object expected, String message) {
        try{
            org.testng.Assert.assertNotSame(actual, expected, message);
            LOGGER.debug("<assertNotSame> returns Not Same - " + message);
            Snapshot.attachDriverSnapShot("Pass: - " + message);
//            insertCheckpointToDb(message, true);
        } catch (AssertionError error){
//            insertCheckpointToDb(message, false);
            LOGGER.error("<assertNotSame> returns Same - " + message, error);
            Snapshot.attachDriverSnapShot("Fail: - " + message);
            org.testng.Assert.assertNotSame(actual, expected, message);
        }
    }

    /**
     * Asserts that two collections contain the same elements in the same order. If they do not, an
     * AssertionError, with the given message, is thrown.
     *
     * @param actual the actual value
     * @param expected the expected value
     * @param message the assertion error message
     */
    public static void assertEquals(Collection<?> actual, Collection<?> expected, String message) {
        try{
            org.testng.Assert.assertEquals(actual, expected, message);
            equalsPass(message);
        } catch (AssertionError error){
            equalsFail(message, error);
            org.testng.Assert.assertEquals(actual, expected, message);
        }
    }


    /**
     * Asserts that two iterators return the same elements in the same order. If they do not, an
     * AssertionError, with the given message, is thrown. Please note that this assert iterates over
     * the elements and modifies the state of the iterators.
     *
     * @param actual the actual value
     * @param expected the expected value
     * @param message the assertion error message
     */
    public static void assertEquals(Iterator<?> actual, Iterator<?> expected, String message) {
        try{
            org.testng.Assert.assertEquals(actual, expected, message);
            equalsPass(message);
        } catch (AssertionError error){
            equalsFail(message, error);
            org.testng.Assert.assertEquals(actual, expected, message);
        }
    }


    /**
     * Asserts that two iterables return iterators with the same elements in the same order. If they
     * do not, an AssertionError, with the given message, is thrown.
     *
     * @param actual the actual value
     * @param expected the expected value
     * @param message the assertion error message
     */
    public static void assertEquals(Iterable<?> actual, Iterable<?> expected, String message) {
        try{
            org.testng.Assert.assertEquals(actual, expected, message);
            equalsPass(message);
        } catch (AssertionError error){
            equalsFail(message, error);
            org.testng.Assert.assertEquals(actual, expected, message);
        }
    }

    /**
     * Asserts that two arrays contain the same elements in the same order. If they do not, an
     * AssertionError, with the given message, is thrown.
     *
     * @param actual the actual value
     * @param expected the expected value
     * @param message the assertion error message
     */
    public static void assertEquals(Object[] actual, Object[] expected, String message) {
        try{
            org.testng.Assert.assertEquals(actual, expected, message);
            equalsPass(message);
        } catch (AssertionError error){
            equalsFail(message, error);
            org.testng.Assert.assertEquals(actual, expected, message);
        }
    }

    /**
     * Asserts that two arrays contain the same elements in no particular order. If they do not, an
     * AssertionError, with the given message, is thrown.
     *
     * @param actual the actual value
     * @param expected the expected value
     * @param message the assertion error message
     */
    public static void assertEqualsNoOrder(Object[] actual, Object[] expected, String message) {
        try{
            org.testng.Assert.assertEqualsNoOrder(actual, expected, message);
            LOGGER.debug("<assertEqualsNoOrder> returns Equals - " + message);
            Snapshot.attachDriverSnapShot("Pass: - " + message);
//            insertCheckpointToDb(message, true);
        } catch (AssertionError error){
//            insertCheckpointToDb(message, false);
            LOGGER.error("<assertEqualsNoOrder> returns Not Equal - " + message, error);
            Snapshot.attachDriverSnapShot("Fail: - " + message);
            org.testng.Assert.assertEqualsNoOrder(actual, expected, message);
        }
    }


    /** Assert set equals */
    public static void assertEquals(Set<?> actual, Set<?> expected, String message) {
        try{
            org.testng.Assert.assertEquals(actual, expected, message);
            equalsPass(message);
        } catch (AssertionError error){
            equalsFail(message, error);
            org.testng.Assert.assertEquals(actual, expected, message);
        }
    }


    /** Asserts that two maps are equal. */
    public static void assertEquals(Map<?, ?> actual, Map<?, ?> expected, String message) {
        try{
            org.testng.Assert.assertEquals(actual, expected, message);
            LOGGER.debug("<assertEquals> returns Equal - " + message);
            Snapshot.attachDriverSnapShot("Pass: - " + message);
//            insertCheckpointToDb(message, true);
        } catch (AssertionError error){
//            insertCheckpointToDb(message, false);
            LOGGER.error("<assertEquals> returns Not Equal - " + message, error);
            Snapshot.attachDriverSnapShot("Fail: - " + message);
            org.testng.Assert.assertEquals(actual, expected, message);
        }
    }


    public static void assertNotEquals(Object actual1, Object actual2, String message) {
        try{
            org.testng.Assert.assertNotEquals(actual1, actual2, message);
            LOGGER.debug("<assertNotEquals> returns Not Equals - " + message);
            Snapshot.attachDriverSnapShot("Pass: - " + message);
//            insertCheckpointToDb(message, true);
        } catch (AssertionError error){
//            insertCheckpointToDb(message, false);
            LOGGER.error("<assertNotEquals> returns Equals - " + message, error);
            Snapshot.attachDriverSnapShot("Fail: - " + message);
            org.testng.Assert.assertNotEquals(actual1, actual2, message);
        }
    }

    public static void assertNotEquals(float actual1, float actual2, float delta, String message) {
        try{
            org.testng.Assert.assertNotEquals(actual1, actual2, delta, message);
            LOGGER.debug("<assertNotEquals> returns Not Equals - " + message);
            Snapshot.attachDriverSnapShot("Pass: - " + message);
//            insertCheckpointToDb(message, true);
        } catch (AssertionError error){
//            insertCheckpointToDb(message, false);
            LOGGER.error("<assertNotEquals> returns Equals - " + message, error);
            Snapshot.attachDriverSnapShot("Fail: - " + message);
            org.testng.Assert.assertNotEquals(actual1, actual2, delta, message);
        }
    }


    public static void assertNotEquals(double actual1, double actual2, double delta, String message) {
        try{
            org.testng.Assert.assertNotEquals(actual1, actual2, delta, message);
            LOGGER.debug("<assertNotEquals> returns Not Equals - " + message);
            Snapshot.attachDriverSnapShot("Pass: - " + message);
//            insertCheckpointToDb(message, true);
        } catch (AssertionError error){
//            insertCheckpointToDb(message, false);
            LOGGER.error("<assertNotEquals> returns Equals -" + message, error);
            Snapshot.attachDriverSnapShot("Fail: - " + message);
            org.testng.Assert.assertNotEquals(actual1, actual2, delta, message);
        }
    }


    public static void assertNotEquals(Set<?> actual, Set<?> expected, String message) {
        try{
            org.testng.Assert.assertNotEquals(actual, expected, message);
            LOGGER.debug("<assertNotEquals> returns Not Equals - " + message);
            Snapshot.attachDriverSnapShot("Pass: - " + message);
//            insertCheckpointToDb(message, true);
        } catch (AssertionError error){
//            insertCheckpointToDb(message, false);
            LOGGER.error("<assertNotEquals> returns Equals - " + message, error);
            Snapshot.attachDriverSnapShot("Fail: - " + message);
            org.testng.Assert.assertNotEquals(actual, expected, message);
        }
    }


    public static void assertNotEquals(Map<?, ?> actual, Map<?, ?> expected, String message) {
        try{
            org.testng.Assert.assertNotEquals(actual, expected, message);
            LOGGER.debug("<assertNotEquals> returns Not Equals - " + message);
            Snapshot.attachDriverSnapShot("Pass: - " + message);
//            insertCheckpointToDb(message, true);
        } catch (AssertionError error){
//            insertCheckpointToDb(message, false);
            LOGGER.error("<assertNotEquals> returns Equals - " + message, error);
            Snapshot.attachDriverSnapShot("Fail: - " + message);
            org.testng.Assert.assertNotEquals(actual, expected, message);
        }
    }

}
