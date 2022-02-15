package repositories;

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
                "credit_card_id INTEGER," +
                "bank_id        INTEGER," +
                "customer_id    INTEGER," +
                "CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customer(id)," +
                "CONSTRAINT fk_bank FOREIGN KEY (bank_id) REFERENCES bank_branch(id)," +
                "CONSTRAINT fk_credit_card FOREIGN KEY (credit_card_id) REFERENCES credit_card(id)" +
                ");";
        PreparedStatement preparedStatement = connection.prepareStatement(accountTable);
        preparedStatement.execute();
    }

    public Integer insertIntoAccount(Account account) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO account (balance,customer_id,credit_card_id) " +
                "VALUES (?,?,null) returning id;");
        preparedStatement.setDouble(1,account.getBalance());
        preparedStatement.setInt(2,account.getCustomer().getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return resultSet.getInt("id");
        }
        return null;
    }

    public boolean existsById(Integer id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT COUNT(*) as accountExist FROM account WHERE id = ?;");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("accountExist") > 0;
        }
        return false;
    }

    public void update(Integer id,Long balance,Integer bankId,Integer customerId,Integer creditCardId) throws SQLException {
        if(id != null){
            if(existsById(id)){
                PreparedStatement preparedStatement =  connection.prepareStatement(
                        "UPDATE account SET balance = ? ,credit_card_id = ?,bank_id = ?, customer_id = ?;");
                preparedStatement.setLong(1,balance);
                preparedStatement.setInt(2,bankId);
                preparedStatement.setInt(3,customerId);
                preparedStatement.setInt(4,creditCardId);
                preparedStatement.executeUpdate();
                connection.close();
            }
        }
    }

    public void delete(Integer id) throws SQLException {
        if (id != null) {
            if (existsById(id)){
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM account WHERE id = ?;");
                preparedStatement.setInt(1,id);
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
        }
    }

    public Account findById(Integer id) throws SQLException {
                PreparedStatement preparedStatement = connection
                        .prepareStatement("SELECT * FROM account WHERE id = ?;");
                preparedStatement.setLong(1,id);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                Account account1 = new Account(resultSet.getInt("id")
                        ,resultSet.getDouble("balance"));
        return account1;
    }

    public List<Account> showCustomerAccounts(Integer customerId) throws SQLException {
        if(customerId != null) {
            if (existsById(customerId)) {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT a.id,balance FROM account a" +
                                " INNER JOIN customer ON a.customer_id = customer.id" +
                                " WHERE a.customer_id = ?");
                preparedStatement.setInt(1,customerId);
                ResultSet resultSet = preparedStatement.executeQuery();
                List<Account> accounts = new ArrayList<>();
                while(resultSet.next()) {
                    accounts.add(new Account(resultSet.getInt("id")
                            ,resultSet.getDouble("balance")));
                }
                return accounts;
            }
        }
        return null;
    }
}
