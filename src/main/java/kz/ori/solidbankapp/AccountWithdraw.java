package kz.ori.solidbankapp;

public abstract class AccountWithdraw extends Account {
    public AccountWithdraw(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && getBalance() >= amount) {
            deposit(-amount);
        } else {
            throw new IllegalArgumentException("Insufficient funds or invalid amount");
        }
    }
}

