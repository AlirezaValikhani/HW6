package services;

import model.Account;
import model.Customer;
import repositories.AccountRepository;
import repositories.CustomerRepository;
import util.ConnectionProvider;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class AccountService {
    AccountRepository accountRepository;
    Scanner scanner = new Scanner(System.in);

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void insertAnAccount() throws SQLException {
        System.out.println("*Create your account*");
        while (true) {
            try {
                System.out.print("Please pay your first balance : ");
                Long balance = scanner.nextLong();
                int randomIdBegin = 1000;
                int randomIdEnd = 9999;
                Integer accountId = (int) (Math.random() * (randomIdEnd - randomIdBegin + 1)) + randomIdBegin;
                System.out.println("Your account id is : " + accountId);
                Account account = new Account(accountId,balance);
                accountRepository.insertIntoAccount(account);
                System.out.println("Your account has made successfully");
            }
            catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("**********************");
                System.out.println("Please enter number!!!");
                System.out.println("**********************");
            }
        }
    }
}