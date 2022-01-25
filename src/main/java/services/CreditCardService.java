package services;

import model.CreditCard;
import repositories.CreditCardRepository;
import util.Validation;

import java.sql.SQLException;
import java.util.Scanner;

public class CreditCardService {
    private final CreditCardRepository creditCardRepository;
    private Validation validation = new Validation();
    Scanner scanner = new Scanner(System.in);

    public CreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    public void insertCreditCard(CreditCard creditCard) throws SQLException {
        creditCardRepository.insertIntoCreditCard(creditCard);
    }

    public void transferMoneyToAnotherCard(String cardNumber,String destinationCardNumber, Long amount,String CVV2,String secondPassword) throws SQLException {
        CreditCard fromCard = creditCardRepository.findByCardNumber(cardNumber);
        CreditCard toCard = creditCardRepository.findByCardNumber(destinationCardNumber);
        if( fromCard != null && validation.informationValidation(fromCard,amount,CVV2,secondPassword) && toCard != null){
            Long balance = (fromCard.getBalance() - 600) - amount;
            fromCard.setBalance(balance);
            creditCardRepository.update(fromCard);
            Long transferMoney = toCard.getBalance() + amount;
            toCard.setBalance(transferMoney);
            creditCardRepository.update(toCard);
            System.out.println("Transfer operation was successful");
        }
    }

    /*public void editPassword(String new password ) throws SQLException {
        if(creditCardRepository.findPassword(creditCard.getPassword())){
            System.out.println("Please enter your new password : ");
            String newPassword = scanner.next();
            CreditCard creditCard1 = new CreditCard(newPassword);
            creditCardRepository.update(creditCard1);
            System.out.println("Your password changed successfully");
        }
    }*/
}
