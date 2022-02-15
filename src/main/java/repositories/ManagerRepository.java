package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ManagerRepository {
    private Connection connection;

    public ManagerRepository(Connection connection) {
        this.connection = connection;
    }

    public void creatManagerTable() throws SQLException {
        String managerTable = "CREATE TABLE IF NOT EXISTS manager(" +
                "id             SERIAL PRIMARY KEY," +
                "first_name     VARCHAR (255)," +
                "last_name      VARCHAR (255) ," +
                "user_name      VARCHAR (255)" +
                "password       VARCHAR (255)" +
                "national_cade  VARCHAR (255) ," +
                "phone_number   VARCHAR (255) ," +
                "bank_id        VARCHAR (255)" +
                ");";
        PreparedStatement preparedStatement = connection.prepareStatement(managerTable);
        preparedStatement.execute();
    }

    public void insertIntoManager(String firstName, String lastName, String userName, String password, String nationalCode, String phoneNumber, Integer bankId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO customer (first_name,last_name,user_name,password,national_cade,phone_number,bank_id) VALUES (?,?,?,?,?,?,?,?);");
        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);
        preparedStatement.setString(3, userName);
        preparedStatement.setString(4, password);
        preparedStatement.setString(5, nationalCode);
        preparedStatement.setString(6, phoneNumber);
        preparedStatement.setInt(7, bankId);
        preparedStatement.executeUpdate();
    }

    public void update(Integer id,String firstName,String lastName,String userName,String password,String nationalCode,String phoneNumber,Integer bankId) throws SQLException {
        if(id != null){
                PreparedStatement preparedStatement =  connection.prepareStatement(
                        "UPDATE customer SET first_name = ? , last_name = ? ,user_name = ? , password = ? ,national_code = ? ," +
                                " phonenumber = ?,bank_id = ?;");
                preparedStatement.setString(1,firstName);
                preparedStatement.setString(2,lastName);
                preparedStatement.setString(3,userName);
                preparedStatement.setString(4,password);
                preparedStatement.setString(5,nationalCode);
                preparedStatement.setString(6,phoneNumber);
                preparedStatement.setInt(7,bankId);
                preparedStatement.executeUpdate();
            }
        }

    public void delete(Integer id) throws SQLException {
        if (id != null) {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM customer WHERE id = ?;");
                preparedStatement.setInt(1,id);
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
        }
    }
