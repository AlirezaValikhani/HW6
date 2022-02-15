package services;

import model.Account;
import model.Customer;
import repositories.AccountRepository;
import repositories.CustomerRepository;
import util.ConnectionProvider;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class AccountService {
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void createTableAccount() throws SQLException {
        accountRepository.creatAccountTable();
    }


    public Integer insertIntoAccount(Account account) throws SQLException {
        return accountRepository.insertIntoAccount(account);
    }

    public void updateAccount(Integer id,Long balance,Integer bankId,Integer customerId,Integer creditCardId) throws SQLException {
        accountRepository.update(id, balance,bankId,customerId,creditCardId);
    }

    public void deleteAccount(Integer id) throws SQLException {
        accountRepository.delete(id);
    }

    public Account findById(Integer id) throws SQLException {
        return accountRepository.findById(id);
    }

    public List<Account> showAllAccounts(Integer id) throws SQLException {
        return accountRepository.showCustomerAccounts(id);
    }
}





