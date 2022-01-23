import model.*;
import repositories.*;
import services.BankBranchService;
import services.CreditCardService;
import util.ConnectionProvider;
import util.Validation;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        EmployeeRepository employeeRepository = new EmployeeRepository(ConnectionProvider.getConnection());
        CustomerRepository customerRepository = new CustomerRepository(ConnectionProvider.getConnection());
        CreditCardRepository creditCardRepository = new CreditCardRepository(ConnectionProvider.getConnection());
        TransactionRepository transactionRepository = new TransactionRepository(ConnectionProvider.getConnection());
        BankBranchRepository bankBranchRepository = new BankBranchRepository(ConnectionProvider.getConnection());
        AccountRepository accountRepository = new AccountRepository(ConnectionProvider.getConnection());
        BankBranchCustomerRepository bbcRepository = new BankBranchCustomerRepository(ConnectionProvider.getConnection());
        employeeRepository.creatEmployeeTable();
        customerRepository.creatCustomerTable();
        creditCardRepository.creatCreditCardTable();
        transactionRepository.creatCreditCardTable();
        bankBranchRepository.creatBankBranchTable();
        accountRepository.creatAccountTable();
        /*bbcRepository.creatBankBranchCustomerTable();*/
        /*BankBranchService bankBranchService = new BankBranchService(bankBranchRepository);*/
        Validation validation = new Validation(ConnectionProvider.getConnection(),creditCardRepository);
        CreditCardService creditCardService = new CreditCardService(creditCardRepository,validation);
        creditCardService.transferMoneyToAnotherCard(new CreditCard(1,1234,1000000,12345678,1122334455667788L,"AlirezaValikhani",1122,2024-5-24),8877665544332211L,10000L);
    }
}
/*System.out.println("*Welcome to My Bank*");
        System.out.print("Please enter your first name : ");
        String firstName = scanner.next();
        System.out.print("Please enter your last name : ");
        String lastName = scanner.next();
        System.out.print("Please enter your national_code : ");
        String nationalCode = scanner.next();
        System.out.print("Please enter your phone_number : ");
        String phoneNumber = scanner.next();
        System.out.print("Please enter your email : ");
        String email = scanner.next();
        System.out.print("Please enter your address : ");
        String address = scanner.next();
        Random random = new Random();
        int randomIdBegin = 1000;
        int randomIdEnd = 9999;
        Integer accountId = (int) (Math.random() * (randomIdEnd - randomIdBegin + 1)) +randomIdBegin;
        System.out.print("Your id is : " + accountId);
        Account account = new Account(firstName,lastName,nationalCode,phoneNumber,email,address,accountId);
        return account;*/
