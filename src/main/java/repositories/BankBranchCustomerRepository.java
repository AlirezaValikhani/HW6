package repositories;

import model.BankBranch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BankBranchCustomerRepository {
    private Connection connection;


    public BankBranchCustomerRepository(Connection connection) {
        this.connection = connection;
    }

    public void creatBankBranchCustomerTable() throws SQLException {
        String bankBranchCustomerTable = "CREATE TABLE IF NOT EXISTS bank_branch_customer(" +
                "id                SERIAL PRIMARY KEY," +
                "customer_id       INTEGER ," +
                "bank_branch_id    INTEGER," +
                "FOREIGN KEY (customer_id) REFERENCES customer(id)," +
                "FOREIGN KEY (bank_branch_id) REFERENCES bank_branch(id)" +
                ");";
        PreparedStatement preparedStatement = connection.prepareStatement(bankBranchCustomerTable);
        preparedStatement.execute();
    }


    public void insertIntoBankBranchCustomer(Integer id,BankBranch bankBranch) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO bank_branch_customer (id,customer_id,bank_branch_id) VALUES (?,?,?);");
        preparedStatement.setInt(1,id);
        preparedStatement.setInt(2,bankBranch.getCustomerId());
        preparedStatement.setInt(3,bankBranch.getId());
        preparedStatement.executeUpdate();
        connection.close();
    }

    public void update(Integer id,BankBranch bankBranch) throws SQLException {
        if(bankBranch.getId() != null){
                PreparedStatement preparedStatement =  connection.prepareStatement(
                        "UPDATE bank_branch_customer SET id = ? , customer_id = ? ,bank_branch_id = ?;");
                preparedStatement.setInt(1,id);
                preparedStatement.setInt(2,bankBranch.getCustomerId());
                preparedStatement.setInt(3,bankBranch.getId());
                preparedStatement.executeUpdate();
                connection.close();
            }
        }

    public void delete(Integer id,BankBranch bankBranch) throws SQLException {
        if (bankBranch.getId() != null) {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM bank_branch_customer WHERE id = ?;");
                preparedStatement.setInt(1,bankBranch.getId());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
        }
    }
