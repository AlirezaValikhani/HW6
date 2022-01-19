package Repositories;

import model.BankBranch;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BankBranchCustomerRepository {
    private Connection connection;

    public BankBranchCustomerRepository(Connection connection) {
        this.connection = connection;
    }

    public void creatBankBranchCustomerTable() throws SQLException {
        String bankBranchTable = "CREATE TABLE IF NOT EXISTS bank_branch_customer(" +
                "id                SERIAL PRIMARY KEY," +
                "customer_id       INTEGER ," +
                "bank_branch_id    INTEGER," +
                "CONSTRAINT fk_customer REFERENCES customer(id)," +
                "CONSTRAINT fk_bank_branch REFERENCES bank_branch(id)" +
                ")";
        connection.prepareStatement(bankBranchTable).execute();
        connection.close();
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