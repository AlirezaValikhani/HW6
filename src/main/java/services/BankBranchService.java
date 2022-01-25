package services;

import model.Account;
import model.BankBranch;
import model.Customer;
import repositories.BankBranchRepository;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class BankBranchService {
    private BankBranchRepository bankBranchRepository;
    Scanner scanner = new Scanner(System.in);

    public BankBranchService(BankBranchRepository bankBranchRepository) {
        this.bankBranchRepository = bankBranchRepository;
    }

    /*public void insertBankBranch() throws SQLException {
        System.out.println("*Welcome.Made a bank*");
        while (true) {
            try {
                System.out.print("Please enter bank name : ");
                String name = scanner.nextLine();
                System.out.print("Please enter bank id : ");
                Integer id = scanner.nextInt();
                *//*int randomIdBegin = 1000;
                int randomIdEnd = 9999;
                Integer bankBranchId = (int) (Math.random() * (randomIdEnd - randomIdBegin + 1)) + randomIdBegin;*//*
                System.out.println("Your account id is : " + id);
                *//*BankBranch bankBranch = new BankBranch(id,balance);*//*
                bankBranchRepository.insertIntoBankBranch(id,name);
                System.out.println("Your account has made successfully");
            }
            catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("**********************");
                System.out.println("Please enter number!!!");
                System.out.println("**********************");
            }
        }
    }*/

    public void insertIntoBankBranch(Integer id, String bankName) throws SQLException {
        BankBranch bankBranch1 = new BankBranch(id,bankName);
        bankBranchRepository.insertIntoBankBranch(bankBranch1);
    }


}

