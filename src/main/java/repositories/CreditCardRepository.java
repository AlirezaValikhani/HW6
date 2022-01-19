package repositories;

import model.CreditCard;

import java.sql.*;

public class CreditCardRepository {
    private Connection connection;

    public CreditCardRepository(Connection connection) {
        this.connection = connection;
    }

    public void creatCreditCardTable() throws SQLException {
        String creditCardTable = "CREATE TABLE IF NOT EXISTS credit_card(" +
                "id                SERIAL PRIMARY KEY," +
                "second_password   INTEGER," +
                "card_number       INTEGER," +
                "holder_name       VARCHAR (255) ," +
                "account_balance   INTEGER ," +
                "CVV2              INTEGER " +
                "expirationDate    DATE ," +
                "account_id        INTEGER," +
                "transactionId     INTEGER" +
                ")";
        connection.prepareStatement(creditCardTable).execute();
        connection.close();
    }

    public void insertIntoCreditCard(CreditCard creditCard) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO credit_card (second_password,cardNumber,holderName,accountBalance,CVV2,expirationDate" +
                        ",accountId,transactionId,) VALUES (?,?,?,?,?,?,?,?);");
        preparedStatement.setInt(1,creditCard.getCardNumber());
        preparedStatement.setInt(2,creditCard.getSecondPassword());
        preparedStatement.setString(3,creditCard.getHolderName());
        preparedStatement.setInt(4,creditCard.getAccountBalance());
        preparedStatement.setInt(5,creditCard.getCVV2());
        preparedStatement.setDate(6, (Date) creditCard.getExpirationDate());
        preparedStatement.setInt(7,creditCard.getAccountId());
        preparedStatement.setInt(8,creditCard.getTransactionId());
        preparedStatement.executeUpdate();
        connection.close();
    }

    public boolean existsById(Integer id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT COUNT(*) as creditCardExist FROM credit_card WHERE id = ?;");
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("bankExist") > 0;
        }
        return false;
    }

    public void update(CreditCard creditCard) throws SQLException {
        if(creditCard.getId() != null){
            if(existsById(creditCard.getId())){
                PreparedStatement preparedStatement =  connection.prepareStatement(
                        "UPDATE credit_card SET second_password = ? ,cardNumber = ? , holderName = ? ,accountBalance = ? ," +
                                " CVV2 = ? , expirationDate = ? , accountId = ?,transactionId = ?;");
                preparedStatement.setInt(1,creditCard.getCardNumber());
                preparedStatement.setInt(2,creditCard.getSecondPassword());
                preparedStatement.setString(3,creditCard.getHolderName());
                preparedStatement.setInt(4,creditCard.getAccountBalance());
                preparedStatement.setInt(5,creditCard.getCVV2());
                preparedStatement.setDate(6, (Date) creditCard.getExpirationDate());
                preparedStatement.setInt(7,creditCard.getAccountId());
                preparedStatement.setInt(8,creditCard.getTransactionId());
                preparedStatement.executeUpdate();
                connection.close();
            }
        }
    }

    public void delete(CreditCard creditCard) throws SQLException {
        if (creditCard.getId() != null) {
            if (existsById(creditCard.getId())){
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM employee WHERE id = ?;");
                preparedStatement.setInt(1,creditCard.getId());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
        }
    }
}
