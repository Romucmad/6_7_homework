package b_Money;//package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;

	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() {

		assertEquals("SEK",SEK.getName());
		assertEquals("DKK",DKK.getName());

	}

	@Test
	public void testGetRate() {
		assertEquals(new Double(0.15),SEK.getRate());
		assertEquals(new Double(0.2),DKK.getRate());

	}

	@Test
	public void testSetRate() {
		Currency UAH = new Currency("SEK",0.13);
		assertEquals(new Double(0.13),UAH.getRate());
		UAH.setRate(0.12);
		assertEquals(new Double(0.12),UAH.getRate());

	}

	@Test
	public void testGlobalValue() {
		assertEquals(new Integer((int)(1000*0.15)),SEK.universalValue(1000));
		assertEquals(new Integer((int)(1000*0.20)),DKK.universalValue(1000));

	}

	@Test
	public void testValueInThisCurrency() {
			assertEquals(new Integer(750),DKK.valueInThisCurrency(1000,SEK));
	}

}
