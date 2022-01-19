package Repositories;

import model.Account;
import model.BankBranch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankBranchRepository {
    private Connection connection;

    public BankBranchRepository(Connection connection) {
        this.connection = connection;
    }

    public void creatBankBranchTable() throws SQLException {
        String bankBranchTable = "CREATE TABLE IF NOT EXISTS bank_branch(" +
                "id             SERIAL PRIMARY KEY," +
                "name           VARCHAR (255)," +
                "account_id     INTEGER ," +
                "customer_id    INTEGER ," +
                "employee_id    INTEGER," +
                "CONSTRAINT fk_employee FOREIGN KEY (employee_id) REFERENCES employee(id);" +
                ")";
        connection.prepareStatement(bankBranchTable).execute();
        connection.close();
    }

    public void insertIntoBankBranch(BankBranch bankBranch) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO account (name,account_id,customer_id,employee_id) VALUES (?,?,?,?);");
        preparedStatement.setString(1,bankBranch.getName());
        preparedStatement.setInt(2,bankBranch.getAccountId());
        preparedStatement.setInt(3,bankBranch.getCustomerId());
        preparedStatement.setInt(4,bankBranch.getEmployeeId());
        preparedStatement.executeUpdate();
        connection.close();
    }

    public boolean existsById(Integer id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT COUNT(*) as bankExist FROM bank_branch WHERE id = ?;");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            return resultSet.getInt("bankExist") > 0;
        }
        return false;
    }


    public void update(BankBranch bankBranch) throws SQLException {
        if(bankBranch.getId() != null){
            if(existsById(bankBranch.getId())){
                PreparedStatement preparedStatement =  connection.prepareStatement(
                        "UPDATE bank_branch SET name = ? , account_id = ? ,customerId = ? , employee_id = ?;");
                preparedStatement.setString(1,bankBranch.getName());
                preparedStatement.setInt(2,bankBranch.getAccountId());
                preparedStatement.setInt(3,bankBranch.getCustomerId());
                preparedStatement.setInt(1,bankBranch.getEmployeeId());
                preparedStatement.executeUpdate();
                connection.close();
            }
        }
    }

    public void delete(BankBranch bankBranch) throws SQLException {
        if (bankBranch.getId() != null) {
            if (existsById(bankBranch.getId())){
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM bank_branch WHERE id = ?;");
                preparedStatement.setInt(1,bankBranch.getId());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
        }
    }

    /*public BankBranch findById(ََBankBranch bankBranch) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM bank_branch WHERE id = ?;");
        preparedStatement.setLong(1, bankBranch.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        BankBranch bankBranch1 = null;
        while(resultSet.next()) {
            bankBranch1 = new BankBranch(resultSet.getInt("id")
                    , resultSet.getString("name")
                    , resultSet.getInt("account_id")
                    , resultSet.getInt("customer_id")
                    , resultSet.getInt("employee_id"));
        }
        return bankBranch1;
    }*/
}
