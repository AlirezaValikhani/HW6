package services;

import model.CreditCard;
import repositories.CreditCardRepository;
import util.Validation;

import java.sql.SQLException;

public class CreditCardService {
    private final CreditCardRepository creditCardRepository;
    private Validation validation;

    public CreditCardService(CreditCardRepository creditCardRepository, Validation validation) {
        this.creditCardRepository = creditCardRepository;
        this.validation = validation;
    }

    public void insertCreditCard(CreditCard creditCard) throws SQLException {
        creditCardRepository.insertIntoCreditCard(creditCard);
    }

    public void transferMoneyToAnotherCard(CreditCard creditCard,Long destinationCreditCard, Long amount) throws SQLException {
        if(creditCardRepository.findDestinationCreditCard(destinationCreditCard) &&
                validation.informationValidation(creditCard,amount)){
            Long balance = creditCard.getBalance() - amount;
            Long transferMoney = creditCard.getBalance() + amount;
        }
    }
}
