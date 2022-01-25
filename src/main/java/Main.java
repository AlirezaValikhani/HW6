import repositories.*;
import services.*;
import util.ConnectionProvider;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static CreditCardService creditCardService;
    public static void main(String[] args) throws SQLException {
        CreditCardRepository creditCardRepository = new CreditCardRepository(ConnectionProvider.getConnection());
        creditCardRepository.creatCreditCardTable();
        creditCardService = new CreditCardService(creditCardRepository);
        transferMoneyToAnotherCard();
    }

    public static void transferMoneyToAnotherCard() throws SQLException {
        System.out.println("Please enter your card number");
        String fromCard = scanner.nextLine();
        System.out.println("Please enter your destination card number");
        String toCard = scanner.nextLine();
        System.out.println("Please enter your amount");
        Long amount = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Please enter your cvv2");
        String cvv2 = scanner.nextLine();
        System.out.println("Please enter your pass");
        String password = scanner.nextLine();
        creditCardService.transferMoneyToAnotherCard(fromCard,toCard,amount,cvv2,password);
    }
}