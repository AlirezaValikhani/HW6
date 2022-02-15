package repositories;

import model.BankBranch;
import model.Customer;
import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerRepository {
    private Connection connection;

    public CustomerRepository(Connection connection) {
        this.connection = connection;
    }

    public void creatCustomerTable() throws SQLException {
        String customerTable = "CREATE TABLE IF NOT EXISTS customer(" +
                "id             SERIAL PRIMARY KEY," +
                "first_name     VARCHAR (255)," +
                "last_name      VARCHAR (255) ," +
                "user_name      VARCHAR (255) UNIQUE ," +
                "password       VARCHAR (255)," +
                "national_code  VARCHAR (255) UNIQUE NOT NULL," +
                "phone_number   VARCHAR (255) ," +
                "bank_id        VARCHAR (255)" +
                ");";
        PreparedStatement preparedStatement = connection.prepareStatement(customerTable);
        preparedStatement.execute();
    }

    public Integer insertIntoCustomer(String firstName,String lastName,String userName,String password,String nationalCode,String phoneNumber) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO customer (first_name,last_name,user_name,password,national_code,phone_number) VALUES (?,?,?,?,?,?) returning id;");
        preparedStatement.setString(1,firstName);
        preparedStatement.setString(2,lastName);
        preparedStatement.setString(3,userName);
        preparedStatement.setString(4,password);
        preparedStatement.setString(5,nationalCode);
        preparedStatement.setString(6,phoneNumber);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return resultSet.getInt("id");
        }
        return null;
    }

    public boolean existsById(Integer id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT COUNT(*) as customerExist FROM customer WHERE id = ?;");
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("customerExist") > 0;
        }
        return false;
    }

    public void update(Integer id,String firstName,String lastName,String userName,String password,String nationalCode,String phoneNumber,Integer bankId) throws SQLException {
        if(id != null){
            if(existsById(id)){
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
    }

    public void delete(Integer id) throws SQLException {
        if (id != null) {
            if (existsById(id)){
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM customer WHERE id = ?;");
                preparedStatement.setInt(1,id);
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
        }
    }

    public List<Customer> findAll(Integer id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM customer WHERE id = ?;");
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Employee employee = new Employee(resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("national_code"),
                    resultSet.getString("phone_number"),
                    resultSet.getString("user_name"),
                    resultSet.getString("password"),
                    (new BankBranch(resultSet.getInt("id"),resultSet.getString("bank_name"))));
        }
        return null;
    }

    public Boolean findById(Integer id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM customer WHERE id = ?;");
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

    public Customer findByUserName(String userName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM customer WHERE user_name = ?;");
        preparedStatement.setString(1, userName);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return new Customer(resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("user_name"),
                    resultSet.getString("password"),
                    resultSet.getString("national_code"),
                    resultSet.getString("phone_number"),
                    new BankBranch(resultSet.getInt(8)));
        }
        return null;
    }

    public String findByPassword(String password) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM customer WHERE password = ?;");
        preparedStatement.setString(1,password);
        preparedStatement.executeQuery();
        preparedStatement.close();
        return password;
    }

    public Customer findByUserNameAndGetObject(String userName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM customer WHERE user_name = ?;");
        preparedStatement.setString(1,userName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            return new Customer(resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("national_code"),
                    resultSet.getString("phone_number"),
                    resultSet.getString("user_name"),
                    resultSet.getString("password"),
                    (new BankBranch(resultSet.getInt("id"))));
        }
        return null;
    }
}
