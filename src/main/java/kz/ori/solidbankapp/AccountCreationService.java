package kz.ori.solidbankapp;

public interface AccountCreationService {
    Account createAccount(String type, String accountNumber, double balance);
}
