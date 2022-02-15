package repositories;

import model.Transaction;

import java.sql.*;

public class TransactionRepository {
    private Connection connection;

    public TransactionRepository(Connection connection) {
        this.connection = connection;
    }

    /*public void creatCreditCardTable() throws SQLException {
        String transactionTable = "CREATE TABLE IF NOT EXISTS transaction(" +
                "id             SERIAL PRIMARY KEY," +
                "dateTime       TIMESTAMP ," +
                "show_balance   VARCHAR (255)," +
                "deposit        VARCHAR (255) ," +
                "transfer       VARCHAR (255), " +
                "creditCardId   INTEGER ," +
                "CONSTRAINT fk_credit_card FOREIGN KEY (creditCardId) REFERENCES credit_card(id)" +
                ");";
        connection.prepareStatement(transactionTable).execute();
    }*/

    public void insertIntoTransaction(Timestamp dateTime,Integer sourceAccountId,Integer destinationAccountId ) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO transaction (dateTime,source_account_id,destination_account_id" +
                        ") VALUES (?,?,?);");
        preparedStatement.setTimestamp(1,dateTime);
        preparedStatement.setInt(2,sourceAccountId);
        preparedStatement.setInt(3,destinationAccountId);
        preparedStatement.executeUpdate();
    }

    public boolean existsById(Integer id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT COUNT(*) as transactionExist FROM transaction WHERE id = ?;");
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("transactionExist") > 0;
        }
        return false;
    }

    public void update(Integer id,Timestamp dateTime,Integer sourceAccountId,Integer destinationAccountId) throws SQLException {
        if(id != null){
            if(existsById(id)){
                PreparedStatement preparedStatement =  connection.
                        prepareStatement("UPDATE bank_transaction SET date_time = ? , source_account_id = ? ,destination_account_id = ?;");
                preparedStatement.setTimestamp(1,dateTime);
                preparedStatement.setInt(2,sourceAccountId);
                preparedStatement.setInt(3,destinationAccountId);
                preparedStatement.executeUpdate();
            }
        }
    }

    public void delete(Integer id) throws SQLException {
        if (id != null) {
            if (existsById(id)){
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM bank_transaction WHERE id = ?;");
                preparedStatement.setInt(1,id);
                preparedStatement.executeUpdate();
            }
        }
    }
}
