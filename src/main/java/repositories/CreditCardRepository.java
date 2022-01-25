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
                "password          VARCHAR (255) ," +//put first pass here
                "balance           BIGINT," +
                "second_password   VARCHAR (255)," +
                "card_number       VARCHAR (255)," +
                "holder_name       VARCHAR (255) ," +
                "CVV2              VARCHAR (255)," +
                "expirationDate    DATE " +
                ");";
        connection.prepareStatement(creditCardTable).execute();
    }

    public void insertIntoCreditCard(CreditCard creditCard) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO credit_card (password,second_password,cardNumber,holderName,CVV2,expirationDate" +
                        ",transactionId,) VALUES (?,?,?,?,?,?,?);");
        preparedStatement.setString(1,creditCard.getPassword());
        preparedStatement.setLong(2,creditCard.getBalance());
        preparedStatement.setString(3,creditCard.getSecondPassword());
        preparedStatement.setString(4,creditCard.getCardNumber());
        preparedStatement.setString(5,creditCard.getHolderName());
        preparedStatement.setString(6,creditCard.getCVV2());
        preparedStatement.setDate(7, (Date) creditCard.getExpirationDate());
        preparedStatement.executeUpdate();
    }

    public boolean existsById(Integer id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT COUNT(*) as creditCardExist FROM credit_card WHERE id = ?;");
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("creditCardExist") > 0;
        }
        return false;
    }

    public void update(CreditCard creditCard) throws SQLException {
        if(creditCard.getId() != null){
            if(existsById(creditCard.getId())){
                PreparedStatement preparedStatement =  connection.prepareStatement(
                        "UPDATE credit_card SET password = ? , balance = ?,second_password = ?" +
                                " ,card_number = ? , holder_name = ? ," +
                                " cvv2 = ? , expirationdate = ? WHERE id = ?;");
                preparedStatement.setString(1,creditCard.getPassword());
                preparedStatement.setLong(2,creditCard.getBalance());
                preparedStatement.setString(3,creditCard.getSecondPassword());
                preparedStatement.setString(4,creditCard.getCardNumber());
                preparedStatement.setString(5,creditCard.getHolderName());
                preparedStatement.setString(6,creditCard.getCVV2());
                preparedStatement.setDate(7, (Date) creditCard.getExpirationDate());
                preparedStatement.setInt(8, creditCard.getId());
                preparedStatement.executeUpdate();
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

    public Boolean findDestinationCreditCard(CreditCard cardNumber) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM credit_card WHERE card_number = ?;");
        preparedStatement.setString(1,cardNumber.getCardNumber());
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

    public CreditCard findByCardNumber(String cardNumber) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM credit_card WHERE card_number = ?;");
        preparedStatement.setString(1,cardNumber);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            CreditCard creditCard1 = new CreditCard(resultSet.getInt("id")
                    , resultSet.getLong("balance")
                    , resultSet.getString("password")
                    , resultSet.getString("second_password")
                    , resultSet.getString("card_number")
                    , resultSet.getString("holder_name")
                    , resultSet.getString("CVV2")
                    , resultSet.getDate("expirationDate"));
            return creditCard1;
        }
        return null;
    }

    /*public CreditCard findCreditCardInformation(CreditCard creditCard) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT second_password,CVV2,expirationDate FROM credit_card" +
                        " WHERE second_password = ?,CVV2 = ?,expirationDate = ?;");
        preparedStatement.setString(1,creditCard.getSecondPassword());
        preparedStatement.setString(2,creditCard.getCVV2());
        preparedStatement.setDate(3, (Date) creditCard.getExpirationDate());
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        CreditCard creditCard1 = new CreditCard(resultSet.getInt("id")
                ,resultSet.getLong("balance")
                ,resultSet.getString("password")
                ,resultSet.getString("second_password")
                ,resultSet.getString("card_number")
                ,resultSet.getString("holder_name")
                ,resultSet.getString("CVV2")
                ,resultSet.getDate("expirationDate"));
        return creditCard1;
    }*/

    public Boolean findPassword(String password) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM credit_card WHERE password = ?;");
        preparedStatement.setString(1,password);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

    public Boolean findCVV2(String CVV2) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM credit_card WHERE CVV2 = ?;");
        preparedStatement.setString(1,CVV2);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

    public Boolean findExpirationDate(Date expirationDate) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM credit_card WHERE CVV2 = ?;");
        preparedStatement.setDate(1,expirationDate);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }
}
