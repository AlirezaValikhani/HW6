package repositories;

import model.Account;
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
                "password          INTEGER ," +//put first pass here
                "second_password   BIGINT," +
                "card_number       BIGINT," +
                "CVV2              BIGINT," +
                "expirationDate    DATE," +
                "customer_id       INTEGER," +
                "account_id        INTEGER " +
                ");";
        connection.prepareStatement(creditCardTable).execute();
    }

    public Integer insertIntoCreditCard(Integer password,Long secondPassword,Long cardNumber,Long CVV2,Timestamp expirationDate,Integer accountId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO credit_card (password,second_password,card_number,CVV2,expirationDate,account_id" +
                        ") VALUES (?,?,?,?,?,?) returning id;");
        preparedStatement.setInt(1,password);
        preparedStatement.setLong(2,secondPassword);
        preparedStatement.setLong(3,cardNumber);
        preparedStatement.setLong(4,CVV2);
        preparedStatement.setTimestamp(5, expirationDate);
        preparedStatement.setInt(6, accountId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return resultSet.getInt("id");
        }
        return null;
    }

    public Boolean existsById(Integer id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT COUNT(*) as creditCardExist FROM credit_card WHERE id = ?;");
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("creditCardExist") > 0;
        }
        return false;
    }

    public void update(Integer id,Integer password,Long balance,Long secondPassword,Long cardNumber,Long CVV2,Date expirationDate,Integer accountId) throws SQLException {
                PreparedStatement preparedStatement =  connection.prepareStatement(
                        "UPDATE credit_card SET password = ? , balance = ?" +
                                ",second_password = ?,card_number = ? ," +
                                " cvv2 = ? , expirationdate = ?,customer_id = ?,account_id = ? WHERE id = ?;");
                preparedStatement.setInt(1,password);
                preparedStatement.setLong(2,balance);
                preparedStatement.setLong(3,secondPassword);
                preparedStatement.setLong(4,cardNumber);
                preparedStatement.setLong(5,CVV2);
                preparedStatement.setDate(6, expirationDate);
                preparedStatement.setInt(7, accountId);
                preparedStatement.setInt(8, id);
                preparedStatement.executeUpdate();
            }

    public void updateToGetObject(CreditCard creditCard) throws SQLException {
        PreparedStatement preparedStatement =  connection.prepareStatement(
                "UPDATE credit_card SET password = ? , second_password = ?" +
                        " ,card_number = ? ," +
                        " cvv2 = ? , expirationdate = ?,accountId = ? WHERE id = ?;");
        preparedStatement.setInt(1,creditCard.getPassword());
        preparedStatement.setLong(2,creditCard.getSecondPassword());
        preparedStatement.setLong(3,creditCard.getCardNumber());
        preparedStatement.setLong(4,creditCard.getCVV2());
        preparedStatement.setTimestamp(5,creditCard.getExpirationDate());
        preparedStatement.setInt(6,creditCard.getAccount().getId());
        preparedStatement.setInt(7, creditCard.getId());
        preparedStatement.executeUpdate();
    }

    public void delete(Integer id) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM credit_card WHERE id = ?;");
                preparedStatement.setInt(1,id);
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }

    public Boolean findDestinationCreditCard(String cardNumber) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM credit_card WHERE card_number = ?;");
        preparedStatement.setString(1,cardNumber);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

    public CreditCard findByCardNumber(String cardNumber) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT cd.id,password," +
                        "second_password,card_number,cvv2," +
                        "expirationdate,a.id,balance FROM credit_card cd " +
                        "INNER JOIN account a ON cd.account_id = a.id " +
                        "WHERE card_number = ?;");
        preparedStatement.setString(1,cardNumber);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            return new CreditCard(resultSet.getInt(1)
                    , resultSet.getInt(2)
                    , resultSet.getLong(3)
                    , resultSet.getLong(4)
                    , resultSet.getLong(5)
                    , resultSet.getTimestamp(6)
                    ,(new Account(resultSet.getInt(7),resultSet.getDouble(8))));
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

    public CreditCard findByIdGetObject(Integer id) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT id,password,second_password,card_number FROM credit_card WHERE id = ?;");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            CreditCard creditCard1 = new CreditCard(resultSet.getInt("id")
                    , resultSet.getInt("password")
                    , resultSet.getLong("second_password")
                    , resultSet.getLong("card_number")
                    , resultSet.getLong("CVV2")
                    , resultSet.getTimestamp("expirationDate"));
            return creditCard1;
        }
        return null;
    }

    public Boolean findById(Integer id) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM credit_card WHERE id = ?;");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

    /*public CreditCard findCVV2(String CVV2) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM credit_card WHERE CVV2 = ?;");
        preparedStatement.setString(1, CVV2);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            CreditCard creditCard1 = new CreditCard(resultSet.getInt("id")
                    , resultSet.getLong("balance")
                    , resultSet.getInt("password")
                    , resultSet.getLong("second_password")
                    , resultSet.getLong("card_number")
                    , resultSet.getString("holder_name")
                    , resultSet.getLong("CVV2")
                    , resultSet.getDate("expirationDate"));
            return creditCard1;
        }
        return null;
    }

    public CreditCard findExpirationDate(Date expirationDate) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM credit_card WHERE CVV2 = ?;");
        preparedStatement.setDate(1, expirationDate);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            CreditCard creditCard1 = new CreditCard(resultSet.getInt("id")
                    , resultSet.getLong("balance")
                    , resultSet.getInt("password")
                    , resultSet.getLong("second_password")
                    , resultSet.getLong("card_number")
                    , resultSet.getString("holder_name")
                    , resultSet.getLong("CVV2")
                    , resultSet.getDate("expirationDate"));
            return creditCard1;
        }
        return null;
    }*/

    public void updatePassword(Integer password,Integer id) throws SQLException {
        if(password != null){
            PreparedStatement preparedStatement =  connection.prepareStatement(
                    "UPDATE credit_card SET password = ? WHERE id = ?;");
            preparedStatement.setInt(1,password);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
        }
    }

    public void updateSecondPassword(String secondPassword,Integer id) throws SQLException {
        if(secondPassword != null){
                PreparedStatement preparedStatement =  connection.prepareStatement(
                        "UPDATE credit_card SET second_password = ? WHERE id = ?;");
                preparedStatement.setString(1,secondPassword);
                preparedStatement.setInt(2,id);
                preparedStatement.executeUpdate();
        }
    }
}
