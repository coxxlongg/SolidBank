package kz.ori.solidbankapp;

public abstract class AccountDeposit extends Account {
    public AccountDeposit(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public void withdraw(double amount) {
        throw new UnsupportedOperationException("Withdrawals are not allowed for this account type");
    }
}
