package repositories;

import model.Transaction;

import java.sql.*;

public class TransactionRepository {
    private Connection connection;

    public TransactionRepository(Connection connection) {
        this.connection = connection;
    }

    public void creatCreditCardTable() throws SQLException {
        String transactionTable = "CREATE TABLE IF NOT EXISTS transaction(" +
                "id             SERIAL PRIMARY KEY," +
                "show_balance   VARCHAR (255)," +
                "withdraw       VARCHAR (255) ," +
                "deposit        VARCHAR (255) ," +
                "transfer       VARCHAR (255), " +
                "creditCardId   INTEGER ," +
                "CONSTRAINT fk_credit_card FOREIGN KEY (creditCardId) REFERENCES credit_card(id)" +
                ");";
        connection.prepareStatement(transactionTable).execute();
    }

    public void insertIntoTransaction(Transaction transaction) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO bank_transaction (show_balance,withdraw,deposit,transfer,creditCardId" +
                        ") VALUES (?,?,?,?,?);");
        preparedStatement.setString(1,transaction.getShowBalance());
        preparedStatement.setString(2,transaction.getWithdraw());
        preparedStatement.setString(3,transaction.getDeposit());
        preparedStatement.setString(4,transaction.getTransfer());
        preparedStatement.setInt(5,transaction.getCreditCardId());
        preparedStatement.executeUpdate();
        connection.close();
    }

    public boolean existsById(Integer id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT COUNT(*) as transactionExist FROM bank_transaction WHERE id = ?;");
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("bankExist") > 0;
        }
        return false;
    }

    public void update(Transaction transaction) throws SQLException {
        if(transaction.getId() != null){
            if(existsById(transaction.getId())){
                PreparedStatement preparedStatement =  connection.prepareStatement(
                        "UPDATE bank_transaction SET show_balance = ? , withdraw = ? ,deposit = ? ," +
                                " transfer = ? , creditCardId = ?;");
                preparedStatement.setString(1,transaction.getShowBalance());
                preparedStatement.setString(2,transaction.getWithdraw());
                preparedStatement.setString(3,transaction.getDeposit());
                preparedStatement.setString(4,transaction.getTransfer());
                preparedStatement.setInt(5,transaction.getCreditCardId());
                preparedStatement.executeUpdate();
                connection.close();
            }
        }
    }

    public void delete(Transaction transaction) throws SQLException {
        if (transaction.getId() != null) {
            if (existsById(transaction.getId())){
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM bank_transaction WHERE id = ?;");
                preparedStatement.setInt(1,transaction.getId());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
        }
    }
}
