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
                "password          BIGINT ," +//put first pass here
                "second_password   BIGINT," +
                "card_number       BIGINT," +
                "holder_name       VARCHAR (255) ," +
                "CVV2              INTEGER ," +
                "expirationDate    DATE ," +
                "account_id        INTEGER," +
                "transactionId     BIGINT" +
                ");";
        connection.prepareStatement(creditCardTable).execute();
    }

    public void insertIntoCreditCard(CreditCard creditCard) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO credit_card (password,second_password,cardNumber,holderName,CVV2,expirationDate" +
                        ",accountId,transactionId,) VALUES (?,?,?,?,?,?,?);");
        preparedStatement.setLong(1,creditCard.getPassword());
        preparedStatement.setLong(2,creditCard.getSecondPassword());
        preparedStatement.setLong(3,creditCard.getCardNumber());
        preparedStatement.setString(4,creditCard.getHolderName());
        preparedStatement.setInt(5,creditCard.getCVV2());
        preparedStatement.setDate(6, (Date) creditCard.getExpirationDate());
        preparedStatement.setInt(7,creditCard.getAccountId());
        preparedStatement.setLong(8,creditCard.getTransactionId());
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
                        "UPDATE credit_card SET password = ? ,second_password = ? ,cardNumber = ? , holderName = ? ," +
                                " CVV2 = ? , expirationDate = ? , accountId = ?,transactionId = ?;");
                preparedStatement.setLong(1,creditCard.getPassword());
                preparedStatement.setLong(2,creditCard.getSecondPassword());
                preparedStatement.setLong(3,creditCard.getCardNumber());
                preparedStatement.setString(4,creditCard.getHolderName());
                preparedStatement.setInt(5,creditCard.getCVV2());
                preparedStatement.setDate(6, (Date) creditCard.getExpirationDate());
                preparedStatement.setInt(7,creditCard.getAccountId());
                preparedStatement.setLong(8,creditCard.getTransactionId());
                preparedStatement.executeUpdate();
                connection.close();
            }
        }
    }

    public void delete(CreditCard creditCard) throws SQLException {
        if (creditCard.getId() != null) {
            if (existsById(creditCard.getId())){
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM credit_card WHERE id = ?;");
                preparedStatement.setInt(1,creditCard.getId());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
        }
    }

    public Boolean findDestinationCreditCard(Long creditCard) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM credit_card WHERE card_number = ?;");
        preparedStatement.setLong(1,creditCard);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) return true;
        else return false;
    }

    public Boolean findSecondPassword(CreditCard creditCard) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM credit_card WHERE second_password = ?;");
        preparedStatement.setLong(1,creditCard.getSecondPassword());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) return true;
        else return false;
    }

    public Boolean findCVV2(Integer CVV2) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM credit_card WHERE CVV2 = ?;");
        preparedStatement.setLong(1,CVV2);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) return true;
        else return false;
    }

    public Boolean findExpirationDate(Date expirationDate) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM credit_card WHERE CVV2 = ?;");
        preparedStatement.setDate(1,expirationDate);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) return true;
        else return false;
    }
}
