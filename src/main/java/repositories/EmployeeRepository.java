package repositories;

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
                "national_cade  VARCHAR (255) ," +
                "phone_number   VARCHAR (255) ," +
                "email          VARCHAR (255)," +
                "address        VARCHAR (255)," +
                "bank_id        INTEGER ," +
                "CONSTRAINT fk_bank FOREIGN KEY (bank_id) REFERENCES bank_branch(id)" +
                ");";
        PreparedStatement preparedStatement = connection.prepareStatement(employeeTable);
        preparedStatement.execute();
    }

    public void insertIntoEmployee(Employee employee) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("INSERT INTO employee (first_name,last_name,national_cade,phone_number,email,address,bank_id) VALUES (?,?,?,?,?,?,?);");
        preparedStatement.setString(1,employee.getFirstName());
        preparedStatement.setString(2,employee.getLastName());
        preparedStatement.setString(3,employee.getNationalCode());
        preparedStatement.setString(4,employee.getPhoneNumber());
        preparedStatement.setString(5,employee.getEmail());
        preparedStatement.setString(6,employee.getAddress());
        preparedStatement.setInt(7,employee.getBankId());
        preparedStatement.executeUpdate();
        connection.close();
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

    public void update(Employee employee) throws SQLException {
        if(employee.getId() != null){
            if(existsById(employee.getId())){
                PreparedStatement preparedStatement =  connection.prepareStatement(
                        "UPDATE employee SET first_name = ? , last_name = ? ,national_code = ? ," +
                                " phonenumber = ? , email = ? , address = ?,bank_id = ?;");
                preparedStatement.setString(1,employee.getFirstName());
                preparedStatement.setString(2,employee.getLastName());
                preparedStatement.setString(3,employee.getNationalCode());
                preparedStatement.setString(4,employee.getPhoneNumber());
                preparedStatement.setString(5,employee.getEmail());
                preparedStatement.setString(6,employee.getAddress());
                preparedStatement.setInt(7,employee.getBankId());
                preparedStatement.executeUpdate();
                connection.close();
            }
        }
    }

    public void delete(Employee employee) throws SQLException {
        if (employee.getId() != null) {
            if (existsById(employee.getId())){
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM employee WHERE id = ?;");
                preparedStatement.setInt(1,employee.getId());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
        }
    }
}
