package util;

import model.Account;
import model.CreditCard;
import repositories.CreditCardRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class Validation {
    private Connection connection;
    private CreditCardRepository creditCardRepository;

    public Validation(Connection connection, CreditCardRepository creditCardRepository) {
        this.connection = connection;
        this.creditCardRepository = creditCardRepository;
    }

    public Boolean informationValidation(CreditCard creditCard, Long amount) throws SQLException {
        if (creditCardRepository.findSecondPassword(creditCard) &&
                creditCardRepository.findCVV2(creditCard.getCVV2()) &&
                creditCardRepository.findExpirationDate((Date) creditCard.getExpirationDate()) &&
                creditCard.getBalance() > amount) {
            return true;
        }else return false;
    }


}
