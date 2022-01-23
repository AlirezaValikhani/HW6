package services;

import model.Account;
import repositories.AccountRepository;
import util.ConnectionProvider;

import java.sql.SQLException;

public class AccountService {
    AccountRepository accountRepository = new AccountRepository(ConnectionProvider.getConnection());

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void insertAnAccount(Account account) throws SQLException {
        accountRepository.insertIntoAccount(account);
    }


}
