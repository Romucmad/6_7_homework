package b_Money;//package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
    Currency SEK, DKK, NOK, EUR;
    Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;

    @Before
    public void setUp() throws Exception {
        SEK = new Currency("SEK", 0.15);
        DKK = new Currency("DKK", 0.20);
        EUR = new Currency("EUR", 1.5);
        SEK100 = new Money(10000, SEK);
        EUR10 = new Money(1000, EUR);
        SEK200 = new Money(20000, SEK);
        EUR20 = new Money(2000, EUR);
        SEK0 = new Money(0, SEK);
        EUR0 = new Money(0, EUR);
        SEKn100 = new Money(-10000, SEK);
    }

    @Test
    public void testGetAmount() {
        assertEquals("Should be 10000", new Integer(10000), SEK100.getAmount());
        assertEquals("Should be 1000", new Integer(1000), EUR10.getAmount());
    }

    @Test
    public void testGetCurrency() {
        assertEquals("Should be SEK", SEK, SEK100.getCurrency());
        assertEquals("Should be EUR", EUR, EUR10.getCurrency());

    }

    @Test
    public void testToString() {
        assertEquals("10000 SEK", SEK100.toString());
        assertEquals("1000 EUR", EUR10.toString());

    }

    @Test
    public void testUniversalValue() {
        assertEquals(new Integer(1500), SEK100.universalValue());
        assertEquals(new Integer(1500), EUR10.universalValue());

    }

    @Test
    public void testEqualsMoney() {
        assertTrue(SEK100.equals(EUR10));
        assertFalse(SEKn100.equals(SEK100));
    }

    @Test
    public void testAdd() {
        Money SEK100AddedSEK0 = SEK100.add(SEK0);
        assertTrue(SEK100AddedSEK0.equals(SEK100));

        Money EURO10AddedSEK100 = EUR10.add(SEK100);
        assertEquals(new Integer(2000),EURO10AddedSEK100.getAmount());

    }

    @Test
    public void testSub() {
        Money SEK100AddedSEK0 = SEK100.sub(SEK0);
        assertTrue(SEK100AddedSEK0.equals(SEK100));

        Money EURO10AddedSEK100 = EUR10.sub(SEK100);
        assertEquals(new Integer(0),EURO10AddedSEK100.getAmount());

    }

    @Test
    public void testIsZero() {
        assertTrue(EUR0.isZero());
        assertFalse(SEK100.isZero());

    }

    @Test
    public void testNegate() {
        Money SEK100Negated = SEK100.negate();
        assertEquals(new Integer(-10000),SEK100Negated.getAmount());

        Money SEKPositive = SEKn100.negate();
        assertEquals(new Integer(10000),SEKPositive.getAmount());

    }

    @Test
    public void testCompareTo() {
        assertEquals(0, SEK100.compareTo(EUR10));
        assertEquals(1, SEK100.compareTo(SEK0));
        assertEquals(-1, SEK0.compareTo(SEK100));

    }
}
