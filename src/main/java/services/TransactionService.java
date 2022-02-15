package services;

import repositories.EmployeeRepository;
import repositories.TransactionRepository;

import java.sql.SQLException;
import java.sql.Timestamp;

public class TransactionService {
    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void insertIntoTransaction(Timestamp dateTime, Integer sourceAccountId, Integer destinationAccountId) throws SQLException {
        transactionRepository.insertIntoTransaction(dateTime,sourceAccountId,destinationAccountId);
    }

    public void updateTransaction(Integer id ,Timestamp dateTime, Integer sourceAccountId, Integer destinationAccountId) throws SQLException {
        transactionRepository.update(id,dateTime,sourceAccountId,destinationAccountId);
    }

    public void deleteTransaction(Integer id) throws SQLException {
        transactionRepository.delete(id);
    }
}
