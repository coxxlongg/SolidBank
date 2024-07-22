package kz.ori.solidbankapp;

import java.util.List;

public class AccountListingServiceImpl implements AccountListingService {
    private AccountDAO accountDAO;

    public AccountListingServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public List<Account> listAllAccounts() {
        return accountDAO.findAll();
    }
}
