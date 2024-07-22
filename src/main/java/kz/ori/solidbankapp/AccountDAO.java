package kz.ori.solidbankapp;

import java.util.List;

public interface AccountDAO {
    void save(Account account);
    Account findById(String accountNumber);
    List<Account> findAll();

    void updateAccount(Account account);
}

