package services;

import model.Account;
import model.BankBranch;
import model.Customer;
import repositories.BankBranchRepository;

import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class BankBranchService {
    private BankBranchRepository bankBranchRepository;

    public BankBranchService(BankBranchRepository bankBranchRepository) {
        this.bankBranchRepository = bankBranchRepository;
    }

    public void insertBankBranch(BankBranch bankBranch) throws SQLException {
        bankBranchRepository.insertIntoBankBranch(bankBranch);
    }
}

