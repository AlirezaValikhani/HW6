import model.Account;
import model.CreditCard;
import model.Customer;
import model.Employee;
import repositories.*;
import services.*;
import util.ConnectionProvider;
import util.Utilities;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static CreditCardService creditCardService;
    static BankBranchService bankBranchService;
    static EmployeeService employeeService;
    static CustomerService customerService;
    static AccountService accountService;
    static Utilities utilities = new Utilities(ConnectionProvider.getConnection());

    public static void main(String[] args) throws SQLException {
        BankBranchRepository bankBranchRepository = new BankBranchRepository(ConnectionProvider.getConnection());
        bankBranchRepository.creatBankBranchTable();
        bankBranchService = new BankBranchService(bankBranchRepository);
        CreditCardRepository creditCardRepository = new CreditCardRepository(ConnectionProvider.getConnection());
        creditCardRepository.creatCreditCardTable();
        creditCardService = new CreditCardService(creditCardRepository);
        EmployeeRepository employeeRepository = new EmployeeRepository(ConnectionProvider.getConnection());
        employeeRepository.creatEmployeeTable();
        employeeService = new EmployeeService(employeeRepository);
        CustomerRepository customerRepository = new CustomerRepository(ConnectionProvider.getConnection());
        customerRepository.creatCustomerTable();
        customerService = new CustomerService(customerRepository);
        AccountRepository accountRepository = new AccountRepository(ConnectionProvider.getConnection());
        accountRepository.creatAccountTable();
        accountService = new AccountService(accountRepository);
        System.out.println("Hi please choose a number");
        while (true) {
            System.out.println("please choose one of numbers :)\n" +
                    "1-Create bank\n" +
                    "2-Create employee\n" +
                    "3-Login as employee\n" +
                    "4-Login as customer");
            String input = scanner.nextLine();
            if (input.equals("1")) {
                createBank();
            } else if (input.equals("2")) {
                createEmployee();
            } else if (input.equals("3")) {
                employeeLogin();
            } else if (input.equals("4")) {
                customerLogin();
            }
        }
    }

    public static void createBank() throws SQLException {
        System.out.println("Welcome.Make your bank");
        System.out.print("Please enter bank id : ");
        Integer bankId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Please enter bank name : ");
        String bankName = scanner.nextLine();
        bankBranchService.insertIntoBankBranch(bankId, bankName);
    }

    public static void employeeLogin() throws SQLException {
        System.out.println("Welcome to the login section");
        System.out.println("Enter your user name : ");
        String userName = scanner.nextLine();
        if (employeeService.existUserName(userName)) {
            System.out.println("Enter your password : ");
            String password = scanner.nextLine();
            if (employeeService.existPassword(password)) {
                employeeService.findEmployeeByPassword(password);
                managerMenu();
            }
        } else System.out.println("User name or password is wrong!!!");
    }

    public static void managerMenu() throws SQLException {
        System.out.println("Welcome to employee logged in section");
        while (true) {
            System.out.println("1-Add customer\n" +
                    "2-Delete customer\n" +
                    "3-Exit");
            String input = scanner.nextLine();
            if (input.equals("1")) {
                addCustomer(input);
            }else if(input.equals("2")){
                deleteCustomer(input);
            } else if (input.equals("3")) {
                System.out.println("I hope see you next time");
                break;
            } else{
                System.out.println("***********************************\n Please enter 1 or 2 or 3 or 4!!! \n***********************************");
            }
        }
    }

    public static void addCustomer(String input) throws SQLException {
        if (input.equals("1")) {
            System.out.println("Enter customers first name : ");
            String firstName = scanner.nextLine();
            System.out.println("Enter customers last name : ");
            String lastName = scanner.nextLine();
            System.out.println("Enter customers user name : ");
            String userName = scanner.nextLine();
            System.out.println("Enter customers password : ");
            String password = scanner.nextLine();
            System.out.println("Enter customers national code : ");
            String nationalCode = scanner.nextLine();
            System.out.println("Enter customers phone number : ");
            String phoneNumber = scanner.nextLine();
            System.out.println("Enter customers balance : ");
            Double balance = scanner.nextDouble();
            Integer customerId = customerService.insertIntoCustomer(firstName, lastName,
                    userName, password, nationalCode, phoneNumber);
            Customer customer = new Customer(customerId);
            Account account = new Account(balance, customer);
            Integer accountId = accountService.insertIntoAccount(account);
            Integer creditCartId = addCreditCard(customerId,accountId);
            System.out.println("**********************************************************************\n" +
                    "Customer created successfully (username :  "
                    + userName + "  / password :  " + password + " )\nCustomer Id : "
                    + customerId + "\nAccount ID : " + accountId + "\nCredit cart ID : " + creditCartId +
                    "\n**********************************************************************");
        }
    }

    public static void deleteCustomer(String input) throws SQLException {
            System.out.println("Please enter customer id : ");
            Integer id = scanner.nextInt();
            if (customerService.existsById(id))
                customerService.deleteCustomer(id);
            System.out.println("Customer deleted successfully : ");
    }

    public static Integer addCreditCard(Integer customerId,Integer accountId) throws SQLException {
        Integer id = null;
        Integer password = null;
        Long secondPassword = null;
        Long cardNumber;
        if (customerService.findById(customerId) != null) {
            try {
                System.out.println("Enter card password : ");
                password = scanner.nextInt();
                if (String.valueOf(password).length() != 4) {
                    System.out.println("You only need to enter a number of length 4");
                }
            } catch (NumberFormatException e) {
                System.out.println("You only have to enter a number");
            }
        }
        try {
            System.out.println("Please enter your second password : ");
            secondPassword = scanner.nextLong();
            if (String.valueOf(secondPassword).length() < 8) {
                System.out.println("Second password must have more than 8 numbers");
            } else {
            }
        } catch (NumberFormatException e) {
            System.out.println("You only have to enter a number");
        }
        Long leftLimit = 1000000000000000L;
        Long rightLimit = 9999999999999999L;
        cardNumber = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
        Long CVV2 = (int) (Math.random() * (9999L - 1000L + 1)) + 1000L;
        Date date = new Date(System.currentTimeMillis());
        Timestamp expirationDate = new Timestamp(date.getTime());
        Integer creditCardId = creditCardService.insertIntoCreditCard(password, secondPassword, cardNumber, CVV2, expirationDate,accountId);
        System.out.println("Credit card was made successfully and your card number is : " + cardNumber );
        return creditCardId;
    }

    public static void customerLogin() throws SQLException {
        System.out.println("Welcome to the login section");
        System.out.println("Enter your user name : ");
        String userName = scanner.nextLine();
        System.out.println("Enter your password : ");
        String password = scanner.nextLine();
        if (customerService.existsByUserName(userName) != null) {
            if (customerService.existPassword(password)) {
                Customer customer = customerService.findEmployeeByUserName(userName);
                customerMenu(customer);
            }
        } else System.out.println("***********************************\nUser name or password is wrong!!!\n***********************************");
    }

    public static void customerMenu(Customer customer) throws SQLException {
        System.out.println("Welcome to employee logged in section");
        while (true) {
            System.out.println("1-See your all accounts\n" +
                    "2-Transfer money into other account\n" +
                    "3-Change your password\n" +
                    "4-Change your second password\n" +
                    "5-Exit");
            String input = scanner.nextLine();
            if(input.equals("1")){
                watchAllAccounts(customer);
            }else if (input.equals("2")){
                transferMoneyIntoOtherAccount(customer);
            }else if (input.equals("3")){
                changePassword();
            }else if (input.equals("4")){
                changeSecondPassword();
            }else if (input.equals("5")){
                System.out.println("I hope see you next time");
                break;
            }
        }
    }

    public static void watchAllAccounts(Customer customer){
        try {
            List<Account> customerAccounts = accountService.showAllAccounts(customer.getId());
            System.out.println(customerAccounts);
        } catch (NumberFormatException | SQLException e) {
            System.out.println("You only have to enter a number");
        }
    }

    public static void transferMoneyIntoOtherAccount(Customer customer) throws SQLException {
        System.out.println("Enter your card number : ");
        String cardNumber = scanner.next();
        if (cardNumber.contains("1234567890") && cardNumber.length() > 16) {
            System.out.println("You must enter a number of length 16 and you have only enter number!!!");
        }
        System.out.println("Enter destination card number : ");
        String destinationCardNumber = scanner.next();
        if (cardNumber.contains("1234567890") && cardNumber.length() > 16) {
            System.out.println("You must enter a number of length 16 and you have only enter number!!!");
        }
        System.out.println("Enter the desired amount: ");
        Double amount = scanner.nextDouble();
        System.out.println("Enter your cvv2 : ");
        String cvv2 = scanner.next();
        System.out.println("Enter your second password : ");
        String secondPassword = scanner.next();
        creditCardService.transferMoneyToAnotherCard(cardNumber, destinationCardNumber, amount, cvv2, secondPassword);
    }

    public static void changePassword() throws SQLException {
        System.out.println("Enter your credit card id : ");
        Integer creditCardId = scanner.nextInt();
        System.out.println("Enter your new password : ");
        Integer newPassword = scanner.nextInt();
        creditCardService.editPassword(newPassword, creditCardId);
    }

    public static void changeSecondPassword() throws SQLException {
        System.out.println("Enter your credit card id : ");
        Integer creditCardId = scanner.nextInt();
        System.out.println("Enter your new second password : ");
        Integer newSecondPassword = scanner.nextInt();
        creditCardService.editPassword(newSecondPassword, creditCardId);
    }

    public static void createEmployee() throws SQLException {
        System.out.println("*Employee creation section*");
        System.out.println("Enter first name : ");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name : ");
        String lastName = scanner.nextLine();
        System.out.println("Enter user name : ");
        String userName = scanner.nextLine();
        System.out.println("Enter password : ");
        String password = scanner.nextLine();
        System.out.println("Enter national code : ");
        String nationalCode = scanner.nextLine();
        System.out.println("Enter phone number : ");
        String phoneNumber = scanner.nextLine();
        employeeService.insertIntoEmployee(firstName, lastName, userName, password, nationalCode, phoneNumber);
        System.out.println("----------------------\n Employee added successfully \n----------------------");
    }
}