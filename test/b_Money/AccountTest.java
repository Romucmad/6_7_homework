package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));
		SweBank.deposit("Alice", new Money(1000000, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
		// no idea how it should work
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		// no idea how it should work
	}

	@Test
	public void testAddWithdraw() {
		Money m = new Money(300,this.SEK);
		testAccount.withdraw(m);
		assertEquals(new Integer(10000000-300),testAccount.getBalance().getAmount());
	}

	
	@Test
	public void testGetBalance() {
		assertEquals(new Integer(10000000),testAccount.getBalance().getAmount());
	}
}
