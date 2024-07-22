package kz.ori.solidbankapp;

import java.util.List;
import java.util.Scanner;

public class MyCLI implements WithdrawDepositCLIUI, CreateAccountOperationUI {
    private AccountDAO accountDAO;
    private AccountCreationService accountCreationService;
    private AccountListingService accountListingService;
    private Scanner scanner = new Scanner(System.in);

    public MyCLI(AccountDAO accountDAO, AccountCreationService accountCreationService, AccountListingService accountListingService) {
        this.accountDAO = accountDAO;
        this.accountCreationService = accountCreationService;
        this.accountListingService = accountListingService;
    }

    public void start() {
        while (true) {
            System.out.println("1. Create Account");
            System.out.println("2. List Accounts");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    listAccounts();
                    break;
                case 3:
                    handleDeposit();
                    break;
                case 4:
                    handleWithdraw();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void createAccount() {
        System.out.println("Enter account type (CHECKING, SAVING, FIXED): ");
        String type = scanner.next();
        System.out.println("Enter initial balance: ");
        double balance = scanner.nextDouble();
        String accountNumber = String.format("%03d%06d", 1, ((MemoryAccountDAO) accountDAO).getNextAccountId());
        createAccount(type, accountNumber, balance);
    }

    @Override
    public void createAccount(String type, String accountNumber, double balance) {
        try {
            accountCreationService.createAccount(type, accountNumber, balance);
            System.out.println("Account created successfully with Account Number: " + accountNumber);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void listAccounts() {
        List<Account> accounts = accountListingService.listAllAccounts();
        for (Account account : accounts) {
            System.out.println("Account Number: " + account.getAccountNumber() + ", Balance: " + account.getBalance());
        }
    }

    private void handleDeposit() {
        System.out.println("Enter account number: ");
        String accountNumber = scanner.next();
        System.out.println("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        deposit(accountNumber, amount);
    }

    @Override
    public void deposit(String accountNumber, double amount) {
        Account account = accountDAO.findById(accountNumber);
        if (account != null) {
            account.deposit(amount);
            accountDAO.updateAccount(account);
            System.out.println("Deposited " + amount + " to account " + accountNumber);
        } else {
            System.out.println("Account not found");
        }
    }

    private void handleWithdraw() {
        System.out.println("Enter account number: ");
        String accountNumber = scanner.next();
        System.out.println("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        withdraw(accountNumber, amount);
    }

    @Override
    public void withdraw(String accountNumber, double amount) {
        Account account = accountDAO.findById(accountNumber);
        if (account != null) {
            try {
                account.withdraw(amount);
                accountDAO.updateAccount(account);
                System.out.println("Withdrew " + amount + " from account " + accountNumber);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Account not found");
        }
    }
}
