package repositories;

import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                "national_cade  VARCHAR (255) ," +
                "phone_number   VARCHAR (255) " +
                "email          VARCHAR (255)," +
                "address        VARCHAR (255)," +
                "account_id     INTEGER," +
                "customer_id    INTEGER ," +
                ")";
        connection.prepareStatement(customerTable).execute();
        connection.close();
    }

    public void insertIntoCustomer(Customer customer) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO customer (first_name,last_name,national_cade,phone_number,email,address,account_id,customer_id) VALUES (?,?,?,?,?,?,?,?);");
        preparedStatement.setString(1,customer.getFirstName());
        preparedStatement.setString(2,customer.getLastName());
        preparedStatement.setString(3,customer.getNationalCode());
        preparedStatement.setString(4,customer.getPhoneNumber());
        preparedStatement.setString(5,customer.getEmail());
        preparedStatement.setString(6,customer.getAddress());
        preparedStatement.setInt(7,customer.getAccountId());
        preparedStatement.setInt(8,customer.getCustomerId());
        preparedStatement.executeUpdate();
        connection.close();
    }

    public boolean existsById(Integer id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT COUNT(*) as customerExist FROM customer WHERE id = ?;");
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("bankExist") > 0;
        }
        return false;
    }

    public void update(Customer customer) throws SQLException {
        if(customer.getId() != null){
            if(existsById(customer.getId())){
                PreparedStatement preparedStatement =  connection.prepareStatement(
                        "UPDATE customer SET first_name = ? , last_name = ? ,national_code = ? ," +
                                " phonenumber = ? , email = ? , address = ? , account_id = ? , customer_id = ?;");
                preparedStatement.setString(1,customer.getFirstName());
                preparedStatement.setString(2,customer.getLastName());
                preparedStatement.setString(3,customer.getNationalCode());
                preparedStatement.setString(4,customer.getPhoneNumber());
                preparedStatement.setString(5,customer.getEmail());
                preparedStatement.setString(6,customer.getAddress());
                preparedStatement.setInt(7,customer.getAccountId());
                preparedStatement.setInt(8,customer.getCustomerId());
                preparedStatement.executeUpdate();
                connection.close();
            }
        }
    }

    public void delete(Customer customer) throws SQLException {
        if (customer.getId() != null) {
            if (existsById(customer.getId())){
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM customer WHERE id = ?;");
                preparedStatement.setInt(1,customer.getId());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
        }
    }
}
