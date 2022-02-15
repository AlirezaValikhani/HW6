package util;


import model.CreditCard;
import repositories.AccountRepository;
import services.AccountService;

import java.sql.Connection;
import java.sql.SQLException;

public class Utilities {
    private Connection connection;
    AccountService accountService = new AccountService(new AccountRepository(ConnectionProvider.getConnection()));
    /*EmployeeRepository employeeRepository = new EmployeeRepository(ConnectionProvider.getConnection());
    AccountRepository accountRepository = new AccountRepository(ConnectionProvider.getConnection());*/

    public Utilities(Connection connection) {
        this.connection = connection;
    }

    public Boolean informationValidation(Integer accountId,CreditCard creditCard, Double amount, String CVV2, String secondPassword) throws SQLException {
        Double balance = accountService.findById(accountId).getBalance();
        if (balance > amount + 600 && creditCard.getCVV2().equals(CVV2) &&
                creditCard.getSecondPassword().equals(secondPassword)) {
            return true;
        } else {
            System.out.println("Your balance is not enough!!! ");
            return false;
        }
    }
}
