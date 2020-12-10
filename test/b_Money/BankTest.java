package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
    Currency SEK, DKK;
    Bank SweBank, Nordea, DanskeBank;

    @Before
    public void setUp() throws Exception {
        DKK = new Currency("DKK", 0.20);
        SEK = new Currency("SEK", 0.15);
        SweBank = new Bank("SweBank", SEK);
        Nordea = new Bank("Nordea", SEK);
        DanskeBank = new Bank("DanskeBank", DKK);
        SweBank.openAccount("Ulrika");
        SweBank.openAccount("Bob");
        Nordea.openAccount("Bob");
        DanskeBank.openAccount("Gertrud");
    }

    @Test
    public void testGetName() {
        assertEquals("SweBank", SweBank.getName());
        assertEquals("Nordea", Nordea.getName());

    }

    @Test
    public void testGetCurrency() {
        assertSame(SweBank.getCurrency(), SEK);
        assertSame(DanskeBank.getCurrency(), DKK);
    }

    @Test
    public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
        try {
            SweBank.openAccount("Ulrika");
            fail("Should throw accountExist exception");
        } catch (AccountExistsException ignored) {}

        SweBank.openAccount("Roman");

        assertFalse(SweBank.accountExists("DoesNotExists"));
        assertTrue(SweBank.accountExists("Roman"));

    }


    @Test
    public void testDeposit() throws AccountDoesNotExistException {

//      We test only throws here since the get balance is not tested yet

        Money m = new Money(300,this.DKK);
        try{
            SweBank.deposit("NoACcount",m);
            fail("Account does not exists should be thrown");
        }catch (AccountDoesNotExistException ignored){ }

        SweBank.deposit("Ulrika",m);
    }


    @Test
    public void testWithdraw() throws AccountDoesNotExistException {
//      We test only throws here since the get balance is not tested yet

        Money m = new Money(300,this.DKK);
        try{
            SweBank.withdraw("NoACcount",m);
            fail("Account does not exists should be thrown");
        }catch (AccountDoesNotExistException ignored){ }

        SweBank.withdraw("Ulrika",m);
    }

    @Test
    public void testGetBalance() throws AccountDoesNotExistException {
        try{
            SweBank.getBalance("DoesNotExists");
            fail("Account does not exists should be thrown");
        }catch (AccountDoesNotExistException ignored){ }


//       First we check 0 balance

        assertEquals(new Integer(0),this.SweBank.getBalance("Bob"));
        //deposit check

        Money m = new Money(300,this.SEK);
        SweBank.deposit("Bob",m);
        assertEquals(new Integer(300),this.SweBank.getBalance("Bob"));

        // check winthdraw
        Money mWithdraw = new Money(200,this.SEK);
        SweBank.withdraw("Bob",mWithdraw);
        assertEquals(new Integer(100),this.SweBank.getBalance("Bob"));


    }

    @Test
    public void testTransfer() throws AccountDoesNotExistException {
        //deposit money to bob
        Money m = new Money(300,this.SEK);
        SweBank.deposit("Bob",m);
        Money transfer200 = new Money(200,this.SEK);
        // check send to non existing account
        try{
            SweBank.transfer("Bob","AccountDoesNotExist",transfer200);
            fail("Account does not exists thrown");
        } catch (AccountDoesNotExistException ignored){}

        // check send from not existing account
        try{
            SweBank.transfer("DoesNotExist","Bob",transfer200);
            fail("Account does not exists thrown");
        } catch (AccountDoesNotExistException ignored){}
        // check is ok
        assertEquals(new Integer(300),SweBank.getBalance("Bob"));
        // send to urlika
        SweBank.transfer("Bob","Ulrika",transfer200);
        // check their things after
        assertEquals(new Integer(200),SweBank.getBalance("Ulrika"));
        assertEquals(new Integer(100),SweBank.getBalance("Bob"));


    }

    @Test
    public void testTimedPayment() throws AccountDoesNotExistException {

        //  super complicated to me

    }
}
