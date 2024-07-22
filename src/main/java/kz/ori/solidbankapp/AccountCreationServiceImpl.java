package kz.ori.solidbankapp;

public class AccountCreationServiceImpl implements AccountCreationService {
    private AccountDAO accountDAO;

    public AccountCreationServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public Account createAccount(String type, String accountNumber, double balance) {
        Account account = switch (type.toUpperCase()) {
            case "CHECKING" -> new CheckingAccount(accountNumber, balance);
            case "SAVING" -> new SavingAccount(accountNumber, balance);
            case "FIXED" -> new FixedAccount(accountNumber, balance);
            default -> throw new IllegalArgumentException("Invalid account type");
        };
        accountDAO.save(account);
        return account;
    }
}