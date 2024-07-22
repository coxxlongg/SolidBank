package kz.ori.solidbankapp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryAccountDAO implements AccountDAO {
    private List<Account> accounts = new ArrayList<>();
    private AtomicInteger accountIdCounter = new AtomicInteger(1);

    @Override
    public void save(Account account) {
        accounts.add(account);
    }

    @Override
    public Account findById(String accountNumber) {
        return accounts.stream()
                .filter(acc -> acc.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Account> findAll() {
        return new ArrayList<>(accounts);
    }

    @Override
    public void updateAccount(Account account) {
        Account existingAccount = findById(account.getAccountNumber());
        if (existingAccount != null) {
            accounts.remove(existingAccount);
            accounts.add(account);
        }
    }

    public int getNextAccountId() {
        return accountIdCounter.getAndIncrement();
    }
}
