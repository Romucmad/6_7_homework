package b_Money;

import b_Money.Account;
import b_Money.AccountDoesNotExistException;
import b_Money.Bank;
import b_Money.Money;

public class TimedPayment {
    private int interval, next;
    private Account fromaccount;
    private Money amount;
    private Bank tobank;
    private String toaccount;

    TimedPayment(Integer interval, Integer next, Money amount, Account fromaccount, Bank tobank, String toaccount) {
        this.interval = interval;
        this.next = next;
        this.amount = amount;
        this.fromaccount = fromaccount;
        this.tobank = tobank;
        this.toaccount = toaccount;
    }

    /* Return value indicates whether or not a transfer was initiated */
    public Boolean tick() {
        if (next == 0) {
            next = interval;

            fromaccount.withdraw(amount);

            try {
                tobank.deposit(toaccount, amount);
            }
            catch (AccountDoesNotExistException e) {
                /* Revert transfer.
                 * In reality, this should probably cause a notification somewhere. */
                fromaccount.deposit(amount);
            }
            return true;
        }
        else {
            next--;
            return false;
        }
    }
}