package Repositories;

import model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    private Connection connection;

    public AccountRepository(Connection connection) {
        this.connection = connection;
    }

    public void creatAccountTable() throws SQLException {
        String accountTable = "CREATE TABLE IF NOT EXISTS account(" +
                "id             SERIAL PRIMARY KEY," +
                "balance        BIGINT," +
                "account_type   VARCHAR(255)," +
                "customer_id    INTEGER ," +
                "bank_id        INTEGER ," +
                "credit_card_id INTEGER," +
                "CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customer(id);" +
                "CONSTRAINT fk_bank FOREIGN KEY (bank_id) REFERENCES bank_branch(id);" +
                "CONSTRAINT fk_credit_card FOREIGN KEY (credit_card_id) REFERENCES credit_card(id);" +
                ")";
        connection.prepareStatement(accountTable).execute();
        connection.close();
    }

    public void insertIntoAccount(Account account) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO account (balance,account_type,customer_id,bank_id,credit_card_id) " +
                "VALUES (?,?,?,?,?);");
        preparedStatement.setLong(1,account.getBalance());
        preparedStatement.setString(2,account.getAccountType());
        preparedStatement.setLong(3,account.getCustomerId());
        preparedStatement.setInt(4,account.getBankId());
        preparedStatement.setInt(5,account.getCreditCardId());
        preparedStatement.executeUpdate();
        connection.close();
    }

    public boolean existsById(Integer id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT COUNT(*) as accountExist FROM account WHERE id = ?;");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("bankExist") > 0;
        }
        return false;
    }

    public void update(Account account) throws SQLException {
        if(account.getId() != null){
            if(existsById(account.getId())){
                PreparedStatement preparedStatement =  connection.prepareStatement(
                        "UPDATE account SET balance = ? , accountType = ? ,customerId = ? , bankId = ?,creditCardId = ?;");
                preparedStatement.setLong(1,account.getBalance());
                preparedStatement.setString(2,account.getAccountType());
                preparedStatement.setInt(3,account.getCustomerId());
                preparedStatement.setInt(4,account.getBankId());
                preparedStatement.setInt(1,account.getCreditCardId());
                preparedStatement.executeUpdate();
                connection.close();
            }
        }
    }

    public void delete(Account account) throws SQLException {
        if (account.getId() != null) {
            if (existsById(account.getId())){
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM account WHERE id = ?;");
                preparedStatement.setInt(1,account.getId());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
        }
    }

    /*public Account findById(ََAccount account) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM account WHERE id = ?;");
                preparedStatement.setLong(1, account.getId());
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                Account account1 = new Account(resultSet.getInt("id")
                        ,resultSet.getLong("balance")
                        ,resultSet.getString("account_type")
                        ,resultSet.getInt("customer_id")
                        ,resultSet.getInt("bank_id")
                        ,resultSet.getInt("credit_card_id"));
        return account1;
    }*/

    public List<Account> showCustomerAccounts(Integer customerId) throws SQLException {
        if(customerId != null) {
            if (existsById(customerId)) {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM account INNER JOIN customer ON account.customer_id = customer.id\n" +
                                "WHERE account.customer_id = ?;");
                preparedStatement.setInt(1,customerId);
                ResultSet resultSet = preparedStatement.executeQuery();
                List<Account> accounts = new ArrayList<>();
                while(resultSet.next()) {
                    accounts.add(new Account(resultSet.getInt("id")
                            , resultSet.getLong("balance")
                            , resultSet.getString("account_type")
                            , resultSet.getInt("customer_id")
                            , resultSet.getInt("bank_id")
                            , resultSet.getInt("credit_card_id")));
                }
                return accounts;
            }
        }
        return null;
    }
}
