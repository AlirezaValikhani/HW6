package repositories;

import model.BankBranch;
import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRepository {
    private Connection connection;

    public EmployeeRepository(Connection connection) {
        this.connection = connection;
    }

    public void creatEmployeeTable() throws SQLException {
        String employeeTable = "CREATE TABLE IF NOT EXISTS employee(" +
                "id             SERIAL PRIMARY KEY," +
                "first_name     VARCHAR (255)," +
                "last_name      VARCHAR (255) ," +
                "national_code  VARCHAR (255) UNIQUE NOT NULL," +
                "phone_number   VARCHAR (255) ," +
                "user_name    VARCHAR (255) UNIQUE ," +
                "password       VARCHAR (255)," +
                "bank_id        INTEGER ," +
                "CONSTRAINT fk_bank_branch FOREIGN KEY (bank_id) REFERENCES bank_branch(id)" +
                ");";
        PreparedStatement preparedStatement = connection.prepareStatement(employeeTable);
        preparedStatement.execute();
    }

    public void insertIntoEmployee(String firstName,String lastName,String userName,String password,String nationalCode,String phoneNumber) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("INSERT INTO employee (first_name,last_name,national_code,phone_number,user_name,password)" +
                        " VALUES (?,?,?,?,?,?);");
        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);
        preparedStatement.setString(3, nationalCode);
        preparedStatement.setString(4, phoneNumber);
        preparedStatement.setString(5, userName);
        preparedStatement.setString(6, password);
        preparedStatement.executeUpdate();
    }

    public boolean existsById(Integer id) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT COUNT(*) as employeeExist FROM employee WHERE id = ?;");
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("employeeExist") > 0;
        }
        return false;
    }

    public void update(Integer id,String firstName,String lastName,String userName,String password,String nationalCode,String phoneNumber,Integer bankId) throws SQLException {
        if(id != null){
            if(existsById(id)){
                PreparedStatement preparedStatement =  connection.prepareStatement(
                        "UPDATE employee SET first_name = ? , last_name = ? ,national_code = ? ," +
                                " phonenumber = ? ,user_name = ? , password = ? ,bank_id = ?;");
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, nationalCode);
                preparedStatement.setString(4, phoneNumber);
                preparedStatement.setString(5, userName);
                preparedStatement.setString(6, password);
                preparedStatement.setInt(7, bankId);
                preparedStatement.executeUpdate();
                preparedStatement.executeUpdate();
                connection.close();
            }
        }
    }

    public void delete(Integer id) throws SQLException {
        if (id != null) {
            if (existsById(id)){
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM employee WHERE id = ?;");
                preparedStatement.setInt(1,id);
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
        }
    }

    public String findByUserName(String userName) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM employee WHERE user_name = ?;");
                preparedStatement.setString(1,userName);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) return userName;
                else return null;
            }

    public Employee findByUserNameAndGetObject(String userName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM employee WHERE user_name = ?;");
        preparedStatement.setString(1,userName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Employee employee;
            employee = new Employee(resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("national_code"),
                    resultSet.getString("phone_number"),
                    resultSet.getString("user_name"),
                    resultSet.getString("password"),
                    (new BankBranch(resultSet.getInt(1),resultSet.getString(2))));
        }
        return null;
    }

    public String findByPassword(String password) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM employee WHERE password = ?;");
        preparedStatement.setString(1,password);
        preparedStatement.executeQuery();
        preparedStatement.close();
        return password;
    }
}
