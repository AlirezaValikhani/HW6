package repositories;

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
                "bank_name           VARCHAR (255)" +
                ");";
    PreparedStatement preparedStatement = connection.prepareStatement(bankBranchTable);
    preparedStatement.execute();
    }

    public void insertIntoBankBranch(BankBranch bankBranch) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO bank_branch (id ,bank_name) VALUES (?,?);");
        preparedStatement.setInt(1,bankBranch.getId());
        preparedStatement.setString(2,bankBranch.getBankName());
        preparedStatement.executeUpdate();
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
                        "UPDATE bank_branch SET id = ? ,bank_name = ?;");
                preparedStatement.setInt(1,bankBranch.getId());
                preparedStatement.setString(2,bankBranch.getBankName());
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

    public BankBranch findById(BankBranch bankBranch) throws SQLException {
        String s = "SELECT * FROM bank_branch WHERE id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(s);
        preparedStatement.setLong(1, bankBranch.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        BankBranch bankBranch1 = null;
        while(resultSet.next()) {
            bankBranch1 = new BankBranch(resultSet.getInt("id")
                    , resultSet.getString("bank_name"));
        }
        return bankBranch1;
    }
}
